package com.shop.controller;

import java.util.Arrays;
import java.util.List;

import com.shop.dto.Item;
import com.shop.dto.ItemOperation;
import com.shop.service.ItemService;
import com.shop.service.ItemServiceImpl;
import com.shop.util.InputUtils;

public class ShopController {
    private ItemService service;
    
    public ShopController () {
    	service = ItemServiceImpl.getInstance();
    	
    }
    
    public void start() {
    	do {
    		doOperation(ItemOperation.values()[showMenu() - 1]);	
    	}while ("Y".equalsIgnoreCase(InputUtils.getInputString("Do you want to restart? Y/N ... ")));
    }
    
   private int showMenu() {
	   Arrays.asList(ItemOperation.values()).forEach(System.out::println);
		return InputUtils.getInputNumber("Select Menu Num...");
	}

	
    private void doOperation(ItemOperation opt) {
    	 
    	    switch (opt) {
    	    case ShowAll:
    	    	showItems();
    	    	break;
    	    
    	    case Add:
    	    	addItem();
    	    	break;
    	    	
    	    case Update:
    	    	updateItem();
    	    	break;
    	    	
    	    case Delete:
    	    	deleteItem();
    	    	break;
    	    	
    	    case Search:
    	    	searchItems();
    	    	break;
    	    	
    	    	default:
    	    		throw new IllegalArgumentException("There Is No Option To Do . . .");
    	    }
    }

     private void searchItems() {
    	 List<Item>items = service.showAll();
		
	}

     private void deleteItem() {
    	 List<Item>items = service.showAll();
    	 if (items.equals(((Item) items).getId())) {
    		 items.remove(items);
    	 }
    	 
		
	}

     private void updateItem() {
    	
    	Item item = new Item();
   		item.setName(InputUtils.getInputString("Enter Item Name :"));
   		item.setPrice(InputUtils.getInputNumber("Enter Price :"));
   		item.setQuantity(InputUtils.getInputNumber("Enter Qty :"));
   		service.update(item);
   		printMessage("Item Update ...");
		
	}

      private void addItem() {
    	Item item = new Item();
  		item.setName(InputUtils.getInputString("Enter Item Name :"));
  		item.setPrice(InputUtils.getInputNumber("Enter Price :"));
  		item.setQuantity(InputUtils.getInputNumber("Enter Qty :"));
  		service.add(item);
  		printMessage("Item Added ...");
		
	}

      private void showItems() {
    	  List<Item>items = service.showAll();
  		  printItems(items);
	
}
      private void printMessage(String message) {
  		  System.out.print(message);
  	}

	private void printItems(List<Item> items) {
		System.out.printf("%-2s %-15s %-10s %-10s%n","ID","Name","Price","Quantity" );
		items.forEach(i -> {
			System.out.printf("%-2d %-15s %, -10.2f %,-7d%n",i.getId(),i.getName(),i.getPrice(),i.getQuantity());
			
		});
		
	}
	
	
}
