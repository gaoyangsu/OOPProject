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
        printHeader("Modify Holidays");
        printMenu("Select Choice",
        "1.Display Holidays",
        "2.Add Holidays",
        "3.Remove Holidays",
        "4.Back");

        int choice = readChoice(1, 3);
        switch(choice){
            case 1:
            displayHolidays();
            display();
            break;
            case 2:
            addHolidayToIndex();
            display();
            break;
            case 3:
            deleteHolidayInIndex();
            display();
            break;
            case 4:
            end();
            break;

            }
        }
    private void displayHolidays(){
        int index=0;
        for(Holiday holiday : retrieveHolidays()){
            System.out.println(++index + holiday.getHolidayName() +" "+holiday.getDate());
        }
        if(index == 0){System.out.println("No holidays indexed");}
    }
    private  void addHolidayToIndex(){
        String name = readString("Enter name of the holiday").toUpperCase();
        System.out.println("Set date of holiday");
        Holiday holiday = new Holiday(dateInput(),name);
        try{addHoliday(holiday);}catch(IOException e){
            System.out.println(e);
        };
    }
    private  void deleteHolidayInIndex(){
        System.out.println("Select Index of removed holiday");
        displayHolidays();
        int i = sc.nextInt();
        try{removeHoliday(retrieveHolidays().get(i));}catch(IOException e){
            System.out.println(e);
        };
    }
}
