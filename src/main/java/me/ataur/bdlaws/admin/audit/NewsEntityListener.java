package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.News;
import me.ataur.bdlaws.admin.model.NewseHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Imran Hossain
 */

public class NewsEntityListener {


	@PostPersist
	public void prePersist(News target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(News target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(News target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(News target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getSimpleName();
		entityManager.persist(new NewseHistory(target,action,cl));

	}

}