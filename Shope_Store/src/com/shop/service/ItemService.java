package com.shop.service;

import java.util.List;

import com.shop.dto.Item;

public interface ItemService {
	
	void add(Item item);
	
	void update(Item item);
	
	void delete(int id);
	
	List<Item> showAll();
	
	Item searchById(int id);
	
	List<Item>search (String name, double price , int quantity);

}
