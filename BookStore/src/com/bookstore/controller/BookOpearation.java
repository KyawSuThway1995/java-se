package com.bookstore.controller;

public enum BookOpearation {
	SHAWALL, CREATE, UPDATE, DELETE, SEARCH, LOGOUT;
	
	@Override
	public String toString() {
		return String.format("%d. %s", ordinal() + 1, name());
	}
}
