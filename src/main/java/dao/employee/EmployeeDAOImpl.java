package dao.employee;

import dao.OracleFactory;
import entity.Employee;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private static final String SELECT_EMPLOYEES =
            "SELECT * FROM emp";

    private static final String SELECT_EMPLOYEE =
            "SELECT * FROM emp WHERE empno = ?";

    private static final String UPDATE_EMPLOYEE =
            "UPDATE emp" +
            " SET empno = :1, ename = :2, job = :3, mgr = :4," +
            " hiredate = :5, sal = :6, comm = :7, deptno = :8" +
            "WHERE empno = :9";

    private static final String INSERT_EMPLOYEE =
            "INSERT INTO emp" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_EMPLOYEE =
            "DELETE emp WHERE empno = ?";

    @Override
    public List<Employee> getEmployeeList() {

        List<Employee> employeeList = new ArrayList<>();

        try (PreparedStatement preparedStatement = OracleFactory.connect()
                .prepareStatement(SELECT_EMPLOYEES)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                employeeList.add(parseEmployee(rs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    @Override
    public Employee getEmployee(int id) {
        Employee emp = null;
        try (PreparedStatement preparedStatement = OracleFactory.connect()
                .prepareStatement(SELECT_EMPLOYEE)) {
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                emp = parseEmployee(rs);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emp;
    }

    private Employee parseEmployee(ResultSet rs) {
        Employee emp = null;

        try {
            int number = rs.getInt("empno");
            String name = rs.getString("ename");
            String job = rs.getString("job");
            int manager = rs.getInt("mgr");
            Date hireDate = rs.getDate("hiredate");
            double salary = rs.getDouble("sal");
            double commissions = rs.getDouble("comm");
            int department = rs.getInt("deptno");
            emp = new Employee(number, name, job, manager, hireDate, salary, commissions, department);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emp;
    }

    @Override
    public void insertEmployee(Employee employee) {
        try (PreparedStatement preparedStatement = OracleFactory.connect()
                .prepareStatement(INSERT_EMPLOYEE)) {

            preparedStatement.setInt(1, employee.getNumber());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getJob());
            preparedStatement.setInt(4, employee.getManager());
            preparedStatement.setDate(5, employee.getHireDate());
            preparedStatement.setDouble(6, employee.getSalary());
            preparedStatement.setDouble(7, employee.getCommissions());
            preparedStatement.setInt(8, employee.getDepartment());

            preparedStatement.execute();
            System.out.println("Employee was successfully inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(int id, Employee employee) {
        try (PreparedStatement preparedStatement = OracleFactory.connect()
            .prepareStatement(UPDATE_EMPLOYEE)) {

            preparedStatement.setInt(1, employee.getNumber());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getJob());
            preparedStatement.setInt(4, employee.getManager());
            preparedStatement.setDate(5, employee.getHireDate());
            preparedStatement.setDouble(6, employee.getSalary());
            preparedStatement.setDouble(7, employee.getCommissions());
            preparedStatement.setInt(8, employee.getDepartment());
            preparedStatement.setInt(9, id);

            preparedStatement.execute();
            System.out.println("Employee was successfully updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try (PreparedStatement preparedStatement = OracleFactory.connect()
                .prepareStatement(DELETE_EMPLOYEE)) {

            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            System.out.println("Employee was successfully deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
