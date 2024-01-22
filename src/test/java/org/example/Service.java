package org.example;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class Service {
    Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdevops", "root", "");
  EmployeeDAO employeeDAO=new EmployeeDAO(connection);

    public Service() throws SQLException {
    }

    Employee create (String nom,String email)
    {
        Employee e = new Employee( nom,email);
        AttributeValidationException.validateNotNull(nom,"Name");
        AttributeValidationException.validateNotNull(email,"email");
        employeeDAO.createEmployee(e);
        return  e;
    }
    public Employee update(int id,String nom,String email)
    {
        Employee e = employeeDAO.getEmployeeById(id);
        e.setEmail(email);
        e.setName(nom);
        employeeDAO.updateEmployee(e);
        return e;
    }
}
