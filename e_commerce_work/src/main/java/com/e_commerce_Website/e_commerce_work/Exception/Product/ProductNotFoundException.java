package com.e_commerce_Website.e_commerce_work.Exception.Product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
    public ProductNotFoundException(String message, Long id) {
        super(message + " " + id + " not found");
    }
    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
