package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.ActSchedule;
import me.ataur.bdlaws.admin.model.ActScheduleHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Imran Hossain
 */

public class ActScheduleEntityListener {


	@PostPersist
	public void prePersist(ActSchedule target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(ActSchedule target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(ActSchedule target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(ActSchedule target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getSimpleName();
		entityManager.persist(new ActScheduleHistory(target,action,cl));
	}

}