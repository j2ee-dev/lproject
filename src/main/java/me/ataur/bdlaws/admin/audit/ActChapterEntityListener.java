package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.ActChapter;
import me.ataur.bdlaws.admin.model.ActChapterHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Amran
 */

public class ActChapterEntityListener {


	@PostPersist
	public void prePersist(ActChapter target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(ActChapter target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(ActChapter target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(ActChapter target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getSimpleName();
		entityManager.persist(new ActChapterHistory(target,action,cl));
	}

}