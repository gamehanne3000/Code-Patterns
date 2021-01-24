package StrategyPattern.FirstPart;
import StrategyPattern.FirstPart.StandAloneClass.ShoppingCart;
import java.util.Scanner;

/*
 * This class tells the context (Checkout) to change its strategy.
 */
public class CartClient {

    private static ShoppingCart shoppingCart = new ShoppingCart();

    public static void main(String[] args) {
        presentUserWithInfo();
        String line = "--------------";

        // Implementation of Scanner
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            int select = 0;

            if (input.hasNextInt()) {
                select = input.nextInt();
            }
            switch (select) {

                case 1:
                    /*
                     * The first discount listens for a offer, that when a certain amount off products has been reached -> give the cheapest product for free !
                     */
                    CheckoutContext checkout = new CheckoutContext(new DiscountOverCertainAmountOfProducts());
                    checkout.ShoppingCart(shoppingCart);
                    System.out.println("\n" + line);
                    break;

                case 2:
                    /*
                     * The second discount listens for a offer, that when a certain price has been reached -> give 10 % off
                     */
                    checkout = new CheckoutContext(new DiscountAfterCertainPrice());
                    checkout.ShoppingCart(shoppingCart);
                    System.out.println("\n" + line);
                    break;

                case 3:
                    /*
                     * The third discount listens for a offer, that when three equal objects is bought -> give 1 for free !
                     */
                    checkout = new CheckoutContext(new ThreeForTwo());
                    checkout.ShoppingCart(shoppingCart);
                    System.out.println("\n" + line);
                    break;
            }
        }
    }

    public static void presentUserWithInfo() {
        String line = "-----------------------------------------------------------------";
        String header = " Item         Price     Quantity      Total cost per item";

        System.out.println(" \n" + "You will no get 3 offers to choose from, and they all resign from the same cart");
        System.out.println(" - To choose, please enter in the number 1 to 3");
        System.out.println("-----------------------------------------------------------------");
        System.out.println(" \n" + "    1 = First discount is:      'Discount over certain amount of products'");
        System.out.println("    2 = Second discount is:     'Discount after certain price'");
        System.out.println("    3 = Third discount is:      'Three for two'");
        System.out.println(" \n" + header + "\n" + line);

        shoppingCart.receipt();
        System.out.println(line + " \n                                      Total: " + shoppingCart.calculatePrice());
    }
}

