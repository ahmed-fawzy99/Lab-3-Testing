
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

public class MainTest {

    Main MainTest;

    @BeforeEach
    void setUp() {
        MainTest = new Main();

        MainTest.State = Main.WatchFSM.Normal_Display; // Initial State
        MainTest.nState = Main.Normal_Inner_States.Time;
        MainTest.aState = Main.Alarm_Inner_States.Alarm;
        MainTest.uState = Main.Update_Inner_States.Min;
    }

    @Test
    public void test1() {
        // Test the path ab - Result should be:
        //                                  State = CHIME_ALARM_SET
        //                                  Inner_State = ALARM
        MainTest.testStates("a");
        MainTest.testStates("b");

        // Assert State is in CHIME_ALARM_SET
        Assertions.assertEquals(MainTest.State, Main.WatchFSM.Chime_Alarm_Set);
        // Assert Inner State is in ALARM
        Assertions.assertEquals(MainTest.aState, Main.aState.Alarm);
    }

    @Test
    public void test2() {

        // Test the path aaaca - Result should be:
        //                                  State = UPDATE
        //                                  Inner_State = HOUR
        MainTest.testStates("a");
        MainTest.testStates("a");
        MainTest.testStates("a");
        MainTest.testStates("c");
        MainTest.testStates("a");
        // Assert State is in Update
        Assertions.assertEquals(MainTest.State, Main.WatchFSM.Update);
        // Assert Inner State is in Hour
        Assertions.assertEquals(MainTest.uState, Main.uState.Hour);


    }
}