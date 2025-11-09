package com.e_commerce_Website.e_commerce_work.Dto.Cart.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for adding or updating items in the cart.
 * Represents one product and its quantity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequestDto {

    /**
     * The ID of the product to be added to the cart.
     */
    @NotNull(message = "Product ID cannot be null")
    private Long productId;

    /**
     * Quantity of the product to be added or updated.
     * Must be at least 1.
     */
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
