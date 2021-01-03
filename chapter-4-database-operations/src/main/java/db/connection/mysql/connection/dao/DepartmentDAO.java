package db.connection.mysql.connection.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import db.connection.mysql.connection.DbSQLQuery;
import db.connection.mysql.connection.model.Department;


public class DepartmentDAO {

	private static final Logger logger = Logger.getLogger(DepartmentDAO.class);

	
	public List<Department> getAll() {
		
		List<Department> departments = new ArrayList<Department>();
		
		ResultSet resultSet = DbSQLQuery.select("<Bu SQL sorgusunu oluştur>");
		
		try {
		
			if(resultSet==null) {
				return departments;
			}
			
			while(resultSet.next()) {
				
				Department depart = createDepartment(resultSet);
				departments.add(depart);
				
			}
			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return departments;
	}


	private Department  createDepartment(ResultSet resultSet) throws SQLException {
		
		Department department=new Department("","");
		department.setName(resultSet.getString("dept_name"));
		department.setDeptNo(resultSet.getString("dept_no"));
		
		return department;
	}
	
}
