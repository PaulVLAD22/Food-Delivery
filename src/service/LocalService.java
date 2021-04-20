package service;

import model.local.Local;

import java.nio.file.Path;
import java.util.List;

public class LocalService {
    private static LocalService INSTANCE;

    private LocalService(){

    }
    public static LocalService getInstance(){
        if (INSTANCE == null){
            INSTANCE = new LocalService();
        }
        return INSTANCE;
    }

    private BasicService basicService =BasicService.getInstance();

    public final Path LOCALS_DIRECTORY = Path.of("resources/locals");
    public final Path LOCALS_PATH = Path.of(LOCALS_DIRECTORY + "/locals.csv");

    private CsvReader<Local> localCsvReader = new CsvReader<>();
    private CsvWriter<Local> localCsvWriter = new CsvWriter<>(LOCALS_DIRECTORY, LOCALS_PATH);

    public List<Local> read() {
        return localCsvReader.read(LOCALS_PATH);
    }

    public void write(Local local) {
        localCsvWriter.write(local);
    }


}
