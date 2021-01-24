package StrategyPattern.FirstPart.StandAloneClass;

import java.math.BigDecimal;

public class ShoppingCartItem {

    private BigDecimal itemCost;
    private int quantity;
    private final Product product;

    public ShoppingCartItem(Product product, double itemCost, int quantity) {
        this.itemCost = BigDecimal.valueOf(itemCost);
        this.product = product;
        this.quantity = quantity;
    }

    public int quantity(){
        return quantity;
    }

    public Product product() {
        return product;
    }

    public BigDecimal itemCost() {
        return itemCost;
    }

    @Override
    public String toString() {
        return (" " + product().getItem() + "      " + quantity() + "      " + itemCost() );
    }
}
