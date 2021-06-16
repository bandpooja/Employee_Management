package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.bean.Employee;
import com.bean.Project;
import com.entity.EmployeeEntity;
import com.entity.ProjectEntity;
@Component
public class EmployeeProjectDao {
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Integer addEmployee(Employee employee)
	{
		EmployeeEntity entity=new EmployeeEntity();
		entity.setEmployeeId(employee.getEmployeeId());
		entity.setEmployeeName(employee.getEmployeeName());
		entity.setGender(employee.getGender());
		entity.setSkill(employee.getSkill());
		ProjectEntity projectEntity=em.find(ProjectEntity.class,employee.getProjectId());
		entity.setProject(projectEntity);
		em.merge(entity);
		entity.getProject().setResourceCount(entity.getProject().getResourceCount()+1);
		
		Integer id=entity.getEmployeeId();
		return id;
	}
	
	public Project getProjectDetails(String projectId)
	{
		ProjectEntity entity=em.find(ProjectEntity.class,projectId);
		Project project=new Project();
		project.setProjectId(entity.getProjectId());
		project.setResourceCount(entity.getResourceCount());
		project.setTechnology(entity.getTechnology());
		
		return project;
		
		
		
	}
	
	public List<String> getAllProjects()
	{
		Query query=em.createQuery("select p.projectId from ProjectEntity p");
		List<String> list=query.getResultList();
		return list;
		
		
	}
	
	public int getEmployeeStatus(Integer empId)
	{
		int result=-1;
		EmployeeEntity employeeEntity=em.find(EmployeeEntity.class, empId);
		if(employeeEntity==null)
			result=0;
		else
		{
			if(employeeEntity.getProject()==null)
				result=1;
		}
		return result;
	}
	
	
	

}
