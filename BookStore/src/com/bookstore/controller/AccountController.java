package com.bookstore.controller;

import java.util.Objects;

import com.bookstore.domain.Account;
import com.bookstore.exception.AccountException;
import com.bookstore.exception.BaseException;
import com.bookstore.service.AccountService;
import com.bookstore.service.AccountServiceImpl;
import com.bookstore.util.BookUtils;
import com.bookstore.util.PasswordUtil;

public class AccountController {

	private AccountService service;

	public AccountController() {
		service = AccountServiceImpl.getInstance();
	}

	public void startAccount() {
		for (;;) {
			try {
				doProcess(showMenu());
				return;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	private int showMenu() {
		System.out.println("1. SignUp");
		System.out.println("2. SignIn");
		System.out.println("3. Exit");
		System.out.println();
		return BookUtils.readInt("Choose One Option (1 or 2 or 3) : ");
	}

	private void doProcess(int code) {
		switch (code) {
		case 1:
			signUp();
			break;

		case 2:
			signIn();
			break;
			
		case 3:
			System.out.println(">> Bye Bye");
			System.exit(0);

		default:
			throw new BaseException("Invalid Option Code!");
		}
	}

	private void signUp() {
		for (;;) {
			try {
				Account account = getAccount();
				service.create(account);
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	private void signIn() {

		for (;;) {
			try {
				Account original = getAccount();
				Account temp = service.findById(original.getLoginId());

				if (Objects.isNull(temp)) {
					throw new AccountException.AccountNotFoundException(original.getLoginId() + " not found in system!");
				}

				if (!temp.getPassword().equals(original.getPassword()))
					throw new AccountException.WrongPasswordException("Wrong Password! Try again");

				break;

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private Account getAccount() {
		String loginId = BookUtils.readString("Login ID : ");
		String password = new String(BookUtils.getConsole().readPassword("Password : "));
		
		if("exit".equals(loginId) || "exit".equals(password)) 
			startAccount();
		
		return new Account(loginId, PasswordUtil.encryptPassword(password));

	}
}
