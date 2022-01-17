package me.ataur.bdlaws.login.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import me.ataur.bdlaws.admin.model.CreateViews;
import me.ataur.bdlaws.admin.model.EditViews;
import me.ataur.bdlaws.admin.model.Permission;
import me.ataur.bdlaws.admin.model.Views;
import me.ataur.bdlaws.admin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
/**
 * @author Amran
 */
@ControllerAdvice
public class GlobalController {

	@Autowired
	PermissionRepository permissionRepository;
	@Autowired
	ViewRepository viewRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CreateViewRepository createViewRepository;
	@Autowired
	EditViewRepository editViewRepository;


	@ModelAttribute("views")
	public void views(Model model) {
		List<Views> views = viewRepository.findAll();
		model.addAttribute("views", views);
	}
	@ModelAttribute("createViews")
	public void createViews(Model model) {
		List<CreateViews> views = createViewRepository.findAll();
		model.addAttribute("createViews", views);
	}
	@ModelAttribute("editViews")
	public void editViews(Model model) {
		List<EditViews> views = editViewRepository.findAll();
		model.addAttribute("editViews", views);
	}


@ModelAttribute("users")
	public void users(Model model) {
	model.addAttribute("users", userRepository.findAll());
	}

	@ModelAttribute("username")
	public void username(Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		model.addAttribute("username", username);
	}

	@ModelAttribute("pviews")
	public void pviews(Model model, HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		List<Permission> userList = permissionRepository.findAllByUsername(username);
		model.addAttribute("pviews", userList);
		
	}



	/*Checks------------------------------------------------------------------------*/
	@ModelAttribute("view_checks")
	public void checks(Model model, HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String views = request.getServletPath();

		List<Permission> checks = permissionRepository.getUsersByViewUrls(views, username);
		Object[] userArray = checks.toArray();
		model.addAttribute("view_checks", userArray);
	}

	@ModelAttribute("editChecks")
	public void editChecks(Model model, HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String editChecks = request.getServletPath();
		String replaceNumber = editChecks.replaceAll("[0-9]", "");
		List<Permission> checks = permissionRepository.getUsersByEditUrls(replaceNumber, username);
		Object[] userArray = checks.toArray();
		model.addAttribute("editChecks", userArray);
	}

	@ModelAttribute("createChecks")
	public void createChecks(Model model, HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String views = request.getServletPath();

		List<Permission> checks = permissionRepository.getUsersByCreateUrls(views, username);
		Object[] userArray = checks.toArray();
		model.addAttribute("createChecks", userArray);
	}
	/*Checks------------------------------------------------------------------------*/


	@ModelAttribute("urls")
	public void urls(Model model,HttpServletRequest request){
		String url = request.getServletPath();
		model.addAttribute("urls",url);
	}


	@ModelAttribute("generateWords")
	public void generateWords(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("generateWords", authentication.getName());
		System.out.println(authentication.getName());

	}

}
