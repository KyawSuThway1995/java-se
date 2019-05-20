package com.bookstore.repository;

import com.bookstore.dto.Account;

public interface AccountRepository{
	void create(Account account);
	Account findById(String loginId);
}
