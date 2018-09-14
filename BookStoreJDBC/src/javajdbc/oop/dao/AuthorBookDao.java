package javajdbc.oop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javajdbc.oop.core.Author;
import javajdbc.oop.core.Book;
import javajdbc.oop.dbconnection.DBConnection;

public class AuthorBookDao {

	private Connection conn;
	private static AuthorDao authorDao = new AuthorDao();
	
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
	
	public boolean newBookAuthor(Author author,Book book,double revenueShareDouble)throws SQLException{
		String query = "insert into bs_author_book(id,author_id,book_id,revenue_share) values("+generateId()+",?,?,?)";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, author.getId());
		stmt.setLong(2, book.getId());
		stmt.setDouble(3, revenueShareDouble);
		int n = stmt.executeUpdate();
		if(n!=0){
			return true;
		}
		return false;
	}
	
	
	public  long generateId()throws SQLException{
		String query="select max(id) as maxId from bs_author_book";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if(resultSet.next()){
			return resultSet.getLong("maxId")+1;
		}
		return 0;
	}
	
	
	public boolean addNewBookAuthor(Author author,Book book, double revenueShareDouble)throws SQLException{
		String query = "insert into bs_author_book(id,author_id,book_id,revenue_share) values(?,?,?,?)";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1,generateId());
		stmt.setLong(2, author.getId());
		stmt.setLong(3, book.getId());
		stmt.setDouble(4, revenueShareDouble);
		int n  = stmt.executeUpdate();
		if(n!=0){
			return true;
		}
		return false;
		
	}
	
	public boolean deleteAuthorBookByBook(long id)throws SQLException{
		String query ="delete from bs_author_book where book_id = ?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, id);
		int n = stmt.executeUpdate();
		if(n!=0){
			return true;
			
		}
		return false;
	}
	
	
	
	
	
	

}
