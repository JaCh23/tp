package seedu.traveller.exceptions;

public class InvalidEditFormatException extends TravellerException {
    public InvalidEditFormatException() {
        message = "\tWrong format for Edit!\n\tCorrect format: edit TRIP_NAME START END";
    }
}