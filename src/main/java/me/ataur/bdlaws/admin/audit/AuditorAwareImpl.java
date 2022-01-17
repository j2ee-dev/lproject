package me.ataur.bdlaws.admin.audit;

import me.ataur.bdlaws.admin.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;



/**
 * @author Amran
 */

class AuditorAwareImpl implements AuditorAware<String> {

	// @Override
	public String getCurrentAuditor() {
		// Can use Spring Security to return currently logged in user
		return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

	}
}
