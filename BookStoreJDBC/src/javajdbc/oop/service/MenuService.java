package javajdbc.oop.service;

import java.util.Scanner;

public class MenuService {

	private static BookService bookService = new BookService();
	private static CategoryService categoryService = new CategoryService();
	private static AuthorService authorService  = new AuthorService();
	private static Scanner scanner = new Scanner(System.in);

	public void bookMenus() {

		boolean flag = true;
		while (flag) {
			bookService.printBookMenu();
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				bookService.displayBook();
				break;
			case 2:
				bookService.addNewBook();
				break;
			case 3:
				bookService.deleteBookByName();
				break;
			case 4:
				bookService.findBookByName();
				break;
			case 5:
				bookService.medifyBookById();
				break;
				
			default:
				flag = false;
				break;
			}
		}
	}
	
	
	public void authorMenus(){
		boolean flag = true;
		while(flag){
			authorService.printAuthorMenu();
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				authorService.displayAuthors();
				break;
			case 2:
				authorService.findAuthorByName();
				break;
			case 3:
				authorService.deleteAuthorByName();
				break;
			case 4:
				authorService.modifyAuthor();
				break;
			case 5:
				authorService.addNewAuthor();
				break;
			case 6:
				authorService.displayRevenueOfAuthor();
				break;
			case 7:
				authorService.findTopAuthorBySoldNumber();
				break;
			case 8:
				authorService.findAuthorWithRevenueByName();
				break;
			case 9:
				authorService.findAuthorOfBook();
				break;
			default:
				flag=false;
				break;
			}
			
			
		}
	}
	
	public void categoryMenus(){
		boolean flag=true;
		while(flag){
			categoryService.printCategoryMenu();
			System.out.println("Enter your choice");
			int choice  = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				categoryService.displayCategory();
				break;
			case 2:
				categoryService.addNewCategory();
				break;
			case 3:
				categoryService.deleteCategoryByName();
				break;
			case 4:
				categoryService.updateCategory();
				break;
			case 5:
				categoryService.findCategoryByName();
				break;
			case 6:
				categoryService.displayMostRevenueCategory();
				break;
			default:
				break;
			}
			
		}
	}
	
	
	
	
	
	

	public void printMenuMenu() {
		System.out.println("The bookstore management program");
		System.out.println("1.Manage books");
		System.out.println("2.Manage categories");
		System.out.println("3.Manage author");
	}
}
