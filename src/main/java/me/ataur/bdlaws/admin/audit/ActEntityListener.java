package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.Act;
import me.ataur.bdlaws.admin.model.ActHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Amran
 */

public class ActEntityListener {


	@PostPersist
	public void prePersist(Act target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(Act target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(Act target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(Act target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getName();
		entityManager.persist(new ActHistory(target,action,cl));
	}

}