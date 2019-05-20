package com.bookstore.exception;

public class AccountException {

	private AccountException() {
	}

	public static class AccountNotFoundException extends BaseException {

		private static final long serialVersionUID = 1L;

		public AccountNotFoundException(String message) {
			super(message);
		}

	}

	public static class AccountAlreadyExistException extends BaseException {

		private static final long serialVersionUID = 1L;

		public AccountAlreadyExistException(String message) {
			super(message);
		}
	}
	
	public static class WrongPasswordException extends BaseException{

		private static final long serialVersionUID = 1L;

		public WrongPasswordException(String message) {
			super(message);
		}
		
	}
}
