<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello</title>
</head>
<body>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%
String wn=request.getParameter("wrd");
System.out.println(wn);
java.sql.Connection con=null;
try{
Class.forName("com.mysql.jdbc.Driver");
//out.println("Driver loaded");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/word","root","tiger");
//out.println("Connection");
Statement stmt=con.createStatement();
String qry="select * from words_synonyms where word='"+wn+"'";
//out.println("query is "+qry);
ResultSet rs=stmt.executeQuery(qry);
if(rs.next())
{
			String wrd1=rs.getString(2);
			out.println("The Synonyms for word "+wn+" is:");
			out.println(wrd1);
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
%>
</body>
</html>
