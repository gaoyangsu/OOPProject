package Controller;
import Entity.Movie;
import Entity.Review;
import Entity.SystemSettings;
import Entity.Theatre;
import Entity.TheatreEnums;
import Entity.Admin;
import Entity.Holiday;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import static Controller.RWController.*;

public class AdminController implements Initialiser {
    private static final String ADMIN_FILE = "Data/admin.dat";
    private static ArrayList<Admin> listofAdmins;
    private static final String SETTINGS_FILE = "Data/settings.dat";
    private static SystemSettings systemSetting;
    private static final String HOLIDAY_FILE = "Data/holiday.dat";
    private static ArrayList<Holiday> holidays;
//    public static boolean CRUDTheatresInitialise() {
//        try {
//            readTheatreList();
//        } catch (IOException ex) {
//            return false;
//        } catch (ClassNotFoundException ex) {
//            return true;
//        }
//
//        return true;
//    }

    public static void readHolidays() throws IOException, ClassNotFoundException {
        if (serialisedRead(HOLIDAY_FILE) == null) holidays = new ArrayList<>();
        else holidays = (ArrayList<Holiday>) serialisedRead(HOLIDAY_FILE);
    }
    public static void updateHolidays() throws IOException {
        serialisedWrite(HOLIDAY_FILE, holidays);
    }
    public static ArrayList<Holiday> retrieveHolidays() {
        return holidays;
    }
    public static void addHoliday(Holiday holiday) throws IOException {
        holidays.add(holiday);
        updateHolidays();
    }
    
    public static void removeHoliday(Holiday holiday) throws IOException{
        holidays.remove(holiday);
        updateHolidays();
    }
    public static void readSystemSettings() throws IOException, ClassNotFoundException {
        if (serialisedRead(SETTINGS_FILE) == null) systemSetting = new SystemSettings(2.50, 1.50, 4.50, 3, 8.50, 2.50, 1, 1);
        else systemSetting = (SystemSettings) serialisedRead(SETTINGS_FILE);
    }
    public static void updateSystemSettings() throws IOException {
        serialisedWrite(SETTINGS_FILE, systemSetting);
    }
    public static SystemSettings retrieveSystemSettings() {
        return systemSetting;
    }
    //THIS READ FUNCTION HAS TO BE USED EVERYTIME THE PROGRAMME STARTS
    public static void readAdminList() throws IOException, ClassNotFoundException {
        if (serialisedRead(ADMIN_FILE) == null) listofAdmins = new ArrayList<>();
        else listofAdmins = (ArrayList<Admin>) serialisedRead(ADMIN_FILE);
    }

    public static void updateAdminList() throws IOException {
        serialisedWrite(ADMIN_FILE, listofAdmins);
    }

    //AFTER READING FROM FILE, THE PROGRAMME CAN THEN RETRIEVE THE HASHMAP DATA
    public static ArrayList<Admin> retrieveAdminList() {
        return listofAdmins;
    }
    public static void addAdminIntoList(Admin admin) throws IOException {
        listofAdmins.add(admin);
        updateAdminList();

    }

    public static void removeAdminFromList(Admin admin) throws IOException{
        listofAdmins.remove(admin);
        updateAdminList();
    }
    @Override
    public boolean initialise(){
        try{readHolidays();
        readAdminList();
        readSystemSettings();
        return true;
    } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            return true;
        }
    }
}
