package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.ActScheduleForAct;
import me.ataur.bdlaws.admin.model.ActScheduleForActHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Imran Hossain
 */
public class ActScheduleForActEntityListener {


	@PostPersist
	public void prePersist(ActScheduleForAct target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(ActScheduleForAct target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(ActScheduleForAct target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(ActScheduleForAct target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getSimpleName();
		entityManager.persist(new ActScheduleForActHistory(target,action,cl));
	}

}