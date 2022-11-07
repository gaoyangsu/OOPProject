package Boundary;

import Boundary.MovieGoer.DisplayBookingHistoryBoundary;
import Boundary.MovieGoer.DisplayMovieListBoundary;
import static Controller.MiscMethods.*;
import static Controller.CustomerController.*;
import Entity.Customer;

/**
    Boundary/View Class representing logging in as a moviegoer member
    @version 1.0
    @since 2022-10-23
 */
public class MovieGoerMainLogin extends Boundary{
    /** boolean to denote if user has signed in */
    private boolean signedIn;
    /** userID */
    private String userId;
    
    /**
     * overriden start method from Boundary abstract class
     */
    @Override
    protected void start(){
        if(signedIn)displayMovieGoerView();
        else signin();
    }

    /**
     * For moviegoer to make selections for listing movies, or search past histories of transaction
     */
    private void displayMovieGoerView() {
        SupportFunctions.clearScreen();
        printHeader("MovieGoer");
        printMenu("Welcome, please make a selection:",
                "1. Search or list movies",
                "2. View booking history",
                "3. Back","");

        int choice = readChoice(1, 4);

        switch (choice) {
            case 1:
                direct(this, new DisplayMovieListBoundary(userId));
                end();
                break;
            case 2:
            	direct(this, new DisplayBookingHistoryBoundary(userId));
                end();
                break;
            case 3:
            	end();
                break;
        }
    }

    /**
     * Sign-in View for customer members
     */
    private void signin(){
        SupportFunctions.clearScreen();
        printHeader("MovieGoer");
        String user = readString("Enter Username (press enter to return)");
        if(user == "")end();
        this.userId=user;
        String password = readString("Enter Password");
        for(Customer customer : retrieveCustomerList()){
            if(user.equals(customer.getMovieGoerId()) && password.equals(customer.getMovieGoerPassword())){
                signedIn= true;
                setCurrentUser(customer);
            }
        }
        if(signedIn){displayMovieGoerView();}
        else {
            readString("Incorrect Details");
            end();
        }
    }
}
