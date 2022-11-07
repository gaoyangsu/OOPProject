package Boundary.Staff;
import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.*;
import static Controller.CRUDMovies.*;
import static Controller.CRUDTheatre.*;
import static Controller.MiscMethods.*;
import static Controller.AdminController.*;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;


/**
    Boundary/View Class representing an Admin to add/remove a Holiday 
    @version 1.0
    @since 2022-10-30
 */
public class HolidayBoundary extends Boundary {
    
    Scanner sc= new Scanner(System.in);
    
    /**
     * overriden start method from Boundary abstract class
     */
    @Override
    protected void start() {
        display();
    }

    /** method to display menu to add/remove/display holidays */
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

    /** method to display a holiday */ 
    private void displayHolidays(){

        Scanner sc= new Scanner(System.in);
        SupportFunctions.clearScreen();
        int index=0;
        ArrayList<Holiday> listofHols;

        listofHols=retrieveHolidays();
        if(listofHols.isEmpty()){
            printMenu("No holiday records found in database",
            "Press any key to return");
            sc.nextLine();
            display();
        }
        
        printMenu("------------HOLIDAYS-------------");
        
        for(Holiday holiday : listofHols){
            System.out.println(++index  + " " + holiday.getHolidayName() +" "+holiday.getDate());
            
        }
        readString("\npress enter to return");
        display();
    }

    /**method to add a holiday into an arraylist of holidays, to be saved to holiday.dat */
    public  void addHolidayToIndex(){
        Scanner sc = new Scanner (System.in);
        //SupportFunctions.clearScreen();
        System.out.println("Enter the name of the holiday. Enter 0 to return.");
        String name = sc.nextLine();
        if (name.equals("0")) display();
        else{
        System.out.println("Set date of holiday");
        Date dateInput = dateInput();
        Holiday holiday = new Holiday(dateInput ,name);
        
        try{
            addHoliday(holiday);
            System.out.println("Successfully listed Holiday "+ name);
            System.out.println("Press any key to return");
            sc.nextLine();
        }catch(IOException e){
            System.out.println(e);
            System.out.println("Failed to list movie");   
        }
        finally {
            display();
        }}
        

    }

    /** method to delete a particular holiday index */
    public void deleteHolidayInIndex(){
        SupportFunctions.clearScreen();
        System.out.println("Select Index of holiday to remove (enter 0 to return)");
        int index=0;
        for(Holiday holiday : retrieveHolidays()){
            System.out.println(++index + " " + holiday.getHolidayName() +" "+holiday.getDate());
        }
        int i = sc.nextInt();
        if(i == 0)display();
        String holname = retrieveHolidays().get(i-1).getHolidayName();
        Date holdate = retrieveHolidays().get(i-1).getDate();
        try{removeHoliday(retrieveHolidays().get(i-1));
            readString("\nHoliday "+ holname + holdate + " successfully removed!");
            display();
        }
        
        catch(IOException e){
            System.out.println(e);
            readString("\npress enter to return");
        };
    }
}
