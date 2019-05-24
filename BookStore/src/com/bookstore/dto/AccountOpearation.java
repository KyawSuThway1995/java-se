package com.bookstore.dto;

public enum AccountOpearation {
	SIGN_UP, SIGN_IN, EXIT;
	
	@Override
	public String toString() {
		return String.format("%d. %s", ordinal() + 1, name());
	}
}
