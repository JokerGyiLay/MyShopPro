package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyShop {

	private static Item[] items = { new Item("Apple", 500, 50), 
			new Item("Orange", 300, 50),
			new Item("Mango", 200, 50),
			new Item("Water Melon", 1500, 100)
			};

	// using ArrayList instead of using dynamic array.
	private static List<Item> cart = new ArrayList<>();	// collection
	private Scanner scan = new Scanner(System.in);
	private int payment;
	private int refund;

	/**
	 * starting point
	 */
	public void start() {
		int menuNum = 0;
		int quantity = 0;
		int totalAmount = 0;
		String answer = null;
		do {
			showMenu();
			menuNum = chooseMenuNumber();
			quantity = getItemCount(menuNum);
			totalAmount += calculate(menuNum, quantity);
			System.out.println("Do You Need Other Fruits? Y / N");
			answer = getUserInput();
		} while (answer.equalsIgnoreCase("y"));

		checkOut(totalAmount);
		receipt(totalAmount);

	}

	/**
	 * show receipt
	 * 
	 * @param totalAmount
	 */

	private void receipt(int totalAmount) {
		System.out.println("==========================================");
		System.out.println("             R E C E P I T                ");
		System.out.println("==========================================");
		System.out.printf("%-5s %-7s\t\t\t %s%n", "QTY", "ITEM", "AMT");
		for (int i = 0; i < cart.size(); i++) {
			System.out.printf("%-5d %-7s\t\t\t %d%n", cart.get(i).getStock(), cart.get(i).getName(),
					cart.get(i).getPrice());
		}
		System.out.println("==========================================");
		System.out.printf("%-10s\t\t\t %d%n", "TOTAL AMOUNT", totalAmount);
		System.out.printf("%-10s\t\t\t %d%n", "CASH", payment);
		System.out.printf("%-10s\t\t\t %d%n", "CHANGE", refund);
		System.out.println("==========================================");
		System.out.println("           T H A N K  Y O U               ");
		System.out.println("==========================================");
	}

	private void checkOut(int totalBalance) {
		System.out.println("Your Balance is : " + totalBalance);
		System.out.println("Please enter the pay amount by the customer : ");
		boolean checkBalance = false;
		do {
			payment += Integer.parseInt(getUserInput());
			if (payment < totalBalance) {
				System.out
						.println("Your balance is not enough. Need to pay more  " + (totalBalance - payment) + " MMK");
				checkBalance = true;
			} else {
				refund = payment - totalBalance;
				checkBalance = false;
			}
		} while (checkBalance);
	}

	private int calculate(int menuNum, int quantity) {
		int total = items[menuNum - 1].getPrice() * quantity;
		return total;
	}

	private int getItemCount(int menuNum) {
		System.out.println("How many " + items[menuNum - 1].getName() + " do you want?");
		int quantity = Integer.parseInt(getUserInput());
		checkStock(menuNum, quantity);
		return quantity;
	}

	private void checkStock(int menuNum, int quantity) {

		if (quantity <= items[menuNum - 1].getStock()) {
			updateStock(menuNum, quantity);
		} else {
			System.out.printf("We don't enough %s.But %d %s left. Do you want All OF THESE? Y/N ",
					items[menuNum - 1].getName(), items[menuNum - 1].getStock(), items[menuNum - 1].getName());

			if (getUserInput().equalsIgnoreCase("y")) {
				updateStock(menuNum, items[menuNum - 1].getStock());
			} else {
				getItemCount(menuNum);
			}
		}

	}

	private void updateStock(int menuNum, int quantity) {
		items[menuNum - 1].setStock(items[menuNum - 1].getStock() - quantity);
		cart.add(new Item(items[menuNum - 1].getName(), (items[menuNum - 1].getPrice() * quantity), quantity));
	}

	private int chooseMenuNumber() {
		int menuNum;
		do {
			System.out.println("Please choose the number from 1 to " + items.length);
			menuNum = Integer.parseInt(getUserInput());
		} while (menuNum > items.length);

		return menuNum;
	}

	private void showMenu() {
		System.out.println("Welcome To Hello Mart");

		for (int i = 0; i < items.length; i++) {
			System.out.printf("%d. %-10s%d kyats\t [%d]%n", (i + 1), items[i].getName(), items[i].getPrice(),
					items[i].getStock());
		}
		System.out.println("Please choose the fruit do you want...");

	}

	private String getUserInput() {
		return scan.nextLine();
	}

}
