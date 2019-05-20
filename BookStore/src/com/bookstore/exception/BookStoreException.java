package com.bookstore.exception;

public class BookStoreException {
	
	private BookStoreException() {
	}

	public static class BookNotFoundException extends BaseException{

		private static final long serialVersionUID = 1L;

		public BookNotFoundException(String message) {
			super(message);
		}
	}
	
	public static class InvalidBookIDException extends BaseException{

		private static final long serialVersionUID = 1L;

		public InvalidBookIDException(String message) {
			super(message);
		}
	}
	
	public static class InvalidOptionCodeException extends BaseException{

		private static final long serialVersionUID = 1L;

		public InvalidOptionCodeException(String message) {
			super(message);
		}
		
	}
}
