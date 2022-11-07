package Boundary;
import Controller.AdminController;
import Controller.CRUDCustomerBooking;
import Controller.CRUDMovies;
import Controller.CRUDShowSchedule;
import Controller.CRUDTheatre;
import Controller.CustomerController;
import Controller.Initialiser;

import java.util.*;

import Boundary.MovieGoer.DisplayMovieGoerRegisterBoundary;

import static Controller.MiscMethods.*;


/**
    Boundary/View Class representing a the main menu that kickstarts the app
    @version 1.0
    @since 2022-10-23
 */
public class MoblimaMain extends Boundary {
    
    /** 
     * this method is used to start the entire application
     * @param args
     */
    public static void main(String[] args){
        
        new MoblimaMain().start();
    }

    /** 
     * start method implemented from {@code Boundary} interface
     * to show the listing of mainmenu
     * @param args
     */
    @Override
    protected void start() {
        Initialiser A = new CRUDCustomerBooking();
        Initialiser B = new CRUDMovies();
        Initialiser C = new CRUDShowSchedule();
        Initialiser D = new CRUDTheatre();
        Initialiser E = new AdminController();
        Initialiser F = new CustomerController();
        boolean initialized= (
        A.initialise() &&
        B.initialise() &&
        C.initialise() &&
        D.initialise() &&
        E.initialise() &&
        F.initialise()
        );
        if (!initialized) {
            System.out.println("Error: failed to read data, please check file integrity.");
            System.out.println("Application terminating...");
            return;
        }
        
        SupportFunctions.clearScreen();
        SupportFunctions.printMoblima();
        printHeader("MOBLIMA Booking and Modifying Application");
        printMenu("User/Admin Page",
                "1. I'm a moviegoer",
                "2. I'm a staff",
                "3. I want to register as a moviegoer!",
                "4. End","");

        int choice = readChoice(1, 4);

        switch(choice) {
            case 1:
                //SupportFunctions.clearScreen();
                direct(this, new MovieGoerSelection());
                end();
                break;
            case 2:
                //SupportFunctions.clearScreen();
                direct(this, new StaffMain());
                end();
                break;
            case 3:
                direct(this, new DisplayMovieGoerRegisterBoundary());
                break;
            case 4:
                System.out.println("Goodbye!");
                end();
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }
}

