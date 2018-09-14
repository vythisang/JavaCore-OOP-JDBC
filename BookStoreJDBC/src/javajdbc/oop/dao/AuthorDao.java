package javajdbc.oop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import javajdbc.oop.core.Author;
import javajdbc.oop.dbconnection.DBConnection;

public class AuthorDao {

	private Connection conn;

	private Connection getConnection() throws SQLException {
		return DBConnection.getDbCon().getConn();
	}

	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Author> findAllAuthor() throws SQLException {
		String query = "select * from bs_author";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<>();
		while (resultSet.next()) {
			Author author = new Author(resultSet);
			authorList.add(author);
		}
		return authorList;
	}

	public Author findAuthorById(long id) throws SQLException {
		String query = "select * from bs_author where id= ?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, id);
		ResultSet resultSet = stmt.executeQuery();
		if (resultSet.next()) {
			Author author = new Author(resultSet);
			return author;
		}
		return null;
	}

	public ArrayList<Author> findAuthorByName(String name) throws SQLException {
		String query = "select * from bs_author where name = ?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, name);
		ResultSet resultSet = stmt.executeQuery();
		ArrayList<Author> authorList = new ArrayList<>();
		while (resultSet.next()) {
			Author author = new Author(resultSet);
			authorList.add(author);
		}
		return authorList;

	}

	public boolean deleteAuthorByName(String name) throws SQLException {
		String query = "delete from bs_author where name = ?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, name);
		int n = stmt.executeUpdate();
		if (n != 0) {
			System.out.println(n+" rows deleted");
			return true;
		}
		return false;

	}
	
	public boolean modifyAuthor(Author author)throws SQLException{
		String query = "update bs_author set name=?,dob = ? where id= ?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, author.getName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		stmt.setDate(2, java.sql.Date.valueOf(sdf.format(author.getDob())));
		stmt.setLong(3, author.getId());
		int n = stmt.executeUpdate();
		if(n!=0){
			return true;
		}
		return false;
	}
	
	
	public boolean addNewAuthor(Author author)throws SQLException{
		
		String query = "insert into bs_author(id,name,dob) values(?,?,?)";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, author.getId());
		stmt.setString(2, author.getName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		stmt.setDate(3, java.sql.Date.valueOf(sdf.format(author.getDob())));
		int n = stmt.executeUpdate();
		if(n!=0){
			return true;
		}
		return false;
	}
	
	
	public long generateId()throws SQLException{
		
		String query= "select max(id) as maxId from bs_author";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if(resultSet.next()){
			return resultSet.getLong("maxId")+1;
		}
		return 0;
		
	}
	
	public ArrayList<Author> caculateRevenueOfAuthor()throws SQLException{
		
		String query = "select bs_author.id,bs_author.name,bs_author.dob,"
						+" sum(bs_book.sold_number * bs_book.price * bs_author_book.revenue_share) as revenue"
						+" from bs_book,bs_author_book,bs_author"
						+" where bs_book.id = bs_author_book.book_id and bs_author.id = bs_author_book.author_id"
						+" group by bs_author_book.author_id";
		
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<>();
		while(resultSet.next()){
			Author author = new Author(resultSet);
			author.setRevenue(resultSet.getDouble("revenue"));
			authorList.add(author);
		}
		return authorList;
		
	}
	

	public ArrayList<Author> findTopAuthorBySoldNumber()throws SQLException{
		String query = "select * from view_topauthorbysoldnumber";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet= stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<>();
		while(resultSet.next()){
			Author author = new Author(resultSet);
			author.setSoldNumber(resultSet.getLong("soldNumber"));
			authorList.add(author);
		}
		return authorList;
	}
	
	
	/*public ArrayList<Author> findTopAuthorBySoldNumber()throws SQLException{
		
		String query = "select bs_author.id,bs_author.name,bs_author.dob,"
						+" CASE"
						+" 		WHEN sum(bs_book.sold_number) IS NOT NULL"
						+" 			THEN sum(bs_book.sold_number)"
						+" 		ELSE 0 "
						+" END as soldNumber"
						+" from bs_author left join bs_author_book "
						+" on bs_author.id = bs_author_book.author_id left join bs_book"
						+" on bs_author_book.book_id = bs_book.id"
						+" group by bs_author_book.author_id"
						+" order by soldNumber desc";
		
		
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<>();
		while(resultSet.next()){
			Author author = new Author(resultSet);
			author.setSoldNumber(resultSet.getLong("soldNumber"));
			authorList.add(author);
		}
		return authorList;
		
		
	}*/
	
	
	public ArrayList<Author> findAuthorWithRevenue(String name)throws SQLException{
		String query = "select * from view_author where name = ?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, name);
		ResultSet resultSet = stmt.executeQuery();
		ArrayList<Author> authorList = new ArrayList<>();
		while(resultSet.next()){
			Author author = new Author(resultSet);
			author.setSoldNumber(resultSet.getLong("soldNumber"));
			author.setRevenue(resultSet.getDouble("revenue"));
			author.setBookCount(resultSet.getLong("bookCount"));
			authorList.add(author);
		}
		return authorList;
	}
	
	/*
	public ArrayList<Author> findAuthorWithRevenue(String name)throws SQLException{
		String query = "select bs_author.id,bs_author.name,bs_author.dob,"
						+" sum(bs_book.sold_number) as soldNumber,"
						+" sum(bs_book.sold_number*bs_book.price*revenue_share) as revenue,"
						+" count(bs_book.id) as bookCount"
						+" from bs_book,bs_author,bs_author_book"
						+" where bs_author.id = bs_author_book.author_id and bs_book.id = bs_author_book.book_id"
						+" group by bs_author_book.author_id"
						+" having bs_author.name='"+name+ "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<>();
		while(resultSet.next()){
			Author author = new Author(resultSet);
			author.setSoldNumber(resultSet.getLong("soldNumber"));
			author.setRevenue(resultSet.getDouble("revenue"));
			author.setBookCount(resultSet.getLong("bookCount"));
			authorList.add(author);
		}
		return authorList;
	}
	*/
	
	
	public ArrayList<Author> findAuthorOfBook(long bookId)throws SQLException{
		String query = "select bs_author.id,bs_author.name,bs_author.dob"
						+" from bs_author join bs_author_book on bs_author.id = bs_author_book.author_id"
						+" join bs_book on bs_book.id=bs_author_book.book_id"
						+" where bs_book.id = ?";
		
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, bookId);
		ResultSet resultSet = stmt.executeQuery();
		ArrayList<Author> authorList = new ArrayList<>();
		while(resultSet.next()){
			Author author = new Author(resultSet);
			authorList.add(author);
		}
		return authorList;
	}
	
	
	
	
}
