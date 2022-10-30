package Boundary;

import static Controller.MiscMethods.*;
import static Controller.CRUDMovies.*;
import static Controller.AdminController.*;
import Boundary.Staff.ModifyTheatreBoundary;
import Boundary.Staff.MovieListingEditBoundary;
import Boundary.Staff.ModifySystemSettingsBoundary;
import Entity.Admin;
import Entity.Movie;

public class StaffMain extends Boundary {
    private boolean signedIn;
    @Override
    protected void start(){
        if(signedIn)displayAdminView();
        else signin();
    }
    private void displayAdminView() {
        SupportFunctions.clearScreen();
        //NEED TO DO LOG IN PAGE.. this one is just for testing
        printHeader("Staff");
        printMenu("Welcome, please make a selection:",
                "1. Modify Movie Listing",
                "2. Modify Theatres",
                "3. Modify System Settings",
                "4. Back","");

        int choice = readChoice(1, 4);

        switch (choice) {
            case 1:
                direct(this, new MovieListingEditBoundary());
                end();
                break;
            case 2:
                direct(this, new ModifyTheatreBoundary());
                end();
                break;
            case 3:
                direct(this, new ModifySystemSettingsBoundary());
                break;
            case 4:
                end();
                break;
        }
    }
    private void signin(){
        SupportFunctions.clearScreen();
        printHeader("Staff");
        String user = readString("Enter Username (press enter to return)");
        if(user == "")end();
        String password = readString("Enter Password");
        for(Admin admin : retrieveAdminList()){
            if(user.equals(admin.getAdminId()) && password.equals(admin.getAdminPassword())){
                signedIn= true;
            }
        }
        if(signedIn){displayAdminView();}
        else {
            readString("\nUsername or Password is incorrect!");
            end();
        }
    }
    };
