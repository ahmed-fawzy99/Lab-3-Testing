
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static WatchFSM State;
    public static Normal_Inner_States nState;
    public static Alarm_Inner_States aState;
    public static Update_Inner_States uState;

    public enum WatchFSM {
        Normal_Display,
        Chime_Alarm_Set,
        Update
    }
    public enum Normal_Inner_States {
        Time,
        Date
    }
    public enum Alarm_Inner_States {
        Alarm,
        Chime
    }
    public enum Update_Inner_States {
        Min(0),
        Hour(0),
        Day(1),
        Month(1),
        Year(2000);

        public int value;
        Update_Inner_States(int value) {
            this.value = value;
        }
    }
    public static void timeLimitsChecker(){
        if (Update_Inner_States.Min.value > 59){
            Update_Inner_States.Min.value = 0;
            Update_Inner_States.Hour.value++;
        }
        if (Update_Inner_States.Hour.value > 23){
            Update_Inner_States.Hour.value = 0;
            Update_Inner_States.Day.value++;
        }
        if (Update_Inner_States.Day.value > 31){
            Update_Inner_States.Day.value = 0;
            Update_Inner_States.Month.value++;
        }
        if (Update_Inner_States.Month.value > 12){
            Update_Inner_States.Month.value = 0;
            Update_Inner_States.Year.value++;
        }
        if (Update_Inner_States.Year.value > 2100){
            // Reset State
            Update_Inner_States.Min.value = 0;
            Update_Inner_States.Hour.value = 0;
            Update_Inner_States.Day.value = 1;
            Update_Inner_States.Month.value = 1;
            Update_Inner_States.Year.value = 2000;
        }
    }
    public static void printTimeDate(){
        String Date = String.format("Current Date is %d/%d/%d - Current Time is %d:%d",
                Update_Inner_States.Year.value, Update_Inner_States.Month.value, Update_Inner_States.Day.value
                , Update_Inner_States.Hour.value, Update_Inner_States.Min.value) ;
        System.out.println(Date);
    }

    public static void testStates(String user_input){
        int aCount = 1;
        int chimeCount = 0;

        timeLimitsChecker(); // Respects time limits
        System.out.println("\nCurrent State is " + State.name());
        printTimeDate();
        System.out.print("Your input > ");

        switch(State){
            case Normal_Display:
                if (Objects.equals(user_input.toLowerCase(), "c")){
                    State = WatchFSM.Update;
                    //System.out.println("State is now " + State.name());
                }
                else if (Objects.equals(user_input.toLowerCase(), "b")){
                    State = WatchFSM.Chime_Alarm_Set;
                    //System.out.println("State is now " + State.name());
                }
                else if (Objects.equals(user_input.toLowerCase(), "a")){
                    if (nState == Normal_Inner_States.Time){
                        nState = Normal_Inner_States.Date;
                    } else {
                        nState = Normal_Inner_States.Time;
                    }
                    System.out.println("Inner State is now " + nState.name() + " inside Normal_Display");
                }
                else {
                    System.out.println("Incorrect Input. To quit and view final output enter 'quit'");
                }
                break;

            case Update:
                if (Objects.equals(user_input.toLowerCase(), "d")){
                    State = WatchFSM.Normal_Display;
                    //System.out.println("State is now " + State.name());
                }
                else if (Objects.equals(user_input.toLowerCase(), "b")){
                    uState.value++;
                }
                else if (Objects.equals(user_input.toLowerCase(), "a")){
                    if(aCount == 5){
                        State = WatchFSM.Normal_Display;
                        //System.out.println("State is now " + State.name());
                        aCount = 1;
                        break;
                    }
                    uState = Update_Inner_States.values()[aCount];
                    aCount++;
                    System.out.println("Inner State is now " + uState.name() + " inside Update State");
                }
                else {
                    System.out.println("Incorrect Input. To quit and view final output enter 'quit'");
                }
                break;

            case Chime_Alarm_Set:
                if (Objects.equals(user_input.toLowerCase(), "d")){
                    State = WatchFSM.Normal_Display;
                    //System.out.println("State is now " + State.name());
                }
                else if (Objects.equals(user_input.toLowerCase(), "a")){
                    if (chimeCount > 0){
                        System.out.println("Incorrect input. You are now at Chime Set. Enter 'd' to return to Normal Display Mode");
                        break;
                    }
                    aState = Alarm_Inner_States.Chime;
                    chimeCount++;
                    System.out.println("Inner State is now " + aState.name());
                }
                else {
                    System.out.println("Incorrect Input. To quit and view final output enter 'quit'");
                }
                break;
        }

    }

    public static void main(String []args){

        State = WatchFSM.Normal_Display; // Initial State
        nState = Normal_Inner_States.Time;
        aState = Alarm_Inner_States.Alarm;
        uState = Update_Inner_States.Min;

        System.out.print("Enter your input; [a, b, c, or d] or 'quit' to quit and view the final output:\n");
        Scanner scObj= new Scanner(System.in);



        int aCount = 1;
        int chimeCount = 0;

        while (true){
            timeLimitsChecker(); // Respects time limits
            System.out.println("\nCurrent State is " + State.name());
            printTimeDate();
            System.out.print("Your input > ");
            String user_input = scObj.nextLine();
            if (user_input.toLowerCase().equals("quit")){ break; }

            switch(State){
                case Normal_Display:
                    if (Objects.equals(user_input.toLowerCase(), "c")){
                        State = WatchFSM.Update;
                        //System.out.println("State is now " + State.name());
                    }
                    else if (Objects.equals(user_input.toLowerCase(), "b")){
                        State = WatchFSM.Chime_Alarm_Set;
                        //System.out.println("State is now " + State.name());
                    }
                    else if (Objects.equals(user_input.toLowerCase(), "a")){
                        if (nState == Normal_Inner_States.Time){
                            nState = Normal_Inner_States.Date;
                        } else {
                            nState = Normal_Inner_States.Time;
                        }
                        System.out.println("Inner State is now " + nState.name() + " inside Normal_Display");
                    }
                    else {
                        System.out.println("Incorrect Input. To quit and view final output enter 'quit'");
                    }
                    break;

                case Update:
                    if (Objects.equals(user_input.toLowerCase(), "d")){
                        State = WatchFSM.Normal_Display;
                        //System.out.println("State is now " + State.name());
                    }
                    else if (Objects.equals(user_input.toLowerCase(), "b")){
                        uState.value++;
                    }
                    else if (Objects.equals(user_input.toLowerCase(), "a")){
                        if(aCount == 5){
                            State = WatchFSM.Normal_Display;
                            //System.out.println("State is now " + State.name());
                            aCount = 1;
                            break;
                        }
                        uState = Update_Inner_States.values()[aCount];
                        aCount++;
                        System.out.println("Inner State is now " + uState.name() + " inside Update State");
                    }
                    else {
                        System.out.println("Incorrect Input. To quit and view final output enter 'quit'");
                    }
                    break;

                case Chime_Alarm_Set:
                    if (Objects.equals(user_input.toLowerCase(), "d")){
                        State = WatchFSM.Normal_Display;
                        //System.out.println("State is now " + State.name());
                    }
                    else if (Objects.equals(user_input.toLowerCase(), "a")){
                        if (chimeCount > 0){
                            System.out.println("Incorrect input. You are now at Chime Set. Enter 'd' to return to Normal Display Mode");
                            break;
                        }
                        aState = Alarm_Inner_States.Chime;
                        chimeCount++;
                        System.out.println("Inner State is now " + aState.name());
                    }
                    else {
                        System.out.println("Incorrect Input. To quit and view final output enter 'quit'");
                    }
                    break;
            }
        }

        System.out.println("\n\nFinal Output is:");
        System.out.println("Final State is " + State.name());
        printTimeDate();
        System.out.println("Thanks!");



    }
}
