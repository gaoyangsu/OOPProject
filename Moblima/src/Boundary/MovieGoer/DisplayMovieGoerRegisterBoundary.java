package Boundary.MovieGoer;

import Boundary.Boundary;
import Entity.Customer;
import static Controller.CustomerController.*;
import static Controller.MiscMethods.*;

import java.io.IOException;
import java.util.Scanner;
public class DisplayMovieGoerRegisterBoundary extends Boundary{
    @Override
    protected void start() {
        display();
    }
    private void display(){
        printHeader("REGISTRATION (INSERT ANY EMPTY FIELD TO CANCEL");
        String user = readString("Enter name:");
        String password = readString("Enter Password:");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Contact: ");
        int contact = sc.nextInt();
        sc.nextLine();
        String email = readString("Enter Email:");
        String loginID = readString("Enter Login ID:");
        
        if(user == ""||password ==""||email==""||loginID=="")end();
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
        readString("Account Sucessfully Created!");
        end();
    }
}
