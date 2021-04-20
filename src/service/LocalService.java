package service;

import model.local.Local;

import java.nio.file.Path;
import java.util.List;

public class LocalService {
    private BasicService basicService = new BasicService();

    public final Path LOCALS_DIRECTORY = Path.of("resources/locals");
    public final Path LOCALS_PATH = Path.of(LOCALS_DIRECTORY + "/locals.csv");

    public CsvReader<Local> localCsvReader = new CsvReader<>();
    public CsvWriter<Local> localCsvWriter = new CsvWriter<>(LOCALS_DIRECTORY, LOCALS_PATH);

    public List<Local> read() {
        return localCsvReader.read(LOCALS_PATH);
    }

    public void write(Local local) {
        localCsvWriter.write(local);
    }

//    public Set<Local> readLocals(){
//        Set<Local> locals = new HashSet<>();
//        String filename = basicService.LOCALS_PATH.toString();
//        List<String> fileOutput = basicService.readService.read(filename);
//
//        for (String line : fileOutput) {
//            String [] information = line.split(",");
//            String name = information[0];
//
//            Menu menu = new Menu();
//            Location location = new Location();
//            if (! information[1].equals("null")) {
//                String[] productStrings = information[1].split(";");
//                for (String productString : productStrings) {
//                    String[] productNameQuantity = productString.split(":");
//                    System.out.println(productString);
//                    menu.getProducts().add(new Product(productNameQuantity[0], Integer.parseInt(productNameQuantity[1])));
//                }
//            }
//            String [] addressString = information[2].split(":");
//            String [] coordinates = information[3].split(":");
//            location.setAddress(new Address(addressString[0],addressString[1],addressString[2]));
//            location.setCoordinate(new Coordinate(Integer.parseInt(coordinates[0]),Integer.parseInt(coordinates[1])));
//
//            locals.add(new Local(name,menu,location));
//        }
//        return locals;
//    }

}
