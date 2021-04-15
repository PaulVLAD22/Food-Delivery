package service;

import model.Company;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class WriteService {
    private static WriteService instance = null;

    private WriteService(){
    }

    public static WriteService getInstance() {
        if (instance==null){
            instance=new WriteService();
        }
        return instance;
    }

    public void writeToFile(String DIRECTORY, String PATH, String message){
        if(!Files.exists(Paths.get(DIRECTORY))) {
            try {
                Files.createDirectories(Paths.get(DIRECTORY));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if(!Files.exists(Paths.get(PATH))) {
            try {
                Files.createFile(Paths.get(PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(PATH),
                    StandardOpenOption.APPEND);
            writer.write('\n'+message);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
