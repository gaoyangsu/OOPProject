package Controller;
import Entity.Movie;
import Entity.Review;
import Entity.Theatre;
import Entity.TheatreEnums;
import Entity.Admin;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import static Controller.RWController.*;

public class AdminController {
    private static final String ADMIN_FILE = "Data/admin.dat";
    private static ArrayList<Admin> listofAdmins;

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
    public static boolean authenticate(String id,String password){
        
        return true;
    }
}
