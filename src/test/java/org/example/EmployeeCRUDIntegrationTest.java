package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EmployeeCRUDIntegrationTest {
    private Connection connection;
    private EmployeeDAO employeeDAO;


    @BeforeEach
    public void setUp() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdevops", "root", "");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        employeeDAO = new EmployeeDAO(connection);
    }


    @AfterEach
    public void tearDown() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCRUDOperations() {
        // Créer un employé
        Employee employee = new Employee("John KDoe", "john@example.com");
        employeeDAO.createEmployee(employee);

        // Lire l'employé créé

        List<Employee> mimi = employeeDAO.getAllEmployees();
        Employee retrievedEmployee = mimi.get(mimi.size()-1);
        System.out.println(employee);
        System.out.println(retrievedEmployee);

        assertNotNull(retrievedEmployee);
        assertEquals(employee.getName(), retrievedEmployee.getName());
        assertEquals(employee.getEmail(), retrievedEmployee.getEmail());

        // Mettre à jour l'employé
        retrievedEmployee.setName("khalil Smith");
        retrievedEmployee.setEmail("ok@example.com");
        employeeDAO.updateEmployee(retrievedEmployee);

        // Lire à nouveau l'employé après mise à jour
        Employee updatedEmployee = employeeDAO.getEmployeeById(retrievedEmployee.getId());
        assertNotNull(updatedEmployee);
        assertEquals(retrievedEmployee.getName(), updatedEmployee.getName());
        assertEquals(retrievedEmployee.getEmail(), updatedEmployee.getEmail());

        // Supprimer l'employé
        employeeDAO.deleteEmployee(updatedEmployee.getId());

        // Vérifier que l'employé a été supprimé
        assertNull(employeeDAO.getEmployeeById(updatedEmployee.getId()));
    }
}
