package Boundary.MovieGoer;

import Boundary.Boundary;
import Boundary.SupportFunctions;

public class DisplayBookingHistoryBoundary extends Boundary {
    @Override
    protected void start() {
        SupportFunctions.clearScreen();
        display();
    }
}
