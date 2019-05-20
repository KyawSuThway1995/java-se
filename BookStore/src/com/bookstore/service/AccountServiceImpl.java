package com.bookstore.service;

import com.bookstore.dto.Account;
import com.bookstore.repository.AccountRepository;
import com.bookstore.repository.AccountRepositoryImpl;

public class AccountServiceImpl implements AccountService {
	
	private AccountRepository repo;
	private static final AccountServiceImpl SERVICE = new AccountServiceImpl();
	
	private AccountServiceImpl() {
		repo = AccountRepositoryImpl.getInstance();
	}
	
	public static AccountService getInstance() {
		return SERVICE;
	}

	@Override
	public void create(Account account) {
		repo.create(account);
	}

	@Override
	public Account findById(String loginId) {
		return repo.findById(loginId);
	}

}
