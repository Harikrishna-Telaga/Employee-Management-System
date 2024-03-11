package flm.EmployeeManagementSystem;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App {
	public static void main(String[] args) {

		Session Session = sessionFactory();

		System.out.println("----------------------------------------");
		System.out.println("Welcome to Employee Management Sysytem !");
		System.out.println("----------------------------------------");

		Scanner sc = new Scanner(System.in);

		boolean running = true;
		while (running) {
			System.out.println("----------------------------------------");
			System.out.println("       Select the Below Options ");
			System.out.println("----------------------------------------");
			System.out.println("1 Add Employee");
			System.out.println("2 Update Employee");
			System.out.println("3 Delete Employee");
			System.out.println("4 Search Employee");
			System.out.println("5 Retrieve all Employees");
			System.out.println("6 Exit App ");

			int option = sc.nextInt();
			switch (option) {
			case 1:
				addEmployee(sc);

				break;
			case 2:
				UpdateEmployee(sc);

				break;
			case 3:
				DeleteEmployee(sc);

				break;
			case 4:
				SearchEmployee(sc);

				break;
			case 5: {
				RetrieveAllEmployees();
			}
				break;
			case 6:
				System.out.println("Exiting.....");
				running = false;

				break;
			default:
				System.out.println("invalid option !!!");
			}

		}

	}

	private static void RetrieveAllEmployees() {
		Session Session = sessionFactory();
		Session.beginTransaction();
		Query query = Session.createQuery("from Employee");
		List list = query.list();
		if (list != null) {
			for (Object object : list) {
				System.out.println(object);
			}
		}
	}

	private static void SearchEmployee(Scanner sc) {
		System.out.print("Enter Employee ID: ");
		int id = sc.nextInt();
		Session Session = sessionFactory();
		Session.beginTransaction();
		Employee employee = Session.get(Employee.class, id);
		if (employee != null) {
			System.out.println(employee);
		} else {
			System.out.println("Employee not found !!!");
		}
	}

	private static void DeleteEmployee(Scanner sc) {
		System.out.print("Enter Employee ID: ");
		int id = sc.nextInt();
		Session Session = sessionFactory();
		Session.beginTransaction();
		Employee employee = Session.get(Employee.class, id);
		if (employee != null) {
			System.out.println(employee);
			Session.delete(employee);
			Session.getTransaction().commit();
			System.out.println("Employee Deleted Successfully");
		} else {
			System.out.println("Employee not found !!!");
		}
	}

	private static void UpdateEmployee(Scanner sc) {
		System.out.print("Enter employee ID: ");
		int id = sc.nextInt();
		System.out.print("Enter new name: ");
		String name = sc.next();
		System.out.print("Enter new position: ");
		String position = sc.next();
		System.out.print("Enter new salary: ");
		double salary = sc.nextDouble();

		Session Session = sessionFactory();
		Session.beginTransaction();
		Employee employee = Session.get(Employee.class, id);
		if (employee != null) {
			employee.setName(name);
			employee.setPosition(position);
			employee.setSalary(salary);
			Session.update(employee);
			Session.getTransaction().commit();
			System.out.println("Employee details Updated Successfully");

		} else {
			System.out.println("Employee not found !!");
		}
	}

	private static void addEmployee(Scanner sc) {

		System.out.println("Enter Details to Add Employee ");
		System.out.print("Enter Employee name: ");
		String name = sc.next();
		System.out.print("Enter Employee Position: ");
		String position = sc.next();
		System.out.print("Enter Employee Salary: ");
		double salary = sc.nextDouble();

		Session Session = sessionFactory();
		Session.beginTransaction();
		Employee employee = new Employee(name, position, salary);
		Session.save(employee);
		Session.getTransaction().commit();
		System.out.println("Employee added Successfully");

	}

	private static Session sessionFactory() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Employee.class);

		org.hibernate.SessionFactory sf = cfg.buildSessionFactory();
		Session Session = sf.openSession();
		return Session;
	}

}
