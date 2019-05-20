package com.bookstore.repository;

import com.bookstore.domain.Account;

public interface AccountRepository{
	void create(Account account);
	Account findById(String loginId);
}
