package dao;

import dao.employee.EmployeeDAO;

public abstract class DBFactory {
    public abstract EmployeeDAO getEmployeeDAO();

    public static DBFactory getDBFactory(DBType type) {
        switch (type) {
            case ORACLE:
                return new OracleFactory();
            case MYSQL:
            default:
                return null;
        }
    }
}
