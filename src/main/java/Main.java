import controller.EmployeeController;
import dao.OracleFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EmployeeController employeeController = new EmployeeController();

        while (true) {
            Scanner sc = new Scanner(System.in);
            int choice;
            System.out.println("1. Показать всех сотрудников");
            System.out.println("2. Показать информацию про сотрудника");
            System.out.println("3. Добавить сотрудника");
            System.out.println("4. Удалить сотрудника");
            System.out.println("5. Редактировать сотрудника");
            System.out.println("6. Выход");
            System.out.print("Ввод: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> employeeController.getEmployees();
                case 2 -> employeeController.getEmployee();
                case 3 -> employeeController.addEmployee();
                case 4 -> employeeController.deleteEmployee();
                case 5 -> employeeController.editEmployee();
                case 6 -> {
                    OracleFactory.disconnect();
                    sc.close();
                    return;
                }
                default -> System.out.println("Такого пункта не существует\n");
            }
        }
    }
}
