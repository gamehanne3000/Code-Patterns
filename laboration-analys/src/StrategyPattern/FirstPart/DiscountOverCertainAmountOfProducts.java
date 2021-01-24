package StrategyPattern.FirstPart;
import StrategyPattern.FirstPart.StandAloneClass.ShoppingCart;
import StrategyPattern.FirstPart.StandAloneClass.ShoppingCartItem;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/*
    * This concrete class deducts the price of the cheapest product.
*/
public class DiscountOverCertainAmountOfProducts implements DiscountStrategy {

    private ShoppingCart shoppingCart = new ShoppingCart();
    private BigDecimal result = BigDecimal.ZERO;
    private BigDecimal totalCost = BigDecimal.ZERO;
    private int totalQuantity;

    @Override
    public BigDecimal calculateDiscount(Iterable<ShoppingCartItem> cart) {

        for (ShoppingCartItem item : cart) {
            // Calculating the total cost and the total quantity
            totalCost = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(totalCost);
            totalQuantity += item.quantity();

            // Discount is active
            if (totalQuantity >= 20) {
                result = totalCost.subtract(cheapestItem());
            }
        }
        System.out.println(" 'Discount over certain amount of products' \n"
                + "\n     Discount received is: " + totalCost.subtract(result) +  "kr \n     Total: " +  result + "kr");
        return result;
    }

    // Return the Cheapest item that later can be used to count the discounted price aka result
    public BigDecimal cheapestItem (){

        // Sort list and return first item in a new list
        Iterable<ShoppingCartItem> sortedList = shoppingCart.stream().sorted(Comparator.comparing(ShoppingCartItem::itemCost)).collect(Collectors.toList());

        var firstIndex = sortedList.iterator().next(); // first index

        // Return total price from first item
        return firstIndex.itemCost().multiply(BigDecimal.valueOf(firstIndex.quantity()));
    }
}
