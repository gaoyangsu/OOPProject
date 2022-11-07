package Boundary;
import Controller.AdminController;
import Controller.CRUDCustomerBooking;
import Controller.CRUDMovies;
import Controller.CRUDShowSchedule;
import Controller.CRUDTheatre;
import Controller.CustomerController;

import java.util.*;

import Boundary.MovieGoer.DisplayMovieGoerRegisterBoundary;

import static Controller.MiscMethods.*;



public class MoblimaMain extends Boundary {
    public static void main(String[] args){
        
        new MoblimaMain().start();
    }
    CRUDCustomerBooking customerBooking = new CRUDCustomerBooking();
    CRUDMovies movies = new CRUDMovies();
    CRUDShowSchedule showSchedule = new CRUDShowSchedule();
    CRUDTheatre theatre = new CRUDTheatre();
    AdminController admin = new AdminController();
    CustomerController moviegoer = new CustomerController();
    @Override
    protected void start() {

        boolean initialized= (
        customerBooking.initialise() &&
        movies.initialise() &&
        showSchedule.initialise() &&
        theatre.initialise() &&
        admin.initialise() &&
        moviegoer.initialise()
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

