
package crud.system;
import java.util.Scanner;


public class CRUDSystem {   
    public static void main(String[] args) {

        new LoginForm(); // فتح شاشة اللوجين
    }
}

    /*
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Login ===");
        System.out.print("Username: ");
        String username = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();

        if (LoginDAO.login(username, password)) {
            System.out.println("Login Success");

            while (true) {
                System.out.println("\n1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");

                int choice = input.nextInt();

                if (choice == 1) {
                    System.out.print("Name: ");
                    String name = input.next();
                    System.out.print("Email: ");
                    String email = input.next();
                    System.out.print("Age: ");
                    int age = input.nextInt();

                    StudentDAO.addStudent(new Student(name, email, age));

                } else if (choice == 2) {
                    StudentDAO.viewStudents();

                } else if (choice == 3) {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    System.out.print("New Name: ");
                    String name = input.next();
                    System.out.print("New Email: ");
                    String email = input.next();
                    System.out.print("New Age: ");
                    int age = input.nextInt();

                    StudentDAO.updateStudent(id, name, email, age);

                } else if (choice == 4) {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    StudentDAO.deleteStudent(id);

                } else if (choice == 5) {
                    break;
                }
            }

        } else {
            System.out.println("Wrong username or password");
        }
    }
}*/