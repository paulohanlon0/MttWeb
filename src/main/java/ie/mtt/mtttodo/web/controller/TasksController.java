package ie.mtt.mtttodo.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ie.mtt.mtttodo.web.form.TasksForm;
import ie.mtt.mtttodo.web.validation.TaskValidator;
import ie.mtt.mtttodo.auth.api.AuthUser;
import ie.mtt.mtttodo.services.IMttService;
import ie.mtt.mtttodo.services.Task;
import ie.mtt.mtttodo.services.exception.MttServiceException;

@Controller
public class TasksController {
	
	@Autowired
	private IMttService mttService;
	
	@RequestMapping(value="/home")
	public ModelAndView home(HttpServletRequest request) {
		AuthUser user = (AuthUser)request.getSession().getAttribute("authUser");
		if(user != null) {
			return this.retrieveTasks(user);
		} else {
			return new ModelAndView("sessionTimeOut");
		}
	}
	
	@RequestMapping(value="/delete")
	public ModelAndView delete(@RequestParam String taskId, HttpServletRequest request) {
		AuthUser user = (AuthUser)request.getSession().getAttribute("authUser");
		if(user != null) {
			try {
				mttService.deleteTask(this.tryParseInt(taskId));
				return this.retrieveTasks(user);
			} catch(MttServiceException _e) {
				return new ModelAndView("uncaughtException");
			}
		} else {
			return new ModelAndView("sessionTimeOut");
		}
	}
	
	@RequestMapping(value="/updateForm")
	public ModelAndView updateForm(@RequestParam String taskId, HttpServletRequest request) {
		AuthUser user = (AuthUser)request.getSession().getAttribute("authUser");
		if(user != null) {
			try {
				ModelAndView update = new ModelAndView("update");
				Task task = mttService.getTask(this.tryParseInt(taskId));
				if( task != null) {
					update.getModelMap().addAttribute("taskForm", task);
					return update;
				} else {
					return this.retrieveTasks(user);
				}
			} catch(MttServiceException _e) {
				return new ModelAndView("uncaughtException");
			}
		} else {
			return new ModelAndView("sessionTimeOut");
		}
	}
	
	@RequestMapping(value="/updateTask", method = RequestMethod.POST)
	public ModelAndView updateTask(@ModelAttribute("taskForm") Task taskForm, BindingResult bindingResult, HttpServletRequest request) {
		AuthUser user = (AuthUser)request.getSession().getAttribute("authUser");
		if(user != null) {
			TaskValidator validator = new TaskValidator();
			validator.validate(taskForm, bindingResult);
			if (bindingResult.hasErrors()) {
				return new ModelAndView("update");
			}
			try {
				mttService.updateTask(taskForm);
				return this.retrieveTasks(user);
			} catch(MttServiceException _ex) {
				_ex.printStackTrace();
				return new ModelAndView("uncaughtException");
			}
		} else {
			return new ModelAndView("sessionTimeOut");
		}
	}
	
	@RequestMapping(value="/createForm")
	public ModelAndView createForm(HttpServletRequest request) {
		AuthUser user = (AuthUser)request.getSession().getAttribute("authUser");
		if(user != null) {
			ModelAndView create = new ModelAndView("create");
			create.getModelMap().addAttribute("taskForm", new Task());
			return create;
		} else {
			return new ModelAndView("sessionTimeOut");
		}
	}
	
	@RequestMapping(value="/createTask", method = RequestMethod.POST)
	public ModelAndView createTask(@ModelAttribute("taskForm") Task taskForm, BindingResult bindingResult, HttpServletRequest request) {
		
		AuthUser user = (AuthUser)request.getSession().getAttribute("authUser");
		if(user != null) {
			TaskValidator validator = new TaskValidator();
			validator.validate(taskForm, bindingResult);
			if (bindingResult.hasErrors()) {
				return new ModelAndView("create");
			}
			try {
				mttService.createTask(user.getInternalId(),taskForm);
				return this.retrieveTasks(user);
			} catch(MttServiceException _ex) {
				_ex.printStackTrace();
				return new ModelAndView("uncaughtException");
			}
		} else {
			return new ModelAndView("sessionTimeOut");
		}
	}
	
	
	private int tryParseInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch(NumberFormatException _ex) {
			return 0;
		}
	}
	
	private ModelAndView retrieveTasks(AuthUser user) {
		
		try {
			ModelAndView tasks = new ModelAndView("list");
			TasksForm tasksForm = new TasksForm();
			tasksForm.setTasks(mttService.retrieveTasksForUser(user.getInternalId()));
			tasks.getModelMap().addAttribute("tasksForm", tasksForm.getTasks().iterator());
			return tasks;
		} catch(MttServiceException _e) {
			return new ModelAndView("uncaughtException");
		}
	}
}
