package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.StaticPage;
import me.ataur.bdlaws.admin.model.StaticPageHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Imran Hossain
 */

public class StaticPageEntityListener {


	@PostPersist
	public void prePersist(StaticPage target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(StaticPage target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(StaticPage target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(StaticPage target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getSimpleName();
		entityManager.persist(new StaticPageHistory(target,action,cl));
	}

}