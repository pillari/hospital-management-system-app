package hms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.protocol.SocksProxySocketFactory;

public class HmsApp {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
//		App a = new App();

		boolean flag = true;
		while (flag) {

			System.out.println(" ==== Select Option ===");
			System.out.println("enter 1) : Register patient");
			System.out.println("enter 2) : View all patients");
			System.out.println("enter 3) : View by id ");
			System.out.println("enter 4) :Search by City");
			System.out.println("enter 5) :Search by Name of patients ");
			System.out.println("enter 6) :Search by  Admisiion Date");
			System.out.println("enter 7) : Update ");
			System.out.println("enter 8) :Delete ");
			System.out.println("enter 9) to Exit");

			int key = sc.nextInt();
			switch (key) {
			case 1:
				registerPatient();
				break;
			case 2:
				viewAllPatients();
				break;
			case 3:
				viewPatientById();
				break;
			case 4:
				searchByCity();
				break;
			case 5:
				searchByName();
				break;
			case 6:
				searchByAdmissionDate();
				break;
			case 7:
				updatePatient();
				break;
			case 8:
				deletePatient();
				break;
			case 9: {
				flag = false;
				break;

			}
			default:
				System.out.println("enter valid number");

			}
		}
	}

	public static void registerPatient() {

		System.out.println("enter id to register");
		int id = sc.nextInt();
		System.out.println("enter patient name");
		String name = sc.next();
		System.out.println("enter phone number");
		long phoneno = sc.nextLong();
		System.out.println("enter city name");
		String city = sc.next();
		System.out.println("enter date of admission");
		String date = sc.next();
		System.out.println("enter description");
		String desc = sc.next();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "root");
			PreparedStatement ps = con.prepareStatement("insert into patientportal values(?,?,?,?,?,?)");

			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setLong(3, phoneno);
			ps.setString(4, city);
			ps.setDate(5, Date.valueOf(date));
			ps.setString(6, desc);
			int row = ps.executeUpdate();
			System.out.println(row + "row inserted");
			ps.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static void viewAllPatients() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from patientportal");

			ResultSet rs = ps.executeQuery();
			System.out.println("record exists");

			if (rs.next() == true) {
				System.out.print(rs.getInt(1) + ", ");
				System.out.print(rs.getString(2) + ", ");
				System.out.print(rs.getLong(3) + ", ");
				System.out.print(rs.getString(4) + ", ");
				System.out.print(rs.getDate(5) + ", ");
				System.out.print(rs.getString(6) + ", \n");
			}

			ps.executeQuery();
			rs.close();
			ps.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public static void viewPatientById(){
		System.out.println("enter id to retrive the data");
		int id = sc.nextInt();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from patientportal where id =?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			System.out.println("record exists");

			if (rs.next() == true) {
				System.out.print(rs.getInt(1) + ", ");
				System.out.print(rs.getString(2) + ", ");
				System.out.print(rs.getLong(3) + ", ");
				System.out.print(rs.getString(4) + ", ");
				System.out.print(rs.getDate(5) + ", ");
				System.out.print(rs.getString(6) + ", \n");
			
			}
			ps.executeQuery();
			rs.close();
			ps.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	
	public static void deletePatient() {
		System.out.println("enter id to delete the data");
		int id = sc.nextInt();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "root");
			PreparedStatement ps = con.prepareStatement("delete from patientportal where id =?");
			ps.setInt(1, id);

			int row = ps.executeUpdate();
			System.out.println(row + "row deleted");

			
			ps.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void searchByCity() {
		System.out.println("enter city to retrive the data");
		String city= sc.next();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from patientportal where city =?");
			ps.setString(1, city);

			ResultSet rs = ps.executeQuery();
			System.out.println("record exists");

			if (rs.next() == true) {
				System.out.print(rs.getInt(1) + ", ");
				System.out.print(rs.getString(2) + ", ");
				System.out.print(rs.getLong(3) + ", ");
				System.out.print(rs.getString(4) + ", ");
				System.out.print(rs.getDate(5) + ", ");
				System.out.print(rs.getString(6) + ", \n");
			
			}
			ps.executeQuery();
			rs.close();
			ps.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static void searchByName() {
		System.out.println("enter name  to retrive the data");
		String name= sc.next();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from patientportal where name =?");
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			System.out.println("record exists");

			if (rs.next() == true) {
				System.out.print(rs.getInt(1) + ", ");
				System.out.print(rs.getString(2) + ", ");
				System.out.print(rs.getLong(3) + ", ");
				System.out.print(rs.getString(4) + ", ");
				System.out.print(rs.getDate(5) + ", ");
				System.out.print(rs.getString(6) + ", \n");
			
			}
			ps.executeQuery();
			rs.close();
			ps.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public static void searchByAdmissionDate() {
			System.out.println("enter admission date to retrive the data");
			String date= sc.next();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "root");
				PreparedStatement ps = con.prepareStatement("select * from patientportal where date =?");
				ps.setString(1, date);

				ResultSet rs = ps.executeQuery();
				System.out.println("record exists");

				if (rs.next() == true) {
					System.out.print(rs.getInt(1) + ", ");
					System.out.print(rs.getString(2) + ", ");
					System.out.print(rs.getLong(3) + ", ");
					System.out.print(rs.getString(4) + ", ");
					System.out.print(rs.getDate(5) + ", ");
					System.out.print(rs.getString(6) + ", \n");
				
				}
				ps.executeQuery();
				rs.close();
				ps.close();
				con.close();

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	}
	public static void updatePatient() {
		System.out.println("enter the id to update");
		
		int id = sc.nextInt();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from patientportal where id =?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				boolean flag = true;
				while(flag) {
					System.out.println("enter choose number to update");
			System.out.println("enter 1 to update new  name ");
			System.out.println("enter 2 to update new  phone number ");
			System.out.println("enter 3 to update new city name ");
			System.out.println("enter 4 to update new admission date ");
			System.out.println("enter 5 to update new descption ");
			int key = sc.nextInt();

		switch(key) {
		case 1:{ System.out.println("enter new name to update");
				String name = sc.next();
			 ps = con.prepareStatement("update patientportal set name=? where id =?");
			ps.setString(1, name);
			ps.setInt(2, id);
			
			int row = ps.executeUpdate();
			System.out.println("new name updated");
			break;
		}
		case 2: { System.out.println("enter new phoneno to update");
					int phone = sc.nextInt();
					ps = con.prepareStatement("update patientportal set phone=? where id =?");
					ps.setInt(1, phone);
					ps.setInt(2, id);
							
					int row = ps.executeUpdate();
					System.out.println("new phone number updated");
					break;
					}
		
		case 3 : { System.out.println("enter new city name to update");
		String city = sc.next();
		ps = con.prepareStatement("update patientportal set city=? where id =?");
		ps.setString(1, city);
		ps.setInt(2, id);
				
		int row = ps.executeUpdate();
		System.out.println("new city name updated");
		break;

		}
		case 4 : { System.out.println("enter new  date to update");
		String date = sc.next();
		ps = con.prepareStatement("update patientportal set date=? where id =?");
		ps.setDate(1,Date.valueOf(date));
		ps.setInt(2, id);
				
		int row = ps.executeUpdate();
		System.out.println("new  Admission date  updated");
		break;

		}
		case 5 : { System.out.println("enter new  description to update");
		String desciption = sc.next();
		ps = con.prepareStatement("update patientportal set description =? where id =?");
		ps.setString(1, desciption);
		ps.setInt(2, id);
				
		int row = ps.executeUpdate();
		System.out.println("new  decription  updated");
		break;

		}
		default: System.out.println("invalid choice to update");
		
		}
		ps.close();
		con.close();
		
		}}}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
		
		
	}
	
	}
