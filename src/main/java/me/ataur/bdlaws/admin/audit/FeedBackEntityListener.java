package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.FeedBack;
import me.ataur.bdlaws.admin.model.FeedBackHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Imran Hossain
 */

public class FeedBackEntityListener {


	@PostPersist
	public void prePersist(FeedBack target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(FeedBack target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(FeedBack target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(FeedBack target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getSimpleName();
		entityManager.persist(new FeedBackHistory(target,action,cl));
	}

}