package com.shop.repo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.shop.dto.Item;
import com.shop.util.DBConnector;

public class ItemRepositoryImpl  implements ItemRepository {
	
	private static ItemRepositoryImpl instance;
	private DBConnector dbConnector;
	private Properties pro;
	
	private ItemRepositoryImpl() {
		dbConnector = DBConnector.getInstance();
		pro = new Properties();
		try(InputStream in = new FileInputStream("config.properties")) {
			pro.load(in);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ItemRepositoryImpl getInstance() {
		return instance = instance == null ? new ItemRepositoryImpl() : instance;
	}
	
	@Override
	public void add(Item item) {
		List<Item>list = new ArrayList<>();
		try(PreparedStatement stmt = dbConnector.getConnection().prepareStatement(pro.getProperty("insert"))){
			ResultSet rs = stmt.executeQuery();
			
				list.add(getItem(rs));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Item item) {
		try(PreparedStatement stmt = dbConnector.getConnection().prepareStatement(pro.getProperty("update"))){
			stmt.setString(1, item.getName());
			stmt.setDouble(2, item.getPrice());
			stmt.setInt(3, item.getQuantity());
			stmt.setInt(4, item.getId());
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
//		List<Item>list = new ArrayList<>();
//		try(PreparedStatement stmt = dbConnector.getConnection().prepareStatement(pro.getProperty("delete"))){
//			
//			int rs = stmt.executeUpdate();
//				list.remove(rs);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}

	@Override
	public List<Item> showAll() {
		List<Item>list = new ArrayList<>();
		try(PreparedStatement stmt = dbConnector.getConnection().prepareStatement(pro.getProperty("select"))){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(getItem(rs));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Item searchById(int id) {
		
		StringBuilder sb = new StringBuilder(pro.getProperty("select"));
		sb.append("where id = ?");
		try(PreparedStatement stmt = dbConnector.getConnection().prepareStatement(sb.toString())){
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			return getItem(rs);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Item> search(String name, double price, int quantity) {
		
    List<Item> items = new ArrayList<>();
		
		/*
		 *this query is select * from item only but client may want to search by name
		 *or price or quantity , so that out query needed to be dynamic query e.g
		 *select * from item where name %like%? e.g select  * from item where price = ?
		 *and quantity =? smoething like that.
		 */
		StringBuilder sb = new StringBuilder(pro.getProperty("select"));
		List<Object> param = new ArrayList<>();
		boolean flag = false;
		if(!name.isEmpty() || !name.isBlank()) {
			sb.append("Where name like ?");
			param.add(name.concat("%"));
			flag = true;
		}
		if(price > 0) {
			sb = flag ? sb.append("and price = ?"): sb.append("where price = ?");
			param.add(price);
		}
		if(quantity > 0) {
			sb.append("and quantity = ?");
			param.add(quantity);
		}
		System.out.println(sb.toString());
		try(PreparedStatement stmt = dbConnector.getConnection().prepareStatement(sb.toString())){
			for(int i = 0; i < param.size(); i++) {
				stmt.setObject((i + 1), param.get(i));
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				items.add(getItem(rs));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}
	
	private Item getItem(ResultSet rs) throws SQLException{
		Item item = new Item();
		item.setId(rs.getInt(1));
		item.setName(rs.getString(2));
		item.setPrice(rs.getDouble(3));
		item.setQuantity(rs.getInt(4));
		return item;
	}

}
