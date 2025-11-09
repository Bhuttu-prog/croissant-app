package com.e_commerce_Website.e_commerce_work.Exception.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
    public ProductAlreadyExistsException(String message, String name) {

        super(message + " " + name + " already exists");
    }
    public ProductAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public ProductAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
