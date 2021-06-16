package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Employee;
import com.exception.NoResourceException;
import com.exception.SkillMismatchException;
import com.service.EmployeeProjectService;

@Controller
public class AddEmpController {
	@Autowired
	EmployeeProjectService service;
	@RequestMapping("Home.htm")
public ModelAndView displayHomePage()
{
	ModelAndView mv=new ModelAndView();
	mv.setViewName("home");
	return mv;
}
	@RequestMapping("addemployee.htm")
	public ModelAndView displayAddEmployeeForm(ModelMap map)
	{
		Employee employee=new Employee();
		map.addAttribute("EMP",employee);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("addEmployee");
		return mv;
	}
	@ModelAttribute("list")
	public Map<String, String> populateProject()
	{
		List<String> list=service.getAllProjects();
		Map<String, String> mlist=new HashMap<>();
		for (String projectid : list) {
			
			mlist.put(projectid, projectid);
			
		}
		return mlist;
	}
	@RequestMapping("addemp.htm")
	public ModelAndView addEmployee(@ModelAttribute("EMP")Employee employee)
	{
		
		ModelAndView mv=new ModelAndView();
		Integer id;
		try {
			id = service.addEmployee(employee);
			mv.addObject("message", id);
			mv.setViewName("addEmployee");
		} catch (Exception e) {
			mv.addObject("message", "error"+e.getMessage());
			mv.setViewName("addEmployee");
		} 
		return mv;
		
	}
	
}
