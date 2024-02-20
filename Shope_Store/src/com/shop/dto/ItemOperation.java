package com.shop.dto;

public enum ItemOperation {
	
	ShowAll, Add, Update, Delete, Search;
	
	@Override
	public String toString() {
		return String.format("%-2d. %s",ordinal() + 1,name());
	}

}
