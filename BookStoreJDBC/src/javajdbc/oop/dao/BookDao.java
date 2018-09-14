package javajdbc.oop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javajdbc.oop.core.Book;
import javajdbc.oop.dbconnection.DBConnection;

public class BookDao {

	private Connection conn;

	private Connection getConnection() throws SQLException {
		return DBConnection.getDbCon().getConn();
	}

	public BookDao() {

	}

	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Book> findBook() throws SQLException {
		String query = "select * from bs_book";
		Statement stmt = getConnection().createStatement();
		ArrayList<Book> bookList = new ArrayList<Book>();
		ResultSet resultSet = stmt.executeQuery(query);
		while (resultSet.next()) {
			Book book = new Book(resultSet);
			bookList.add(book);
		}
		return bookList;
	}

	public boolean addNewBook(Book book) throws SQLException {
		String query = "insert into bs_book(id,name,category_id,sold_number,price) values(?,?,?,?,?) ";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, book.getId());
		stmt.setString(2, book.getName());
		stmt.setLong(3, book.getCategory().getId());
		stmt.setLong(4, book.getSoldNumber());
		stmt.setDouble(5, book.getPrice());
		int n = stmt.executeUpdate();
		if (n != 0) {
			return true;
		}
		return false;

	}

	public boolean deleteBookByName(String name) throws SQLException {
		String query = "delete from bs_book where name = ?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, name);
		int n = stmt.executeUpdate();
		if (n != 0) {
			System.out.println(n + " deleted rows");
			return true;
		}
		return false;
	}

	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_book";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		}
		return 0;
	}

	public ArrayList<Book> findBookByName(String name) throws SQLException {
		String query = "select * from bs_book where name = ?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, name);
		ArrayList<Book> bookList = new ArrayList<>();
		ResultSet resultSet = stmt.executeQuery();
		while (resultSet.next()) {
			Book book = new Book(resultSet);
			bookList.add(book);
		}
		return bookList;
	}

	public Book findBookById(long id) throws SQLException {
		String query = "select * from bs_book where id =?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, id);
		ResultSet resultSet = stmt.executeQuery();
		if (resultSet.next()) {
			Book book = new Book(resultSet);
			return book;
		}
		return null;
	}

	public boolean modifyBookById(Book book) throws SQLException {
		String query = new String("update bs_book set name=?, category_id=?,sold_number=?,price=? where id=?");
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, book.getName());
		stmt.setLong(2, book.getCategory().getId());
		stmt.setLong(3, book.getSoldNumber());
		stmt.setDouble(4, book.getPrice());
		stmt.setDouble(5, book.getId());
		int n  = stmt.executeUpdate();
		if(n!=0){
			return true;
		}
		return false;
	}

}
