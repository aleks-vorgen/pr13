package controller;

import dao.DBFactory;
import dao.DBType;
import dao.employee.EmployeeDAO;
import entity.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Scanner;

public class EmployeeController {
    private DBFactory factory = DBFactory.getDBFactory(DBType.ORACLE);
    private EmployeeDAO dao = factory.getEmployeeDAO();


    public void getEmployees() {
        dao.getEmployeeList().forEach(System.out::println);
    }

    public void getEmployee() {
        int id = idInput();

        System.out.println(dao.getEmployee(id));
    }

    public void addEmployee() {
        Employee emp = employeeInput();

        dao.insertEmployee(emp);
    }

    public void deleteEmployee() {
        int id = idInput();

        dao.deleteEmployee(id);
    }

    public void editEmployee() {
        int id = idInput();

        Employee emp = employeeInput();

        dao.updateEmployee(id, emp);
    }

    private int idInput() {
        Scanner sc = new Scanner(System.in);
        int id;
        System.out.print("Введите номер сотрудника: ");
        id = sc.nextInt();
        return id;
    }

    private Employee employeeInput() {
        Scanner sc = new Scanner(System.in);
        int number;
        String name;
        String job;
        int manager;
        Date hireDate = null;
        double salary;
        double commissions;
        int department;
        String input;

        System.out.print("Введите номер сотрудника: ");
        input = sc.nextLine();
        number = Integer.parseInt(input);

        System.out.print("Введите имя сотрудника: ");
        name = sc.nextLine();

        System.out.print("Введите должность сотрудника: ");
        job = sc.nextLine();

        System.out.print("Введите номер менеджера сотрудника: ");
        input = sc.nextLine();
        manager = Integer.parseInt(input);

        System.out.print("Введите дату приёма сотрудника на работу (dd.mm.yyyy): ");
        input = sc.nextLine();
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        try {
            java.util.Date utilDate = format.parse(input);
            hireDate = new Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.print("Введите зарплату сотрудника: ");
        input = sc.nextLine();
        salary = Double.parseDouble(input);

        System.out.print("Введите комисионные сотрудника: ");
        input = sc.nextLine();
        commissions = Double.parseDouble(input);

        System.out.print("Введите номер департамента сотрудника: ");
        input = sc.nextLine();
        department = Integer.parseInt(input);

        return new Employee(number, name, job, manager, hireDate, salary, commissions, department);
    }
}
