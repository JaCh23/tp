package seedu.traveller;

import org.junit.jupiter.api.Test;
import seedu.traveller.commands.AddItemCommand;
import seedu.traveller.commands.DeleteCommand;
import seedu.traveller.commands.EditCommand;
import seedu.traveller.commands.NewCommand;
import seedu.traveller.commands.ViewAllCommand;
import seedu.traveller.commands.SearchCommand;
import seedu.traveller.commands.AddDayCommand;
import seedu.traveller.commands.ExitCommand;
import seedu.traveller.exceptions.TravellerException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    private final NewCommand newCommand;
    private final EditCommand editCommand;
    private final DeleteCommand deleteCommand;
    private final ViewAllCommand viewAllCommand;
    private final SearchCommand searchCommand;
    private final AddDayCommand addDayCommand;
    private final AddItemCommand addItemCommand;
    private final ExitCommand exitCommand;

    public ParserTest() {
        newCommand = new NewCommand("trip0", "CHN", "JPN");
        editCommand = new EditCommand("trip1", "SIN", "MLY");
        deleteCommand = new DeleteCommand("trip2");
        viewAllCommand = new ViewAllCommand();
        searchCommand = new SearchCommand("SKR", "JPN");
        addDayCommand = new AddDayCommand("trip3");
        addItemCommand = new AddItemCommand("trip4", 0, "1-2am", "sleep at home");
        exitCommand = new ExitCommand();
    }

    @Test
    public void parse_success() {
        try {
            assertEquals(newCommand.toString(), Parser.parse("new trip0 /from CHN /to JPN").toString());
            assertEquals(editCommand.toString(), Parser.parse("edit trip1 /from SIN /to MLY").toString());
            assertEquals(deleteCommand.toString(), Parser.parse("delete trip2").toString());
            assertEquals(viewAllCommand.toString(), Parser.parse("viewall").toString());
            assertEquals(searchCommand.toString(), Parser.parse("search /from SKR /to JPN").toString());
            assertEquals(addDayCommand.toString(), Parser.parse("add-day trip3").toString());
            assertEquals(addItemCommand.toString(),
                    Parser.parse("add-item trip4 /day 0 /time 1-2am /name sleep at home").toString());
            assertEquals(exitCommand.toString(), Parser.parse("exit").toString());
        } catch (TravellerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parse_newCommand1Country_exceptionThrown() {
        try {
            assertEquals(newCommand, Parser.parse("new trip0 CHN-JPN"));
        } catch (TravellerException e) {
            assertEquals("\tWrong format for New!\n\tCorrect format: new TRIP_NAME /from START /to END",
                    e.getMessage());
        }
    }

    @Test
    public void parse_newCommandMissingSpace_exceptionThrown() {
        try {
            Parser.parse("newtestTrip1 SIN MLY");
        } catch (TravellerException e) {
            assertEquals("\tThe command 'newtestTrip1 SIN MLY' is not recognised.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            Parser.parse("bad command");
        } catch (TravellerException e) {
            assertEquals("\tThe command 'bad command' is not recognised.", e.getMessage());
        }
    }
}