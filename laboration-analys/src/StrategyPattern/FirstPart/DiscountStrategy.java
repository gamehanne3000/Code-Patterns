package StrategyPattern.FirstPart;
import StrategyPattern.FirstPart.StandAloneClass.ShoppingCartItem;

import java.math.BigDecimal;

/*
    * The interface cares for an ArrayList that will hold several object -> like a cart of products
*/
public interface DiscountStrategy {
    BigDecimal calculateDiscount(Iterable<ShoppingCartItem> cart);
}

