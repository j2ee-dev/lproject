package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.ActSection;
import me.ataur.bdlaws.admin.model.ActSectionHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Imran Hossain
 */

public class ActSectionEntityListener {


	@PostPersist
	public void prePersist(ActSection target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(ActSection target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(ActSection target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(ActSection target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getSimpleName();
		entityManager.persist(new ActSectionHistory(target,action,cl));
	}

}