package Boundary.Staff;
import Boundary.Boundary;
import Entity.Admin;
import static Controller.AdminController.*;
import static Controller.MiscMethods.*;

import java.io.IOException;
import java.util.Scanner;

/**
    Boundary/View Class representing registration of accounts for the Admin
    @version 1.0
    @since 2022-11-01
 */
public class DisplayAdminRegiserBoundary extends Boundary{
    /**
     * overriden start method from Boundary abstract class
     */
    @Override
    protected void start() {
        display();
    }

    /** method to display to the user to register for an account for Admin */
    private void display(){
        printHeader("Registration (Insert any empty field to cancel)");
        String user = readString("Enter name:");
        String password = readString("Enter Password:");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Contact: ");
        int contact = sc.nextInt();
        sc.nextLine();
        String email = readString("Enter Email:");
        String loginID = readString("Enter Login ID:");
        
        if(user == ""||password ==""||email==""||loginID=="")end();
        for(Admin admin : retrieveAdminList()){
            if(loginID.equals(admin.getAdminId())){
                readString("AdminID taken");
                end();
            }
        }
        Admin admin = new Admin(user, email, contact);
        admin.setAdminPassword(password);
        admin.setAdminId(loginID);
        try{addAdminIntoList(admin);}catch(IOException e){}
        readString("Account Successfully Created!");
        end();
    }
}
