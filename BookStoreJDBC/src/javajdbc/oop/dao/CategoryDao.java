package javajdbc.oop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javajdbc.oop.core.Category;
import javajdbc.oop.dbconnection.DBConnection;

public class CategoryDao {

	private Connection conn;
	
	private Connection getConnection()throws SQLException{
		return DBConnection.getDbCon().getConn();
	}
	
	public CategoryDao() {
	}


	public void closeConnection(){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public ArrayList<Category> findAllCategory()throws SQLException{
		String query = "select * from bs_category";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Category> categories = new ArrayList<>();
		while(resultSet.next()){
			Category category = new Category(resultSet);
			categories.add(category);
		}
		return categories;
		
	}
	
	public boolean addNewCategory(Category category)throws SQLException{
		String query = "insert into bs_category(id,name) values("+category.getId()+",'" +category.getName()+"')";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		if(n!=0){
			return true;
		}
		return false;
	}
	
	
	public long generateId()throws SQLException{
		String query = "select max(id) as maxId from bs_category";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if(resultSet.next()){
			return resultSet.getLong("maxId")+1;
		}
		return 0;	
	}

	
	
	public boolean deleteCategoryByName(String name)throws SQLException{
		String query = "delete from bs_category where name = ?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, name);
		int n = stmt.executeUpdate();
		if(n!=0){
			System.out.println(n+" rows deleted");
			return true;
		}
		return false;
	
	}
	
	
	public boolean modifyCategory(Category category)throws SQLException{
		String query = "update bs_category set name =? where id=?";
		PreparedStatement stmt  = getConnection().prepareStatement(query);
		stmt.setString(1, category.getName());
		stmt.setLong(2, category.getId());
		int n = stmt.executeUpdate();
		if(n!=0){
			return true;
		}
		return false;
		
		
	}
	
	public Category findCategoryById(long id)throws SQLException{
		String query = "select * from bs_category where id=?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, id);
		ResultSet resultSet = stmt.executeQuery();
		if(resultSet.next()){
			Category category = new Category(resultSet);
			return category;
		}
		return null;
	}
	
	public ArrayList<Category> findCategoryByName(String name)throws SQLException{
		String query = "select * from bs_category where name=?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, name);
		ResultSet resultSet=  stmt.executeQuery();
		ArrayList<Category> categories = new ArrayList<>();
		while(resultSet.next()){
			Category category = new Category(resultSet);
			categories.add(category);
		}
		return categories;
	}
	
	
	public Category mostRevenueCategory()throws SQLException{
		String query ="select bs_category.name,bs_category.id,"+" sum(bs_book.sold_number*bs_book.price) as 'revenue'"
						+" from bs_book, bs_category"
						+" where bs_book.category_id=bs_category.id"
						+" group by bs_book.category_id"
						+" order by revenue desc"
						+" limit 0, 1;";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet  = stmt.executeQuery(query);
		resultSet.next();
		Category category = new Category(resultSet);
		category.setRevenue(resultSet.getDouble("revenue"));
		return category;
	}

	
	public Category findCategoryOfBook(long categoryId)throws SQLException{
		String query = "select bs_category.id,bs_category.name"
						+" from bs_category join bs_book on bs_book.category_id=bs_category.id"
						+" where bs_book.category_id = ?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, categoryId);
		ResultSet resultSet = stmt.executeQuery();
		if(resultSet.next()){
			Category category = new Category(resultSet);
			return category;
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
