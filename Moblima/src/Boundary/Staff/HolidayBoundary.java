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
public class HolidayBoundary extends Boundary {
    
    Scanner sc= new Scanner(System.in);
    @Override
    protected void start() {
        display();
    }
    private void display(){
        SupportFunctions.clearScreen();
        printHeader("Modify Holidays");
        printMenu("Select Choice",
        "1.Display Holidays",
        "2.Add Holidays",
        "3.Remove Holidays",
        "4.Back");

        int choice = readChoice(1, 4);
        switch(choice){
            case 1:
            displayHolidays();
            break;
            case 2:
            addHolidayToIndex();
            break;
            case 3:
            deleteHolidayInIndex();
            break;
            case 4:
            end();
            break;

            }
        }
    private void displayHolidays(){
        SupportFunctions.clearScreen();
        int index=0;
        for(Holiday holiday : retrieveHolidays()){
            System.out.println(++index  + " " + holiday.getHolidayName() +" "+holiday.getDate());
            readString("\npress enter to return");
            display();
        }
        if(index == 0){readString("\nNo holidays indexed");}
    }
    private  void addHolidayToIndex(){
        SupportFunctions.clearScreen();
        String name = readString("Enter name of the holiday (press enter to return)").toUpperCase();
        if(name == "")display();
        System.out.println("Set date of holiday");
        Holiday holiday = new Holiday(dateInput(),name);
        readString("\nHoliday successfully added!");
        display();
        try{addHoliday(holiday);}catch(IOException e){
            System.out.println(e);
            readString("\npress enter to return");
        };
    }
    private  void deleteHolidayInIndex(){
        SupportFunctions.clearScreen();
        System.out.println("Select Index of holiday to remove (enter 0 to return)");
        int index=0;
        for(Holiday holiday : retrieveHolidays()){
            System.out.println(++index + " " + holiday.getHolidayName() +" "+holiday.getDate());
        }
        int i = sc.nextInt();
        if(i == 0)display();
        try{removeHoliday(retrieveHolidays().get(i-1));
            readString("\nHoliday "+ retrieveHolidays().get(i-1).getHolidayName() + retrieveHolidays().get(i-1).getDate() + " successfully removed!");
            display();
        }
        
        catch(IOException e){
            System.out.println(e);
            readString("\npress enter to return");
        };
    }
}
