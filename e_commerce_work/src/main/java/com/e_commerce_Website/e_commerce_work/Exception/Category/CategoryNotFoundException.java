package com.e_commerce_Website.e_commerce_work.Exception.Category;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
    public CategoryNotFoundException(String message, Long id) {
        super(message + " " + id + " not found");
    }
    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
   
}
