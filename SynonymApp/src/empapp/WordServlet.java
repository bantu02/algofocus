package empapp;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

public class WordServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException 
	{
		String wn=request.getParameter("wrd");
		System.out.println(wn);
		java.sql.Connection con=null;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		//System.out.println("Driver loaded");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/word","root","tiger");
		//System.out.println("Connection");
		Statement stmt=con.createStatement();
		String qry="select * from words_synonyms where word='"+wn+"'";
		System.out.println("query is "+qry);
		ResultSet rs=stmt.executeQuery(qry);
		System.out.println("query executed");
		if(rs.next())
		{
					String wrd1=rs.getString(2);
					System.out.println("The Synonyms for word:"+wn);
					System.out.println(wrd1);
		}
		}
		catch(ClassNotFoundException|SQLException e)
		{
			e.printStackTrace();
		}
		finally{
			if(con!=null)
			{
				try{ con.close();}
				catch(SQLException e){ e.printStackTrace();}
			}
		}
	}
}
