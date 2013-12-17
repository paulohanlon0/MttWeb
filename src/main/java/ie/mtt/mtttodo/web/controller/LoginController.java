package ie.mtt.mtttodo.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ie.mtt.mtttodo.auth.api.AuthCredentials;
import ie.mtt.mtttodo.auth.api.IAuthManager;
import ie.mtt.mtttodo.auth.api.AuthManagerFactory;
import ie.mtt.mtttodo.auth.api.AuthUser;
import ie.mtt.mtttodo.auth.api.exception.InvalidAuthException;
import ie.mtt.mtttodo.auth.api.exception.UserLockedException;

import ie.mtt.mtttodo.services.IMttService;
import ie.mtt.mtttodo.services.exception.MttServiceException;

import ie.mtt.mtttodo.web.form.LoginForm;
import ie.mtt.mtttodo.web.form.TasksForm;
import ie.mtt.mtttodo.web.validation.LoginValidator;

@RequestMapping(value="/")
@Controller
public class LoginController {
	
	@Autowired
	private IMttService mttService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView loginForm() {
		ModelAndView login = new ModelAndView("login");
		login.getModelMap().addAttribute("loginForm", new LoginForm());
		return login;
	}
	
	@RequestMapping(value= "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {
		LoginValidator validator = new LoginValidator();
		validator.validate(loginForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return new ModelAndView("login");
		}
		AuthCredentials credentials = new AuthCredentials(loginForm.getUsername(), loginForm.getPassword());
		IAuthManager auth = AuthManagerFactory.getAuthManager();
		try {
			AuthUser user = auth.authorise(credentials);
			request.getSession(true).setAttribute("authUser", user);
			ModelAndView tasks = new ModelAndView("list");
			TasksForm tasksForm = new TasksForm();
			tasksForm.setTasks(mttService.retrieveTasksForUser(user.getInternalId()));
			tasks.getModelMap().addAttribute("tasksForm", tasksForm.getTasks().iterator());
			return tasks;
		} catch(InvalidAuthException _e) {
			bindingResult.rejectValue("general", "error.auth.invalid");
			return new ModelAndView("login");
		} catch(UserLockedException _ex) {
			bindingResult.rejectValue("general", "error.user.locked");
			return new ModelAndView("login");
		} catch(MttServiceException _s) {
			_s.printStackTrace();
			return new ModelAndView("uncaughtException");
		}
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().invalidate();
		ModelAndView login = new ModelAndView("login");
		login.getModelMap().addAttribute("loginForm", new LoginForm());
		return login;
	}
}
