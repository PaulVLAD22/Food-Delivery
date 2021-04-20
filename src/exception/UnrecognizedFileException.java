package exception;

public class UnrecognizedFileException extends RuntimeException {
    public UnrecognizedFileException(){
        super("This file doesn't have a parser");
    }
}
