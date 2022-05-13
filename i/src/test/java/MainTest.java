import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import static org.junit.Assert.*;


public class MainTest {

    Main MainTest;

    @BeforeEach
    void setUp() {
        MainTest = new Main();
    }

    @Test
    public void test() {
        // Testing adding coffee to order
        MainTest.requestCoffee(MainTest.coffeeMenu[0], MainTest.coffeePrices[0]); // Total is now 14
        MainTest.requestCoffee(MainTest.coffeeMenu[1], MainTest.coffeePrices[1]); // Total is now 14 + 11 = 25
        Assertions.assertEquals(25, MainTest.dueAmount);

        // Testing money deposit functionality
        MainTest.requestMoney__NO_INPUT(15);
        Assertions.assertEquals(15, MainTest.balance);
        MainTest.requestMoney__NO_INPUT(9);
        Assertions.assertEquals(24, MainTest.balance);

        // Try to pay total of 25 with balance of 24.. This should be false
        Assertions.assertFalse(MainTest.pay(MainTest.dueAmount));

        // Now add deposit the remaining 1 EGP and try to pay again
        MainTest.requestMoney__NO_INPUT(1); // Balance is now = Total, and we can pay for the order.
        Assertions.assertTrue(MainTest.pay(MainTest.dueAmount));

        // Make sure that the balance after payment is deducted
        Assertions.assertEquals(0, MainTest.balance);
    }

}