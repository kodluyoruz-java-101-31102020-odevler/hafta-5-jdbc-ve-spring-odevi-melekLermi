package db.connection.mysql.connection.service;

import java.util.List;

import db.connection.mysql.connection.dao.DepartmentDAO;
import db.connection.mysql.connection.model.Department;
import db.connection.mysql.connection.model.Employee;


public class DepartmentService {

	private DepartmentDAO departmentDAO;
	
	public DepartmentService(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
	
	public List<Department> allDepartmans() {

		return this.departmentDAO.getAll();
	}

}
