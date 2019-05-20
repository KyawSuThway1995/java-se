package com.bookstore.service;

import com.bookstore.domain.Account;

public interface AccountService{
	void create(Account account);
	Account findById(String loginId);
}
