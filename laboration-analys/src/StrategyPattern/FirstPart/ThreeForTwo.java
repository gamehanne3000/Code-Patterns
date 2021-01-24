package StrategyPattern.FirstPart;
import StrategyPattern.FirstPart.StandAloneClass.ShoppingCart;
import StrategyPattern.FirstPart.StandAloneClass.ShoppingCartItem;

import java.math.BigDecimal;

/*
    * This concrete class take 3 items and subtracts one from the total price.
*/
public class ThreeForTwo implements DiscountStrategy {

    private ShoppingCart shoppingCart = new ShoppingCart();
    private BigDecimal discount = BigDecimal.ZERO;
    private BigDecimal subTotal = BigDecimal.ZERO;

    @Override
    public BigDecimal calculateDiscount(Iterable<ShoppingCartItem> cart) {
        System.out.println(" 'Three for two' \n");

        for (var item: cart) {
            var itemsLeftToPayFor = item.quantity() - (item.quantity() / 3); //     12 / 3 = 4
            var itemsThatIsForFree = item.quantity() - itemsLeftToPayFor;

            if (item.quantity() >= 3) {
                discount = BigDecimal.valueOf(itemsThatIsForFree).multiply(item.itemCost()); //   4 * price =
                System.out.println(" >>> 3 for 2 on " + item.product().getItem() + "    -> discount: - " + discount +  "kr");
                subTotal = discount.add(subTotal);
            }
        }
        System.out.println("\n     Discount received is: " + subTotal +  "kr \n     Total: " +  shoppingCart.calculatePrice().subtract(subTotal) + "kr");
        return discount;
    }
}





