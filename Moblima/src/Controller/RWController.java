package Controller;
import java.io.*;


public class RWController {

    protected static Object serialisedRead(String filePath) throws IOException, ClassNotFoundException {
        Object data;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(fileInputStream);
            data = objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (EOFException i){
            i.printStackTrace();
            System.out.println(i);
            return null;
        }
        return data;
    }


    protected static void serialisedWrite (String filePath, Object data) throws IOException {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;

        fileOutputStream = new FileOutputStream(filePath);
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(data);
        objectOutputStream.close();
    }
}
