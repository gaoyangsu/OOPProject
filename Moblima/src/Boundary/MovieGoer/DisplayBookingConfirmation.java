package Boundary.MovieGoer;

import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.Movie;
import Entity.MovieEnums;
import Entity.ShowSchedule;

import static Controller.CRUDMovies.*;
import static Controller.MiscMethods.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import static Controller.MiscMethods.*;

public class DisplayBookingConfirmation extends Boundary{

    private ShowSchedule schedule;
    public DisplayBookingConfirmation(ShowSchedule schedule){
        this.schedule=schedule;
    };

    protected void start() {
        display();
    }

    private void display() {
        SupportFunctions.clearScreen();
        printHeader("Booking Confirmation");
        BookingConfirmation();
    }

    private void BookingConfirmation(){

        
    }


}
