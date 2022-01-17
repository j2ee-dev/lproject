package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.Volume;
import me.ataur.bdlaws.admin.model.VolumeHistory;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author Imran Hossain
 */

public class VolumeEntityListener {


	@PostPersist
	public void prePersist(Volume target) {
		perform(target, Action.INSERTED);
	}

	@PostUpdate
	public void preUpdate(Volume target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(Volume target) {
		perform(target, Action.DELETED);
	}

	@Transactional(MANDATORY)
	private void perform(Volume target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		String cl=target.getClass().getSimpleName();
		entityManager.persist(new VolumeHistory(target,action,cl));
	}

}