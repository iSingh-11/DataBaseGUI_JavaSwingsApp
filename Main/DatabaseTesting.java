package Main;
import java.sql.*;
import java.util.ArrayList;
public class DatabaseTesting 
{
	Connection con;
	PreparedStatement ps;
	static String MySQL="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
	static String MySQLDriver="com.mysql.cj.jdbc.Driver";
	static String Oracle="jdbc:oracle:thin:@localhost:1521/xe";
	static String OracleDriver="oracle.jdbc.driver.OracleDriver";
	String active;
	public DatabaseTesting(String database,String databaseDriver,String username,String password) throws Exception
	{
		active=database;
		Class.forName(databaseDriver);
		System.out.println("Driver has been registered");
			
		con=DriverManager.getConnection(database,username,password);
		System.out.println("Connection has been set up");
	}
	public void createNew(String query) throws Exception
	{
		System.out.println("\nFetching database for creation...");
		ps=con.prepareStatement(query);
		System.out.println("Executing query...");
		int t=ps.executeUpdate();
		if(t==1)
		{
			System.out.println("Creation Successful :)");
		}
		else
		{
		 	System.out.println("Creation Failed :(");
		}
	}
	public void insert(String tablename,Object d1,Object d2,Object d3)
	{
		try
		{
			System.out.println("\nFetching database for insertion...");
			ps=con.prepareStatement("insert into "+tablename+" values(?,?,?)");
		 	ps.setObject(1,d1);
		 	ps.setObject(2,d2);
		 	ps.setObject(3,d3);
		 	System.out.println("Executing query...");
		 	int t=ps.executeUpdate();
		 	if(t==1)
		 	{
		 		System.out.println("Insertion Successful :)");
		 	}
		 	else
		 	{
		 		System.out.println("Insertion Failed :(");
		 	}
		}
		catch(SQLException e)
		{
		 e.printStackTrace();
		}
	}
	public void delete(String tablename,String columnname,Object d1)
	{
		try
		{
			System.out.println("\nFetching database for deletion...");
			ps=con.prepareStatement("delete from "+tablename+" where "+columnname+" = ?");
			ps.setObject(1,d1);
			System.out.println("Executing query...");
			int t=ps.executeUpdate();
			if(t==1)
		 	{
		 		System.out.println("Deletion Successful :)");
		 	}
		 	else
		 	{
		 		System.out.println("Deletion Failed :(");
		 	}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void update(String tablename,String col1,String col2,Object d1,Object d2)
	{
		try
		{
			System.out.println("\nFetching database for updation...");
			ps=con.prepareStatement("update "+tablename+" set "+col1+" = ? where "+col2+" = ?");
		 	ps.setObject(1,d1);
		 	ps.setObject(2,d2);
		 	System.out.println("Executing query...");
		 	int t=ps.executeUpdate();
		 	if(t==1)
		 	{
		 		System.out.println("Updation Successful :)");
		 	}
		 	else
		 	{
		 		System.out.println("Updation Failed :(");
		 	}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void display(String tablename)
	{
		try
		{
			System.out.println("\nFetching database for selection...");
			ps=con.prepareStatement("select * from "+tablename);
			System.out.println("Executing query...");
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next())
			{  
				System.out.println("Row "+i+": "+rs.getObject(1)+" "+rs.getObject(2)+" "+rs.getObject(3));
				i++;
			}
			if(i==0) System.out.println("No data found in table '"+tablename+"'");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public ArrayList<Object> showAll()
	{
		try
		{
			ArrayList<Object> v=new ArrayList<Object>();
			System.out.println("\nFetching database...");
			if(active==Oracle)
			{
				ps=con.prepareStatement("select * from tab");
			}
			else
			{
				ps=con.prepareStatement("SHOW TABLES");
			}
			System.out.println("Executing query...");
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next())
			{
				v.add(rs.getObject(1));
				System.out.println(rs.getObject(1));
				i++;
			}
			if(i==0) System.out.println("No table found in database ");
			return v;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/*public static void main(String[] args) 
	{
		try
		{
			DatabaseTesting d=new DatabaseTesting(Oracle,OracleDriver,"GURIQBAL","Prince@2241");
			d.insert("testing",300,5,24);
			d.update("testing","C1","C3",1,2);
			d.delete("testing","C3",24);
			d.display("testing");
			d.createNew("CREATE TABLE Persons(PersonID int)");
			d.showAll();
		}
		catch(Exception e)
		{
			System.out.println("Connection Failed from Database");
		}
		
	}*/
}