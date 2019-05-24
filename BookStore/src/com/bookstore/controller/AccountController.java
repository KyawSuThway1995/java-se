package com.bookstore.controller;

import java.util.Objects;

import com.bookstore.dto.Account;
import com.bookstore.dto.AccountOpearation;
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

	public void run() {
		for (;;) {
			try {
				doProcess(AccountOpearation.values()[showMenu() - 1]);
				return;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	private int showMenu() {
		for(AccountOpearation ope : AccountOpearation.values()) {
			System.out.println(ope);
		}
		
		System.out.println();
		return BookUtils.readInt("Choose One Option (1 or 2 or 3) : ");
	}

	private void doProcess(AccountOpearation ope) {
		switch (ope) {
		case SIGN_UP:
			signUp();
			break;

		case SIGN_IN:
			signIn();
			break;
			
		case EXIT:
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
			run();
		
		return new Account(loginId, PasswordUtil.encryptPassword(password));

	}
}
