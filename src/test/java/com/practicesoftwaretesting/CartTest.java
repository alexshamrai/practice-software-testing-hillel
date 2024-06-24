package com.practicesoftwaretesting;

import com.practicesoftwaretesting.cart.CartController;
import com.practicesoftwaretesting.cart.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {

    private String authToken;

    private static final String PRODUCT_ID = "01J15FKQMKJK5P57MMMMM83DZB";

    CartController cartController = new CartController();

    @BeforeEach
    void beforeEach() {
        authToken = registerAndLoginNewUser();
    }

    @Test
    void createUpdateAndDeleteCart() {
        var createdCart = cartController.withToken(authToken).createCart()
                .assertStatusCode(201)
                .as();
        assertNotNull(createdCart.getId());

        var cartId = createdCart.getId();
        var updateCartResponse = cartController.addItemToCart(cartId, new AddCartItemRequest(PRODUCT_ID, 1))
                .assertStatusCode(200)
                .as();
        assertNotNull(updateCartResponse.getResult());

        var cartDetails = cartController.getCart(cartId)
                .assertStatusCode(200)
                .as();
        var productIds = cartDetails.getCartItems().stream().map(CartItem::getProductId).toList();
        assertTrue(productIds.contains(PRODUCT_ID));

        cartController.deleteCart(cartId)
                .assertStatusCode(204);
    }
}
