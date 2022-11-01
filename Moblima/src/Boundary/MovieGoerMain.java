package Boundary;

import Boundary.MovieGoer.DisplayMovieListBoundary;
import Boundary.MovieGoer.MakeBookingBoundary;
import Entity.Customer;
import static Controller.CustomerController.*;
import static Controller.MiscMethods.*;

public class MovieGoerMain extends Boundary{
    private boolean signedIn;
    @Override
    protected void start(){
        if(signedIn)displayMovieGoerView();
        else signin();
    }
    private void displayMovieGoerView() {
        SupportFunctions.clearScreen();
        printHeader("Moviegoer");
        printMenu("Welcome, please make a selection:",
                "1. Search or list movies",
                "2. Make a booking",
                "3. View booking history",
                "4. Back","");

        int choice = readChoice(1, 3);

        switch (choice) {
            case 1:
                direct(this, new DisplayMovieListBoundary());
                //PrintAllMovieNames();
                //direct(this, new MovieGoerMain());
                break;
            case 2:
                direct(this, new MakeBookingBoundary());
                end();
                break;
            case 3:
            	//direct(this, new DisplayBookingHistoryBoundary());
                //direct(this, new MovieGoerMain());
                end();
                break;
            case 4:
            	end();
                break;
        }
    }
    private void signin(){
        SupportFunctions.clearScreen();
        printHeader("Moviegoer");
        String user = readString("Enter Username (press enter to return)");
        if(user == "")end();
        String password = readString("Enter Password");
        for(Customer customer : retrieveCustomerList()){
            if(user.equals(customer.getMovieGoerId()) && password.equals(customer.getMovieGoerPassword())){
                signedIn= true;
            }
        }
        if(signedIn){displayMovieGoerView();}
        else {
            readString("Incorrect Details");
            end();
        }
    }
}
