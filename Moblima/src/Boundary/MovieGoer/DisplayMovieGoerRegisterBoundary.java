package Boundary.MovieGoer;

import Boundary.Boundary;
import Entity.Customer;
import static Controller.CustomerController.*;
import static Controller.MiscMethods.*;

import java.io.IOException;
import java.util.Scanner;

/**
    Boundary/View Class for moviegoers to register an account 
    in order to log in as a member
    @version 1.0
    @since 2022-11-01
 */
public class DisplayMovieGoerRegisterBoundary extends Boundary{
    
    /**
     * overriden start method from Boundary abstract class
     */
    @Override
    protected void start() {
        display();
    }

    /** method to display the registration view page to the users */
    private void display(){
        printHeader("Registration (Insert any empty field to cancel)");
        String user = readString("Enter name:");
        if (user.equals("")) end();
        String password = readString("Enter Password:");
        if (password.equals("")) end();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Contact: ");
        int contact = sc.nextInt();
        sc.nextLine();
        String email = readString("Enter Email:");
        if (email.equals("")) end();
        String loginID = readString("Enter Login ID:");
        if (loginID.equals("")) end();
        
        for(Customer customer : retrieveCustomerList()){
            if(loginID.equals(customer.getMovieGoerId())){
                readString("UserID taken");
                end();
            }
        }
        Customer customer = new Customer(user, email, contact);
        customer.setMovieGoerPassword(password);
        customer.setMovieGoerId(loginID);
        try{addCustomerIntoList(customer);}catch(IOException e){}
        System.out.println("Account Successfully Created!");
        end();
    }
}
