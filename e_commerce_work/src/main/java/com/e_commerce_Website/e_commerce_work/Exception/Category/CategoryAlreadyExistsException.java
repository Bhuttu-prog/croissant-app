package com.e_commerce_Website.e_commerce_work.Exception.Category;

public class CategoryAlreadyExistsException extends RuntimeException {
    private final String name;
    public CategoryAlreadyExistsException(String message, String name) {
        super(message);
        this.name = name;
    }
    public String getName() {
        return name;
    }
   

}
