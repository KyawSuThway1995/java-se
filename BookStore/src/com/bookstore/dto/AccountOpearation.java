package com.bookstore.dto;

public enum AccountOpearation {
	SIGN_IN, SIGN_OUT, EXIT;
	
	@Override
	public String toString() {
		return String.format("%d. %s", ordinal() + 1, name());
	}
}
