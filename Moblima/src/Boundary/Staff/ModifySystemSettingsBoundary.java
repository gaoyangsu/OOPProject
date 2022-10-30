package Boundary.Staff;
import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.*;
import static Controller.CRUDMovies.*;
import static Controller.CRUDTheatre.*;
import static Controller.MiscMethods.*;
import static Controller.AdminController.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
public class ModifySystemSettingsBoundary extends Boundary {

    @Override
    protected void start() {
        display();
    }
    private void display(){
        SupportFunctions.clearScreen();
        printHeader("Modify System Setting");
        printMenu("Select Choice",
        "1.Holidays Ticket Increment",
        "2.Child Ticket Discount",
        "3.Senior Ticket Discount",
        "4.Premium Ticket Price",
        "5.Standard Ticket Price",
        "6.3D Ticket Increment",
        "7.BlockBuster Ticket Increment",
        "8.Weekend Ticket Increment",
        "9.Add/Remove Holidays",
        "10.Save/Back");
        int choice = readChoice(1, 10);
        switch(choice){
            case 1:
            System.out.printf("Current Price: ");
            System.out.println(retrieveSystemSettings().getHolidaysIncrement());
            retrieveSystemSettings().setHolidaysIncrement(readDouble("New Price:"));
            display();
            break;
            case 2:
            System.out.printf("Current Price: ");
            System.out.println(retrieveSystemSettings().getChildDiscount());
            retrieveSystemSettings().setChildDiscount(readDouble("New Price:"));
            display();
            break;
            case 3:
            System.out.printf("Current Price: ");
            System.out.println(retrieveSystemSettings().getSeniorCitizenDiscount());
            retrieveSystemSettings().setSeniorCitizenDiscount(readDouble("New Price:"));
            display();
            break;
            case 4:
            System.out.printf("Current Price: ");
            System.out.println(retrieveSystemSettings().getPremiumPrice());
            retrieveSystemSettings().setPremiumPrice(readDouble("New Price:"));
            display();
            break;
            case 5:
            System.out.printf("Current Price: ");
            System.out.println(retrieveSystemSettings().getStandardPrice());
            retrieveSystemSettings().setStandardPrice(readDouble("New Price:"));
            display();
            break;
            case 6:
            System.out.printf("Current Price: ");
            System.out.println(retrieveSystemSettings().getThreeDIncrement());
            retrieveSystemSettings().setThreeDIncrement(readDouble("New Price:"));
            display();
            break;
            case 7:
            System.out.printf("Current Price: ");
            System.out.println(retrieveSystemSettings().getBlockBusterIncrement());
            retrieveSystemSettings().setBlockBusterIncrement(readDouble("New Price:"));
            display();
            break;
            case 8:
            System.out.printf("Current Price: ");
            System.out.println(retrieveSystemSettings().getWeekendIncrement());
            retrieveSystemSettings().setWeekendIncrement(readDouble("New Price:"));
            display();
            break;
            case 9:
            direct(this, new HolidayBoundary());
            display();
            break;
            case 10:
            try{updateSystemSettings();}catch(IOException e){
                System.out.println(e);
            }
            end();
            break;
        }
    }
}
