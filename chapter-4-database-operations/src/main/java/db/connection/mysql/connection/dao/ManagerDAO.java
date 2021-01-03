package db.connection.mysql.connection.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.connection.mysql.connection.DbSQLQuery;
import db.connection.mysql.connection.model.Department;
import db.connection.mysql.connection.model.Employee;
import db.connection.mysql.connection.model.Manager;

public class ManagerDAO {

	public List<Manager> loadAllActiveManagers() {
		
		List<Manager> managers = new ArrayList<Manager>();

		String sql = "select emp.*, dm.*, dp.* from employees emp left join dept_manager dm on dm.emp_no = emp.emp_no left join departments dp on dp.dept_no = dm.dept_no where dm.to_date >= NOW();";
		ResultSet resultSet = DbSQLQuery.select(sql);

		try {

			if (resultSet == null) {
				return managers;
			}

			while (resultSet.next()) {

				Employee employee = createEmployee(resultSet);
				Manager manager = new Manager(employee, createDepartment(resultSet).toString());
				managers.add(manager);
			}

		} catch (Exception e) {
			System.out.println("Error");
		}		
			
		return managers;
	}

	private Employee createEmployee(ResultSet resultSet) throws SQLException {

		Employee employee = new Employee();
		employee.setId(resultSet.getLong("emp_no"));
		employee.setName(resultSet.getString("first_name"));
		employee.setLastName(resultSet.getString("last_name"));
		employee.setGender(resultSet.getString("gender"));
		employee.setBirthDate(resultSet.getDate("birth_date"));
		employee.setHireDate(resultSet.getDate("hire_date"));

		return employee;
	}
	
	public Department createDepartment(ResultSet resultSet) throws SQLException, SQLException {
	
		Department department = new Department();
		department.setName(resultSet.getString("dept_name"));
		department.setDeptNo(resultSet.getString("dept_no"));

		return department;

	}
	
	
	
	
	
}
