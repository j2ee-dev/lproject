package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.RepealedAct;
import me.ataur.bdlaws.admin.model.RepealedActHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Imran Hossain
 */

public class RepealedActEntityListener {


	@PostPersist
	public void prePersist(RepealedAct target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(RepealedAct target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(RepealedAct target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(RepealedAct target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getSimpleName();
		entityManager.persist(new RepealedActHistory(target,action,cl));
	}

}