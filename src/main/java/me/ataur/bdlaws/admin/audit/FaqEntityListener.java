package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.Faq;
import me.ataur.bdlaws.admin.model.FaqHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Imran Hossain
 */

public class FaqEntityListener {


	@PostPersist
	public void prePersist(Faq target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(Faq target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(Faq target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(Faq target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getSimpleName();
		entityManager.persist(new FaqHistory(target,action,cl));
	}

}