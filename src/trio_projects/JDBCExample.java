package trio_projects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample {
	static final String DB_URL = "jdbc:mysql://localhost/cs157a?serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "Hanhnhan160315";
	
	/*
	 * create function for taking care of the logic of user interactions
	 * for each user function, we have to create each corresponding methods for the code clarity ( including error handlings, and console feedbacks to the console
	 * 
	 */
	
	public static void printPrompt(){
		System.out.println("Choose number of these prompt, press \"q\" to exit:" + 
			"\n 1. See available routes" + 
			"\n 2. See all the destinations shorter than 30 miles" + 
			"\n 3. See every passenger who is paying with CASH" + 
			"\n 4. See the age of every passenger who paid $120 for a ticket"  + 
			"\n 5. See every passenger name and payment method on whose trips when to Fremont" + 
			"\n 6. See where passengers over the age of 30 like to visit" + 
			"\n 7. See the avg age of people who like to go to SF" + 
			"\n 8. See how many people pay using different types of modes of payment" + 
			"\n 9. See how many people have already paid" + 
			"\n 10. See list of passenger IDs who have made registrations more than once" + 
			"\n 11. see IDs of passengers who paid more than $100 or less than $20" + 
			"\n 12. See the names of busses that have a capacity between 30 and 40" +
			"\n 13. See the avg capacity of the busses" + 
			"\n 14. See the names of people who paid in both cash and credit" + 
			"\n 15. See list of all busses"
		);
	}

	public static void handleMethod1(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from route");

			//PROMPT
			System.out.println("Result: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("Route: " + rs.getString("routeName") + ", distance =" + rs.getInt("distance") + ", destination = " + rs.getString("destination"));
			}

		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}

	public static void handleMethod3(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select passName from payment where paymode = 'Cash'");

			//PROMPT
			System.out.println("Result: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("passName: " + rs.getString("passName"));
			}

		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}

	public static void handleMethod4(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select age from passengers, payment where amountPaid > 120 and passID = pID");

			//PROMPT
			System.out.println("Result: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("Age: " + rs.getInt("age") );
			}

		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}
	public static void handleMethod5(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select passName, PayMode from Payment, Registrations where destination= 'Fremont' and registrations.passID = payment.passID");

			//PROMPT
			System.out.println("Result: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("PassName: " + rs.getString("passName") + ", Paymode: " + rs.getString("PayMode"));
			}
		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}
	public static void handleMethod6(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select pname, age, destination from registrations inner join passengers on registrations.passid = passengers.pid where age > 30");

			//PROMPT
			System.out.println("Result: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("pname: " + rs.getString("pname") + ", age: " + rs.getInt("age") + " Destination: " +  rs.getString("destination"));
			}
		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}
	public static void handleMethod7(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select avg(age) as avg from passengers, registrations where destination = 'San Francisco'");

			//PROMPT
			System.out.println("Result: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("Average age: " + rs.getInt("avg"));
			}
		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}
	public static void handleMethod8(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select paymode, count(*) from Payment group by paymode having count(*) > 1");

			//PROMPT
			System.out.println("Result: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("Paymode: " + rs.getString("paymode") + ", count: " + rs.getInt("count(*)"));
			}
		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}
	public static void handleMethod9(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select pname, passid from passengers, payment where pname = passname");

			//PROMPT
			System.out.println("Results: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("Passenger Name: " + rs.getString("pname") + ", passId: " + rs.getInt("passid"));
			}
		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}
	public static void handleMethod10(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select passID, rdate from registrations R1 where exists ( select * from registrations R2) where R1.passid = R2.passid and R1.rid <> R2.rid)");
			
			//PROMPT
			System.out.println("Results: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("passenger ID : " + rs.getInt("passID") + ", registrationDate: " + rs.getDate("rDate").toString());
			}
		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}
	public static void handleMethod11(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select passid from payment where amountpaid < 20 union select passid from payment where amountpaid > 100");

			//PROMPT
			System.out.println("Results: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("Passenger ID: " + rs.getInt("passID"));
			}
		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}
	public static void handleMethod12(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select bname, capacity from buses where capacity < 40 and capacity > 30");

			//PROMPT
			System.out.println("Results: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("Bus Name: " + rs.getString("bName") + ", capacity: " + rs.getInt("capacity"));
			}
		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}
	public static void handleMethod13(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select avg(capacity) from buses");

			//PROMPT
			System.out.println("Results: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("Average: " + rs.getInt("avg(capacity)"));
			}
		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}
	public static void handleMethod14(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select passName from payment where payMode = 'Credit' and passID = '105' and exists (select passName from payment where payMode = 'Cash' and passID = '105')");

			//PROMPT
			System.out.println("Results: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("Passenger Name: " + rs.getString("passName"));
			}
		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}
	public static void handleMethod15(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from buses");

			//PROMPT
			System.out.println("Results: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("Bus ID: " + rs.getString("bID") + ", bus name: " + rs.getString("bName") + rs.getInt("capacity"));
			}
		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}

	public static void handleMethod2(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select destination, distance from route where distance < 30");

			//PROMPT
			System.out.println("Result: ");

			// STEP 5: Process the results
			while (rs.next()) {
				System.out.println("Destination: " + rs.getString("destination") + ", distance =" + rs.getString("distance"));
			}

		} catch (SQLException se) {se.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
			} // nothing we can do
		} // end try
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}catch (Exception e){
			System.out.println(e);
		}
		
		printPrompt();
		String userInput = sc.nextLine().toString().strip();
		
		while(userInput.charAt(0) != 'q') {
			switch (userInput) {
				case "1":
					handleMethod1(conn);
					break;
				case "2":
					handleMethod2(conn);
					break;
				case "3":
					handleMethod3(conn);
					break;
				case "4":
					handleMethod4(conn);
					break;
				case "5":
					handleMethod5(conn);
					break;
				case "6":
					handleMethod6(conn);
					break;
				case "7":
					handleMethod7(conn);
					break;
				case "8":
					handleMethod8(conn);
					break;
				case "9":
					handleMethod9(conn);
					break;
				case "10":
					handleMethod10(conn);
					break;
				case "11":
					handleMethod11(conn);
					break;
				case "12":
					handleMethod12(conn);
					break;
				case "13":
					handleMethod13(conn);
					break;
				case "14":
					handleMethod14(conn);
					break;
				default:
					handleMethod15(conn);
					break;
			}
			printPrompt();
			userInput = sc.nextLine().toString().strip();
		}

		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} // end finally try
		
		
		System.out.println("Goodbye!");
	}// end main
}
