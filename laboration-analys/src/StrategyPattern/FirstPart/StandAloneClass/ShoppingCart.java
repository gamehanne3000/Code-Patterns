package StrategyPattern.FirstPart.StandAloneClass;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

public class ShoppingCart implements Iterable<ShoppingCartItem> {

    private final ArrayList<ShoppingCartItem> items = new ArrayList<>();
    private final Stack<ShoppingCartItem> shoppingCartStack = new Stack<>();

    // All discounts strategies shares the same cart as the context listens from the clients call which can be found in the class "Products"
    public Iterable<ShoppingCartItem> products() {
        addCartItem(new ShoppingCartItem(new Product("Milk    "), 19.99, 1));
        addCartItem(new ShoppingCartItem(new Product("Water   "), 10,    2));
        addCartItem(new ShoppingCartItem(new Product("Redbull "), 13.95, 24));
        addCartItem(new ShoppingCartItem(new Product("Coke    "), 6.99,  1));
        addCartItem(new ShoppingCartItem(new Product("Oranges "), 6.0,   3));
        addCartItem(new ShoppingCartItem(new Product("Meat    "), 89.0,  1));
        addCartItem(new ShoppingCartItem(new Product("Butter  "), 44.95, 1));
        return items;
    }

    public ShoppingCart() {
        // Supply the list
        products();
    }

    public void addCartItem(ShoppingCartItem item){
        this.items.add(item);
        this.shoppingCartStack.addAll(items);
    }

    public Stream<ShoppingCartItem> stream(){
        return this.items.stream();
    }

    public Iterator<ShoppingCartItem> iterator() {
        return items.iterator();
    }

    public BigDecimal calculatePrice(){
        var sum = BigDecimal.ZERO;

        for (var item: items) {
            sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum);
        }
        return sum;
    }

    // Add the latest change to the ShoppingCart
    public void undo(Stack cart){
        if (!shoppingCartStack.isEmpty() && cart.size() <= items.size()) {
            cart.push(shoppingCartStack.pop());
            System.out.println(" UndoCart size(): " + cart.size() + " -> " + cart);
        }
    }

    // Redo the latest change to the ShoppingCart
    public void redo(Stack redoState, Stack undoState){
        if (!shoppingCartStack.isEmpty()) {
            redoState.push(undoState.pop());
            System.out.println("\n UndoState size():     " + undoState.size() + " -> " + undoState);
            System.out.println("     RedoCart size():  " + redoState.size() + " -> " + redoState);
        }
    }

    public void receipt() {
        // Loop trough every item and print them
        items.forEach(cart -> {
            System.out.println(" " + cart.product().getItem() + "     "
                                   + cart.itemCost() + "        "
                                   + cart.quantity() + "                "
                                   + cart.itemCost().multiply(BigDecimal.valueOf(cart.quantity())));
        });
    }
}

/*

    // This returns a list from cheapest to most expensive.
    public BigDecimal getList (Iterable<ShoppingCartItem> i) {





        return bigDecimal;
    }


 */