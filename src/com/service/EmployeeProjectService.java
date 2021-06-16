package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bean.Employee;
import com.bean.Project;
import com.dao.EmployeeProjectDao;
import com.entity.ProjectEntity;
import com.exception.NoResourceException;
import com.exception.SkillMismatchException;
@Component
public class EmployeeProjectService {
	@Autowired
	EmployeeProjectDao dao;
	
	
public Integer addEmployee(Employee employee) throws SkillMismatchException, NoResourceException
{
	Integer id=0;
	Project project=dao.getProjectDetails(employee.getEmployeeName());
	if(!(employee.getSkill().equalsIgnoreCase(project.getTechnology())))
			throw new SkillMismatchException();
	
	
		if(project.getResourceCount()>=10)
			throw new NoResourceException();
	
	else
	{
		 id=dao.addEmployee(employee);
	}
	return id;
}

public List<String> getAllProjects()
{
	List<String> list=dao.getAllProjects();
	return list;
}
}
