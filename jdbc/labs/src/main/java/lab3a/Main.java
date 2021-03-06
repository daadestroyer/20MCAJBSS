package lab3a;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Service service = new Service();
		int ch = 0;

		Student stud = new Student();

		while (ch != 5) {
			System.out.println("Main Menu for Student Data Entry");
			System.out.println("-------------------------------------");
			System.out.println("1. Add student details");
			System.out.println("2. Update student details");
			System.out.println("3. Delete student details");
			System.out.println("4. Display all the student details");
			System.out.println("5. Search Student");
			System.out.println("6. EXIT");
			System.out.print("Enter your choice : " + "\n");
			System.out.println("--------------------------------------");
			ch = sc.nextInt();

			if (ch == 1) {
				System.out.println("Adding student details");
				System.out.print("Enter student id : ");
				stud.setId(sc.nextInt());
				System.out.print("Enter student first name : ");
				stud.setFirstname(sc.next());
				System.out.print("Enter student last name : ");
				stud.setLastname(sc.next());
				System.out.print("Enter student age : ");
				stud.setAge(sc.nextInt());
				System.out.print("Enter student emails : ");
				stud.setEmail(sc.next());

				service.addStudent(stud);
				 

			} else if (ch == 2) {
				System.out.println("Updating student details");
				System.out.print("Enter student no to be updated : ");
				stud.setId(sc.nextInt());
				System.out.print("Enter email id of student : ");
				stud.setEmail(sc.next());
				service.myExecuteQry(
						"update student set email = '" + stud.getEmail() + "' where id = " + stud.getId() + "");
				System.out.println("Record updated successfully...");
			} else if (ch == 3) {
				System.out.println("Deleting student details");
				System.out.print("Enter stud no to be deleted : ");
				stud.setId(sc.nextInt());
				service.myExecuteQry("delete from student where id = " + stud.getId() + "");
				System.out.println();
				System.out.println("Record deleted successfully...");
				System.out.println();
			} else if (ch == 4) {
				System.out.println("Displaying student details");
				ResultSet r = service.getResultSet("Select * from student");

				while (r.next()) {
					System.out.println("---------------------------------------");
					System.out.println("ID : " + r.getInt("id"));
					System.out.println("First Name : " + r.getString("first"));
					System.out.println("Last Name : " + r.getString("last"));
					System.out.println("Age : " + r.getInt("age"));
					System.out.println("Email : " + r.getString("email"));
					System.out.println();
				}
			} else if (ch == 5) {
				System.out.println("Enter student id to searsch");
				stud.setId(sc.nextInt());
				ResultSet r = service.getResultSet("select * from student where id = " + stud.getId());
				if (r.next()) {
					System.out.println("---------------------------------------");
					System.out.println("ID : " + r.getInt("id"));
					System.out.println("First Name : " + r.getString("first"));
					System.out.println("Last Name : " + r.getString("last"));
					System.out.println("Age : " + r.getInt("age"));
					System.out.println("Email : " + r.getString("email"));
					System.out.println();
				} else {
					System.out.println("Record not found");
				}
			} else if (ch == 6) {
				System.exit(0);
			}
		}
	}
}
