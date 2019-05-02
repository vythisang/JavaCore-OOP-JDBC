package javajdbc.oop.app;

import java.util.Scanner;

import javajdbc.oop.service.MenuService;

public class App {

	private static MenuService menuService = new MenuService();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		boolean flag = true;
		while (flag) {
			menuService.printMenuMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				menuService.bookMenus();
				break;
				
			case 2:
				menuService.categoryMenus();
				break;
			case 3:
				menuService.authorMenus();
				break;
			default:
				flag = false;
				break;
			}

		}

	}

}



