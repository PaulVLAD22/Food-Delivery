//package service;
//
//import model.account.User;
//import model.location.Coordinate;
//
//import java.util.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.NoSuchFileException;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//
//public class ReadService {
//
//    private static ReadService instance = null;
//
//    private ReadService(){
//    }
//
//    public static ReadService getInstance() {
//        if (instance==null){
//            instance=new ReadService();
//        }
//        return instance;
//    }
//
//    public List<String> read (String filename){
//
//        List<String> output = new ArrayList<>();
//        try {
//            BufferedReader reader = Files.newBufferedReader(Paths.get(filename));
//            String line = "";
//            while((line = reader.readLine()) != null) {
//                output.add(line);
//            }
//        } catch (NoSuchFileException e) {
//            System.out.println("The file with the name " + filename + " doesn't exist.");
//        } catch (IOException e) {
//            System.out.println(e.getClass() + " " + e.getMessage());
//        }
//        return output;
//    }
//}
