package flm.EmployeeManagementSystem;

import javax.persistence.Entity;

import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	private int id;
	private String name;
	private String position;
	private double Salary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getSalary() {
		return Salary;
	}

	public void setSalary(double salary) {
		Salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", position=" + position + ", Salary=" + Salary + "]";
	}

	public Employee(String name, String position, double salary) {

		this.name = name;
		this.position = position;
		Salary = salary;
	}

	public Employee() {

	}
}
