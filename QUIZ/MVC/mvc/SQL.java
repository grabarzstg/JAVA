package mvc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SQL {

	private View s_view;
	
	@SuppressWarnings("unused")
	private String s_username_add;
	@SuppressWarnings("unused")
	private String s_password_add;
	

	

	
	SQL(View view) {
			s_view=view;
		
	   }
	   //-----------------------------------------
	void s_UserCheck(String username, String password) throws SQLException, IOException
	{
		
		Connection conn = getConnection();
	      try
	      { 
	    	  String sql="SELECT PASSWORD FROM Users WHERE USER="+"'"+username+"'";

	    	  
	    	  Statement stm;
	    	  stm = conn.createStatement();
	    	  
	    	  ResultSet rset;
	    	  rset = stm.executeQuery(sql);
	      		
	      	if (rset.next())
	      		{
      			System.out.println(rset.getString(1) +password);  
	      		if( password.equals(rset.getString(1)))
	      			{
	      			System.out.println("ZALOGOWANO!"); 
	      			s_view.window2();
	      			}
	      		else
	      			{
	      			
	      		s_view.loginError("NIEPOPRAWNE HAS£O.");
	      			System.out.println("BAD PASS!!" +rset.getString(1) +password);  
	      			}
	      		}
      		else
  			{
	      	s_view.loginError("NIEPOPRAWNY LOGIN.");
  			System.out.println("BAD LOGIN!!");  
  			}
	         rset.close();
	         stm.close();
		     conn.close();
	      }
	      catch (SQLException ex)
	      {  
	         while (ex != null)
	         {  
	            ex.printStackTrace();
	            ex = ex.getNextException();
	         }
	      }

	}
	
	void s_wynik(String username, int score) throws SQLException, IOException{		
		Connection conn = getConnection();
	      try
	      {
	    	  String sql="SELECT WYNIK FROM Users WHERE USER="+"'"+username+"'";

	    	  
	    	  Statement stm;
	    	  stm = conn.createStatement();
	    	  
	    	  ResultSet rset;
	    	  rset = stm.executeQuery(sql);
	    	  if (rset.next())
	      		{
    			System.out.println(rset.getInt(1) +username);  
	      		if( score>rset.getInt(1))
	      			{
	      			String sql2="UPDATE Users SET WYNIK="+score+" "+"WHERE USER="+"'"+username+"'";
	      			System.out.println(sql2);
	      			stm.execute(sql2);
	      			s_view.showInfo("Zmienino najlepszy wynik");
	      			System.out.println("ZMIENIONO!"); 
	      			}
	      		else
	      			{
	      			s_view.showInfo("Aktualny wynik nie jest Twoim najlepszym.");
	      			System.out.println("NIE ZMIENIONO!!" +rset.getInt(1) +score);  
	      			}
	      		}
    		else
			{
    		s_view.showError("NIEOCZEKIWANY B£¥D");
			System.err.println("ERROR!!!");  
			}
	         rset.close();
	         stm.close();
		     conn.close();
	      }
	      
	      catch (SQLException ex)
	      {  
	         while (ex != null)
	         {  
	            ex.printStackTrace();
	            ex = ex.getNextException();
	         }
	      }

	}
	
	void s_mojWynik(String username) throws SQLException, IOException{		
		Connection conn = getConnection();
	      try
	      {
	    	  String sql="SELECT WYNIK FROM Users WHERE USER="+"'"+username+"'";
 
	    	  Statement stm;
	    	  stm = conn.createStatement();
	    	  
	    	  ResultSet rset;
	    	  rset = stm.executeQuery(sql);
	    	  if (rset.next())
	      		{
	    		  s_view.showWynik("Twój najlepszy wynik to:"  +rset.getInt(1));
	      		}
	    	  else
	    	  {
	    		  s_view.showError("NIEOCZEKIWANY B£¥D");
	    	  }
		      rset.close();
		      stm.close();
	    	  conn.close(); 
	      }
	      
	      
	      catch (SQLException ex)
	      {  
	         while (ex != null)
	         {  
	            ex.printStackTrace();
	            ex = ex.getNextException();
	         }
	      }
	     
	      }
	
	
	void setUsernameAdd(String newUsername) {
		s_username_add=newUsername;
		//System.out.println("s_setUsernameAdd zakoñczenie" + s_username_add);
	}

	void setPasswordAdd(String newPassword) {
		s_password_add=newPassword;
		//System.out.println("s_setPasswordAdd zakoñczenie" + s_password_add);
	}
	

	   public static Connection getConnection()
	      throws SQLException, IOException
	   {  
	      Properties props = new Properties();
	      FileInputStream in = new FileInputStream("database.properties");
	      props.load(in);
	      in.close();

	      String drivers = props.getProperty("jdbc.drivers");
	      if (drivers != null)
	         System.setProperty("jdbc.drivers", drivers);
	      String url = props.getProperty("jdbc.url");
	      String username = props.getProperty("jdbc.username");
	      String password = props.getProperty("jdbc.password");

	      return DriverManager.getConnection(url, username, password);
	   }
		
	}

