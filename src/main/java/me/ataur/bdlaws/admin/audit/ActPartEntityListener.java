package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.ActPart;
import me.ataur.bdlaws.admin.model.ActPartHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Imran Hossain
 */

public class ActPartEntityListener {


	@PostPersist
	public void prePersist(ActPart target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(ActPart target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(ActPart target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(ActPart target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getSimpleName();
		entityManager.persist(new ActPartHistory(target,action,cl));
	}

}