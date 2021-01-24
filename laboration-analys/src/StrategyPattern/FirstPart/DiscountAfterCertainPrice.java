package StrategyPattern.FirstPart;
import StrategyPattern.FirstPart.StandAloneClass.ShoppingCart;
import StrategyPattern.FirstPart.StandAloneClass.ShoppingCartItem;
import java.math.BigDecimal;

/*
    * This concrete class handles the discount, if active, to give the client 10% after a certain price
*/
public class DiscountAfterCertainPrice implements DiscountStrategy {

    private ShoppingCart shoppingCart = new ShoppingCart();
    private BigDecimal result = BigDecimal.ZERO;
    private BigDecimal discount = BigDecimal.ZERO;
    private BigDecimal limit = new BigDecimal("500");
    private BigDecimal totalCost = shoppingCart.calculatePrice();

    @Override
    public BigDecimal calculateDiscount(Iterable<ShoppingCartItem> cart) {

            if (totalCost.compareTo(limit) >= 0){
                result = totalCost.multiply(new BigDecimal("0.9"));
                discount = totalCost.subtract(result);
            }else {
                result = totalCost;
            }

        System.out.println(" 'Discount after certain price' \n"
                + "\n     Discount received is: " + discount +  "kr \n     Total: " +  result + "kr");
        return result;
    }
}
