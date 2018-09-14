package javajdbc.oop.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javajdbc.oop.core.Author;
import javajdbc.oop.dao.AuthorDao;

public class AuthorService {

	private AuthorDao authorDao = new AuthorDao();
	private Scanner scanner = new Scanner(System.in);

	public void displayAuthors() {
		try {
			ArrayList<Author> authorList = authorDao.findAllAuthor();
			for (Author author : authorList) {
				System.out.println(author);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void findAuthorByName() {
		System.out.println("Enter name");
		String name = scanner.nextLine();
		try {
			ArrayList<Author> authorList = authorDao.findAuthorByName(name);
			for (Author author : authorList) {
				System.out.println(author);
			}

		} catch (SQLException e) {
			System.out.println("There is an error when finding author by name");
			e.printStackTrace();
		}
	}

	public void deleteAuthorByName() {
		System.out.println("Enter name");
		String name = scanner.nextLine();
		try {
			if (authorDao.deleteAuthorByName(name)) {
				System.out.println("delete successfull");
			} else {
				System.out.println(name + " don't exist");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void modifyAuthor() {
		System.out.println("Enter id");
		String id = scanner.nextLine();
		try {
			Author author = authorDao.findAuthorById(Long.parseLong(id));
			if (author == null) {
				System.out.println(id + " don't exist");
			} else {

				System.out.println("author information whith  id =" + id);
				System.out.println(author);
				System.out.println("Input name");
				String name = scanner.nextLine();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println("Input dob(yyyy-MM-dd)");
				String dob = scanner.nextLine();
				Date authorDate = sdf.parse(dob);
				author.setName(name);
				author.setDob(authorDate);
				if (authorDao.modifyAuthor(author)) {
					System.out.println("Update author successfull");
				} else {
					System.out.println("Update fail");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public void addNewAuthor() {
		try {
			System.out.println("Enter name");
			String name = scanner.nextLine();
			System.out.println("Enter dob(yyyy-MM-dd)");
			String dob = scanner.nextLine();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateDob = sdf.parse(dob);

			Author author = new Author(authorDao.generateId(), name, dateDob);
			if (authorDao.addNewAuthor(author)) {
				System.out.println("Add new author successfull");
			} else {
				System.out.println("Add new author fail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	public void displayRevenueOfAuthor(){
		
		try{
			ArrayList<Author> authorList  =  authorDao.caculateRevenueOfAuthor();
			for(Author author:authorList){
				System.out.println(author);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void findTopAuthorBySoldNumber(){
		try{
			ArrayList<Author> authorList = authorDao.findTopAuthorBySoldNumber();
			for(Author author:authorList){
				System.out.println(author);
			}
			
			
		}catch (SQLException e) {
			System.out.println("There an error when find top author by soldNumber");
			e.printStackTrace();
		}
	}
	
	
	public void findAuthorWithRevenueByName(){
		System.out.println("Enter name");
		String name = scanner.nextLine();
		try{
			ArrayList<Author> authorList = authorDao.findAuthorWithRevenue(name);
			for(Author author:authorList){
				System.out.println(author);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void findAuthorOfBook(){
		System.out.println("Enter book id");
		String bookId = scanner.nextLine();
		try{
			ArrayList<Author> authorList = authorDao.findAuthorOfBook(Long.parseLong(bookId));
			for(Author author:authorList){
				System.out.println(author);
			}
			
		}catch (SQLException e) {
			System.out.println("There an error when find author of book list by bookId");
		}catch (NumberFormatException e) {
			System.out.println("There an error when enter bookId");
		}
	}
	

	public void printAuthorMenu() {
		System.out.println("The program author service management");
		System.out.println("1.Display all author");
		System.out.println("2.Find author by name");
		System.out.println("3.Delete author by name");
		System.out.println("4.Update author by id");
		System.out.println("5.Add new author");
		System.out.println("6.Display revenue each author");
		System.out.println("7.Display top author by soldNumber");
		System.out.println("8.Find author with revenue by name");
		System.out.println("9.Find author list of book by bookId");
	}

}
