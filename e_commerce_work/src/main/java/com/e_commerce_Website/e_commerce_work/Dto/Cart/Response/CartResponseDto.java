package com.e_commerce_Website.e_commerce_work.Dto.Cart.Response;

import com.e_commerce_Website.e_commerce_work.Model.Cart;
import com.e_commerce_Website.e_commerce_work.Model.CartItem;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDto {

    private Long cartId;
    private Long userId;
    private List<CartItemResponseDto> items;
    private BigDecimal totalPrice;

    // Constructor to build DTO from Cart entity
    public CartResponseDto(Cart cart) {
        this.cartId = cart.getId();
        this.userId = cart.getUser().getId();
        this.totalPrice = cart.getTotalPrice();
        if (cart.getCartItems() != null) {
            this.items = cart.getCartItems()
                    .stream()
                    .map(CartItemResponseDto::new)
                    .collect(Collectors.toList());
        }
    }
}
