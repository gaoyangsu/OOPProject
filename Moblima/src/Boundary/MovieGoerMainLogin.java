package Boundary;

import Boundary.MovieGoer.DisplayBookingHistoryBoundary;
import Boundary.MovieGoer.DisplayMovieListBoundary;
import static Controller.MiscMethods.*;
import static Controller.CustomerController.*;
import Entity.Customer;
public class MovieGoerMainLogin extends Boundary{
    private boolean signedIn;
    private String userId;
    
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
    private void signin(){
        SupportFunctions.clearScreen();
        printHeader("Moviegoer");
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
