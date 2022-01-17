package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.Message;
import me.ataur.bdlaws.admin.model.MessageHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Imran Hossain
 */
public class MessageEntityListener {


	@PostPersist
	public void prePersist(Message target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(Message target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(Message target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(Message target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getSimpleName();
		entityManager.persist(new MessageHistory(target,action,cl));

	}

}