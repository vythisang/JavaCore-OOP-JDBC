package javajdbc.oop.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javajdbc.oop.core.Category;
import javajdbc.oop.dao.CategoryDao;

public class CategoryService {

	private CategoryDao categoryDao = new CategoryDao();
	private Scanner scanner = new Scanner(System.in);

	public void displayCategory() {
		try {
			ArrayList<Category> categoryList = categoryDao.findAllCategory();
			for (Category category : categoryList) {
				System.out.println(category);
			}
		} catch (SQLException e) {
			System.out.println("There is an error when display all categories");
			e.printStackTrace();
		}
	}

	public void addNewCategory() {
		System.out.println("Add new category function");
		try {
			System.out.println("Enter name");
			String name = scanner.nextLine();
			long id = categoryDao.generateId();
			Category category = new Category(id, name);
			categoryDao.addNewCategory(category);
		} catch (SQLException e) {
			System.out.println("There is an error when");
			e.printStackTrace();
		}
	}

	public void deleteCategoryByName() {
		System.out.println("Delete category by name function");
		System.out.println("Enter name");
		String name = scanner.nextLine();

		try {

			if (categoryDao.deleteCategoryByName(name)) {
				System.out.println("Delete successfull");
			} else {
				System.out.println(name + " don't exist");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCategory() {
		System.out.println("Modify category by id function");
		System.out.println("Enter id");
		String id = scanner.nextLine();
		try {
			Category category = categoryDao.findCategoryById(Long.parseLong(id));
			if (category == null) {
				System.out.println("Not found id=" + id);
			} else {
				System.out.println("Found category with id=" + id);
				System.out.println("Category information");
				System.out.println(category);
				System.out.println("Enter new name");
				String name = scanner.nextLine();
				category.setName(name);
				categoryDao.modifyCategory(category);
				System.out.println("After update category with id =" + id);
				System.out.println(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void findCategoryByName(){
		System.out.println("Find category by name function");
		System.out.println("Enter name");
		String name = scanner.nextLine();
		try{
			ArrayList<Category> categories= categoryDao.findCategoryByName(name);
			System.out.println("Found category with name="+name);
			for(Category catogory:categories){
				System.out.println(catogory);
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void displayMostRevenueCategory(){
		System.out.println("Display the most revenue function");
		try{
			Category category = categoryDao.mostRevenueCategory();
			System.out.println(category);
		
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public void printCategoryMenu() {
		System.out.println("The category management program");
		System.out.println("1.Display categories");
		System.out.println("2.Add new category");
		System.out.println("3.Delete category by name");
		System.out.println("4.Modify category by id");
		System.out.println("5.Find categories by name");
		System.out.println("6.Display the most revenue category");
	}
}
