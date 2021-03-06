package lab3a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Service {
	Connection cn;
	Statement st;
	PreparedStatement pst;
	String driverPath = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/rvce";
	String username = "root";
	String password = "root";
	
	Service(){
		try {
			Class.forName(driverPath);
			cn = DriverManager.getConnection(url, username, password);
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	public void myExecuteQry(String s) {
		try {
			st = cn.createStatement();
			int executeUpdate = st.executeUpdate(s);
			 
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
	}
	public ResultSet getResultSet(String s) {
		ResultSet r;
		try {
			st = cn.createStatement();
			r = st.executeQuery(s);
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addStudent(Student stud) throws SQLException {
		
		 pst = cn.prepareStatement("select * from student where id = "+stud.getId());
		 ResultSet rs = pst.executeQuery();
		 boolean b = rs.next();
		 if(b==true) {
			 System.out.println("----------------------------");
			 System.out.println("student already present");
			 System.out.println("----------------------------");
		 }
		 else {
			 pst = cn.prepareStatement("insert into student values(?,?,?,?,?)");
				
				pst.setInt(1, stud.getId());
				pst.setString(2, stud.getFirstname());
				pst.setString(3, stud.getLastname());
				pst.setInt(4, stud.getAge());
				pst.setString(5,stud.getEmail());
				
				int executeUpdate = pst.executeUpdate();
				if(executeUpdate>0) {
					System.out.println("----------------------------");
					System.out.println("record inserted");
					System.out.println("----------------------------");
				}
				else {
					System.out.println("----------------------------");
					System.out.println("record not inserted");
					System.out.println("----------------------------");
				}
		 }
		 
	}
	
	public void updateStudent(Student stud) {
		String q = "update studend set first = '" + stud.getFirstname() + "' where id = " + stud.getId() + "";
		myExecuteQry(q);
	}
	
	public void deleteStudent(Student stud) {
		String q = "delete from student where id = " + stud.getId() + "";
		myExecuteQry(q);
	}
	
	public void displayStudent() throws SQLException {
		ResultSet r = getResultSet("Select * from stud");
		while (r.next()) {
			System.out.println("ID : "+r.getInt("id"));
			System.out.println("First Name : "+r.getString("first"));
			System.out.println("Last Name : "+r.getString("last"));
			System.out.println("Age : "+r.getInt("age"));
			System.out.println("Email : "+r.getString("email"));
			System.out.println();
		}
	}
}
