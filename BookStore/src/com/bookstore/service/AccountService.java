package com.bookstore.service;

import com.bookstore.dto.Account;

public interface AccountService{
	void create(Account account);
	Account findById(String loginId);
}
