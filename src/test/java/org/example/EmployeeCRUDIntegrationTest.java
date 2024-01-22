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
   public Service service = new Service();

    public EmployeeCRUDIntegrationTest() throws SQLException {
    }


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
    public void testCRUDOperations() throws AttributeValidationException {
        // Créer un employé
        Employee employee = new Employee("John KDoe", "john@example.com");
        employeeDAO.createEmployee(employee);

        // Lire l'employé créé

        List<Employee> list = employeeDAO.getAllEmployees();
        Employee retrievedEmployee = list.get(list.size()-1);
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
    @Test
    public void testCreateEmployee() {
Employee employee=new Employee();
try{
    employee=service.create(null,null);
    List<Employee> employees = employeeDAO.getAllEmployees();
    Employee retrievedEmployee = employees.get(employees.size() - 1);
    assertNotNull(retrievedEmployee);
    assertEquals(employee.getName(), retrievedEmployee.getName());
    assertEquals(employee.getEmail(), retrievedEmployee.getEmail());}
catch (AttributeValidationException e)
{
    System.out.println(e.getMessage());
}
    }
    @Test
    public void testCreateEmployeeWithNullValues() {
        try {
            service.create(null, null);
            fail("Une exception AttributeValidationException aurait dû être déclenchée avec des valeurs nulles.");
        } catch (AttributeValidationException e) {
         assertEquals("Name ne peut pas être null.", e.getMessage());        }
    }
    @Test
    public void testCreateEmployeeWithValidData() {
        Employee employee = new Employee("John Doe", "john@example.com");

            Employee createdEmployee = service.create("John Doe", "john@example.com");
            assertNotNull(createdEmployee);
            List<Employee> employees = employeeDAO.getAllEmployees();
            Employee retrievedEmployee = employees.get(employees.size() - 1);
            assertNotNull(retrievedEmployee);
            assertEquals(employee.getName(), retrievedEmployee.getName());
            assertEquals(employee.getEmail(), retrievedEmployee.getEmail());

    }

    @Test
    public void testUpdateEmployee()  {

        List<Employee> list = employeeDAO.getAllEmployees();
        AttributeValidationException.validateNotNull(list,"la liste des employees");
        Employee retrievedEmployee = list.get(list.size()-1);
        //testNom
        retrievedEmployee.setName("Khalil Smith");
        AttributeValidationException.validateNotNull(retrievedEmployee.getName(), "name");
        //TestEmail
        retrievedEmployee.setEmail("ok@example.com");
        AttributeValidationException.validateNotNull(retrievedEmployee.getEmail(), "email");

        employeeDAO.updateEmployee(retrievedEmployee);

        // Vérifier la mise à jour de l'employé
        Employee updatedEmployee = employeeDAO.getEmployeeById(retrievedEmployee.getId());
        assertNotNull(updatedEmployee);
        assertEquals(retrievedEmployee.getName(), updatedEmployee.getName());
        assertEquals(retrievedEmployee.getEmail(), updatedEmployee.getEmail());
    }

    @Test
    public void testDeleteEmployee() throws AttributeValidationException {
        List<Employee> list = employeeDAO.getAllEmployees();
        AttributeValidationException.validateNotNull(list,"la liste des employees");

        Employee retrievedEmployee = list.get(list.size()-1);
        employeeDAO.deleteEmployee(retrievedEmployee.getId());

        // Vérifier que l'employé a été supprimé
        assertNull(employeeDAO.getEmployeeById(retrievedEmployee.getId()));
    }
}
