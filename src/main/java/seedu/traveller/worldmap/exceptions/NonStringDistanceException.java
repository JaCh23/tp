package seedu.traveller.worldmap.exceptions;

//@@author Jach23
public class NonStringDistanceException extends WorldMapException {
    public NonStringDistanceException(String dist) {
        message = "\tDistance " + dist + " needs to be a number!";
    }
}