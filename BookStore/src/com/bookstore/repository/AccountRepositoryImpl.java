package com.bookstore.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.bookstore.dto.Account;
import com.bookstore.exception.AccountException;

public class AccountRepositoryImpl implements AccountRepository {

	private static AccountRepository repo;
	private List<Account> accounts;

	@SuppressWarnings("unchecked")
	private AccountRepositoryImpl() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("account.obj"))) {
			accounts = (List<Account>) ois.readObject();
		} catch (Exception e) {
			accounts = new ArrayList<>();
		}
	}

	public static AccountRepository getInstance() {
		return null == repo ? repo = new AccountRepositoryImpl() : repo;
	}

	@Override
	public void create(Account account) {
		Account acc = findById(account.getLoginId());

		if (Objects.nonNull(acc))
			throw new AccountException.AccountAlreadyExistException(
					acc.getLoginId() + " already exist! Try, use other loginID.");

		accounts.add(account);

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("account.obj"))) {
			oos.writeObject(accounts);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Account findById(String loginId) {
		return accounts.stream().filter(ac -> ac.getLoginId().equals(loginId)).findFirst().orElse(null);
	}

}
