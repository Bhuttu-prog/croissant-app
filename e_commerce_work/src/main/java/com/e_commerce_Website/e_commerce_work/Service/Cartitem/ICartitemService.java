package com.e_commerce_Website.e_commerce_work.Service.Cartitem;

import java.util.List;

import com.e_commerce_Website.e_commerce_work.Dto.Cart.Request.CartItemRequestDto;
import com.e_commerce_Website.e_commerce_work.Dto.Cart.Response.CartItemResponseDto;

public interface ICartitemService {
 
    CartItemResponseDto addProductToCartItem( List<CartItemRequestDto> cartItems);


    CartItemResponseDto updateCartItem(Long cartItemId, Integer quantity);

    void deleteCartItem(Long cartItemId);

    CartItemResponseDto getCartItemById(Long cartItemId);

    List<CartItemResponseDto> getAllCartItems( Long userId);


    
} 