package entity;

import java.sql.Date;

public class Employee {
    private int number;
    private String name;
    private String job;
    private int manager;
    private Date hireDate;
    private double salary;
    private double commissions;
    private int department;



    public Employee(int number, String name, String job, int manager, Date hireDate,
                    double salary, double commissions, int department) {

        this.number = number;
        this.name = name;
        this.job = job;
        this.manager = manager;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commissions = commissions;
        this.department = department;
    }

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getJob() { return job; }

    public void setJob(String job) { this.job = job; }

    public int getManager() { return manager; }

    public void setManager(int manager) { this.manager = manager; }

    public Date getHireDate() { return hireDate; }

    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }

    public double getSalary() { return salary; }

    public void setSalary(double salary) { this.salary = salary; }

    public double getCommissions() { return commissions; }

    public void setCommissions(double commissions) { this.commissions = commissions; }

    public int getDepartment() { return department; }

    public void setDepartment(int department) { this.department = department; }

    @Override
    public String toString() {
        return "Employee{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", manager=" + manager +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", commissions=" + commissions +
                ", department=" + department +
                '}';
    }
}
