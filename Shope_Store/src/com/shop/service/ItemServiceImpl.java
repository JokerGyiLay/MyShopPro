package com.shop.service;

import java.util.List;

import com.shop.dto.Item;
import com.shop.repo.ItemRepository;
import com.shop.repo.ItemRepositoryImpl;

public class ItemServiceImpl implements ItemService{
	
	private static ItemServiceImpl instance;
	private ItemRepository repo;
	
	private ItemServiceImpl() {
		repo = ItemRepositoryImpl.getInstance();
	}
	
	public static ItemServiceImpl getInstance() {
		return instance = instance == null ? new ItemServiceImpl() : instance;
	}

	@Override
	public void add(Item item) {
		repo.add(item);
		
	}

	@Override
	public void update(Item item) {
		repo.update(item);
		
	}

	@Override
	public void delete(int id) {
		repo.delete(id);
		
	}

	@Override
	public List<Item> showAll() {
		return repo.showAll();
	}

	@Override
	public Item searchById(int id) {
		return repo.searchById(id);
	}

	@Override
	public List<Item> search(String name, double price, int quantity) {
		return repo.search(name, price, quantity);
	}
	

}
