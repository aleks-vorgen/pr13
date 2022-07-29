package dao.employee;

import entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getEmployeeList();
    Employee getEmployee(int id);
    void insertEmployee(Employee employee);
    void updateEmployee(int id, Employee employee);
    void deleteEmployee(int id);
}
