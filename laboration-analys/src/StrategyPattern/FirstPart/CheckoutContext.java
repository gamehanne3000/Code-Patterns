package StrategyPattern.FirstPart;
import StrategyPattern.FirstPart.StandAloneClass.ShoppingCartItem;

/*
    * This class Takes care of the context and change the behaviour accordingly
*/
public class CheckoutContext {
    // Get the interface
    DiscountStrategy strategy;

    // This method determines which algorithm that should be used
    public CheckoutContext(DiscountStrategy strategy){
        this.strategy = strategy;
    }

    // This method is responsible to executes the clients call
    // This method takes the interface and implements the argument which comes from the operation entered inside the "CartClient"
    public void ShoppingCart(Iterable<ShoppingCartItem> eachItem){
        strategy.calculateDiscount(eachItem);
    }
}




