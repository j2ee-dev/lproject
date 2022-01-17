package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.RelatedLink;
import me.ataur.bdlaws.admin.model.RelatedLinkHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Imran Hossain
 */

public class RelatedLinkEntityListener {


	@PostPersist
	public void prePersist(RelatedLink target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(RelatedLink target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(RelatedLink target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(RelatedLink target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getSimpleName();
		entityManager.persist(new RelatedLinkHistory(target,action,cl));

	}

}