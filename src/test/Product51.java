package test;
import java.util.Scanner;
import java.sql.*;

public class Product51 {

	public static void main(String[] args) {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","wahidur");
			PreparedStatement ps1=con.prepareStatement("insert into Product51 values(?,?,?,?)");
			PreparedStatement ps2=con.prepareStatement("select * from Product51");
			PreparedStatement ps3=con.prepareStatement("select * from Product51 where code=?");
			PreparedStatement ps4=con.prepareStatement("update product51 set name=? where code=?");
			PreparedStatement ps5=con.prepareStatement("delete from Product51 where code=?");
			Scanner sc=new Scanner(System.in);
			while(true)
			{
				System.out.println("Enter Choise:");
				System.out.println("\t1.Add Product"+           
				                  "\n\t2.ViewProducts"+"\n\t3.ViewProducts by code"+
						          "\n\t4.Update by code"+"\n\t5.Delete product by code"+"\n\t6.exit()");
				System.out.println("Enter Choise :");
				switch(Integer.parseInt(sc.nextLine()))
				{
				case 1:
					System.out.println("Enter product details:");
					System.out.println("Enter Product code :");
					String pC=sc.nextLine();
					System.out.println("Enter Product Name :");
					String pN=sc.nextLine();
					System.out.println("Enter Product price :");
					float pP=Float.parseFloat(sc.nextLine());
					System.out.println("Enter Product Qnt :");
					int  pQ=Integer.parseInt(sc.nextLine());
					ps1.setString(1, pC);
					ps1.setString(2, pN);
					ps1.setFloat(3, pP);
					ps1.setInt(4, pQ);
					ps1.executeQuery();
					 break;
				case 2:
					System.out.println("**********product details**********");
					ResultSet rs=ps2.executeQuery();
					while(rs.next())
					{
						System.out.println(String.format("%-20s", rs.getString(1))+String.format("%-20s", rs.getString(2))+String.format("%-20s", rs.getFloat(3))+String.format("%-20s", rs.getInt(4)));
					}
					break;
				case 3:
					System.out.println("Enter Code: ");
					String code=sc.nextLine();
					ps3.setString(1, code);
					ResultSet rs2=ps3.executeQuery();
					if(rs2.next())
					{
						System.out.println(String.format("%-20s", rs2.getString(1))+String.format("%-20s", rs2.getString(2))+String.format("%-20s", rs2.getFloat(3))+String.format("%-20s", rs2.getInt(4)));
					}
					else
					{
						System.out.println("Invalid code or not matched code ");
					}
					break;
				case 4:
					
					
					System.out.println("Enter Code: ");
					String code2=sc.nextLine();
					System.out.println("Enter modified name: ");
					String n=sc.nextLine();
					ps4.setString(1, n);
					ps4.setString(2, code2);
					int k=ps4.executeUpdate();
					if(k>0)
					{
						System.out.println(k+" row updated successfully");
					}
					break;
				case 5:
					System.out.println("Enter code which row want to delete");
					String str=sc.nextLine();
					ps5.setString(1, str);
					int i=ps5.executeUpdate();
					if(i>0)
					{
						System.out.println(i+" row deleted successfully");
					}
					break;
					
				case 6:
					System.out.println("stope.........");
					System.exit(0);
					
					default:
						System.out.println("Invalid Choise");
				}
			}//end of loop
			
		}catch(Exception  e)
		{
			e.printStackTrace();
		}

	}

}
