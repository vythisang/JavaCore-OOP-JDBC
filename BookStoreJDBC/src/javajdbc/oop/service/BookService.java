package javajdbc.oop.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javajdbc.oop.core.Author;
import javajdbc.oop.core.AuthorBook;
import javajdbc.oop.core.Book;
import javajdbc.oop.core.Category;
import javajdbc.oop.dao.AuthorBookDao;
import javajdbc.oop.dao.AuthorDao;
import javajdbc.oop.dao.BookDao;
import javajdbc.oop.dao.CategoryDao;

public class BookService {

	private static Scanner scanner = new Scanner(System.in);
	private static BookDao bookDao = new BookDao();
	private static CategoryDao categoryDao = new CategoryDao();
	private static AuthorDao authorDao = new AuthorDao();
	private static AuthorBookDao authorBookDao = new AuthorBookDao();

	public void displayBook() {
		try {
			ArrayList<Book> bookList = bookDao.findBook();
			for (Book book : bookList) {
				System.out.println(book);
			}

		} catch (SQLException e) {
			System.out.println("There is an error when display all books");
			e.printStackTrace();
		}
	}

	public void addNewBook() {

		try {
			long id = bookDao.generateId();
			System.out.println("Enter name");
			String name = scanner.nextLine();
			System.out.println("Enter soldNumber");
			String soldNumber = scanner.nextLine();
			System.out.println("Enter price");
			String price = scanner.nextLine();
			System.out.println("Chose category ID:");
			ArrayList<Category> categoryList = categoryDao.findAllCategory();
			for (Category category : categoryList) {
				System.out.println(category);
			}

			String categoryId = scanner.nextLine();
			Category selectedCategory = categoryDao.findCategoryById(Long.parseLong(categoryId));
			Book book = new Book(id, name, Double.parseDouble(price), Long.parseLong(soldNumber), selectedCategory);

			ArrayList<Author> authorList = authorDao.findAllAuthor();
			for (Author author : authorList) {
				System.out.println(author);
			}
			System.out.println("Choose author list, split by - character:");
			String authorIdList = scanner.nextLine();
			String[] authors = authorIdList.split("-");
			for (String author : authors) {
				long authorIdLong = Long.parseLong(author);
				Author selectedAuthor = authorDao.findAuthorById(authorIdLong);
				System.out.println("Please choose the revenueShare for this author:");
				String revenueShare = scanner.nextLine();
				authorBookDao.addNewBookAuthor(selectedAuthor, book, Long.parseLong(revenueShare));
			}
			bookDao.addNewBook(book);

		} catch (SQLException e) {
			System.out.println("There an error");
		}
	}

	public void deleteBookByName() {
		System.out.println("Enter name");
		String name = scanner.nextLine();
		try {
			bookDao.deleteBookByName(name);

		} catch (SQLException e) {
			System.out.println("There is an error when deleting book by name");
			e.printStackTrace();
		}
	}

	public void findBookByName() {
		System.out.println("Enter name");
		String name = scanner.nextLine();
		try {
			ArrayList<Book> bookList = bookDao.findBookByName(name);
			for (Book book : bookList) {
				System.out.println(book);
			}

		} catch (SQLException e) {
			System.out.println("There an error when find book by name");
			e.printStackTrace();
		}
	}

	public void medifyBookById()  {
		System.out.println("Enter id");
		String id = scanner.nextLine();
		try {

			Book book = bookDao.findBookById(Long.parseLong(id));
			System.out.println("Infor before update");
			System.out.println(book);
			System.out.println("Enter to modify book");
			System.out.println("Enter name");
			String name = scanner.nextLine();
			System.out.println("Enter soldNumber:");
			String soldNumber = scanner.nextLine();
			System.out.println("Enter price");
			String price = scanner.nextLine();
			book.setName(name);
			book.setSoldNumber(Long.parseLong(soldNumber));
			book.setPrice(Double.parseDouble(price));
			System.out.println("Chose id you want to update book");
			ArrayList<Category> categorieList = categoryDao.findAllCategory();
			for(Category category:categorieList){
				System.out.println(category);
			}
			String categoryId = scanner.nextLine();
			Category category = categoryDao.findCategoryById(Long.parseLong(categoryId));
			book.setCategory(category);
			
			System.out.println("Chose author list,split by - character");
			ArrayList<Author> authorList = authorDao.findAllAuthor();
			for(Author author:authorList){
				System.out.println(author);
			}
			String authorIdList = scanner.nextLine();
			String[] authors = authorIdList.split("-");
			authorBookDao.deleteAuthorBookByBook(Long.parseLong(id));
			for(String author: authors){
				Long authorIdLong = Long.parseLong(author);
				Author selectedAuthor = authorDao.findAuthorById(authorIdLong);
				System.out.println("Enter revenueshare for author:");
				String revenueShare= scanner.nextLine();
				authorBookDao.addNewBookAuthor(selectedAuthor, book, Double.parseDouble(revenueShare));
			}
			bookDao.modifyBookById(book);

			
		} catch (SQLException e) {
			System.out.println("There an error when update book by name");
			e.printStackTrace();
		}

	}

	public void printBookMenu() {
		System.out.println("The book management program");
		System.out.println("1.Display books");
		System.out.println("2.Add new a book");
		System.out.println("3.Delete book by name");
		System.out.println("4.Find book by name");
		System.out.println("5.Update book by id");
	}
}
