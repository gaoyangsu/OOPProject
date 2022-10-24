package Boundary;
import java.util.*;
import static Controller.MiscMethods.*;


public class MoblimaMain extends Boundary {
    public static void main(String[] args){
        new MoblimaMain().start();
    }

    @Override
    protected void start() {
        printHeader("Movie Booking and LIsting Management Application (MOBLIMA)");
        printMenu("Welcome to MOBLIMA, please make a selection:",
                "1. I'm a moviegoer",
                "2. I'm a staff",
                "3. End","");

        int choice = readChoice(1, 3);

        switch(choice) {
            case 1:
                SupportFunctions.clearScreen();
                direct(this, new MovieGoerMain());
                break;
            case 2:
                SupportFunctions.clearScreen();
                direct(this, new StaffMain());
                break;
            case 3:
                System.out.println("Goodbye!");
                end();
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }
}

