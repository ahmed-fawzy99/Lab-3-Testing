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
        // First deposit 100 and ensure it is recorded
        MainTest.deposit(100);
        Assertions.assertEquals(100, MainTest.current_balance);

        // Then deposit 6000 and ensure it is incremented to previous deposit
        MainTest.deposit(6000);
        Assertions.assertEquals(6100, MainTest.current_balance);

        // Attempt to withdraw 5000 EGP. Balance should be 1100
        MainTest.withdraw(5000);
        Assertions.assertEquals(1100, MainTest.current_balance);

        // Attempt to withdraw 1 EGP. Operation should fail because the daily withdrawal limit is 5000 EGP
        // Fail code of withdrawal limit is -1
        Assertions.assertEquals(-1, MainTest.withdraw(1));
        // Make sure the balance is still 1100
        Assertions.assertEquals(1100, MainTest.current_balance);

        // Finally, Attempt to send 1101 EGP to another account. The operation should fail as there's no enough balance
        Assertions.assertEquals(-1, MainTest.sendMoneyToAccount("12312321", 1101));
        // Make sure the balance is still 1100
        Assertions.assertEquals(1100, MainTest.current_balance);

        // Now, Attempt to send 1100 EGP instead. The operation should succeed. Success code is '0'
        Assertions.assertEquals(0, MainTest.sendMoneyToAccount("12312321", 1100));
        // Make sure the balance is now 0 as we have sent all our funds to another account
        Assertions.assertEquals(0, MainTest.current_balance);

    }
}