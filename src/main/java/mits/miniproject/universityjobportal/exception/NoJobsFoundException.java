package mits.miniproject.universityjobportal.exception;

public class NoJobsFoundException extends RuntimeException{
    public NoJobsFoundException(String message){
        super(message);
    }
}
