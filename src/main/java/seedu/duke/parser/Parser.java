package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.exception.LeBookException;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.UpdateStatusCommand;
import seedu.duke.commands.ListOverdueCommand;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {

    private static final String BYE = "bye";
    private static final String ADD = "add";
    private static final String LIST = "list";
    private static final String BORROW = "borrow";
    private static final String RETURN = "return";
    private static final String DELETE = "delete";

    /**
     * Parses user input and returns the corresponding command.
     *
     * @param userInput The input string.
     * @return The corresponding command object.
     * @throws LeBookException If the input is invalid.
     */
    public static Command parse(String userInput) throws LeBookException {
        assert userInput != null : "User input should not be null";
        assert !userInput.trim().isEmpty() : "User input should not be empty";

        String[] fullInput = userInput.split(" ", 2);
        String commandType = fullInput[0].toLowerCase();
        String bookDetails = (fullInput.length > 1) ? fullInput[1] : "";

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            if (bookDetails.trim().equalsIgnoreCase("overdue")) {
                return new ListOverdueCommand();
            }
            return new ListCommand();
        case BORROW, RETURN:
            return new UpdateStatusCommand(userInput);
        case DELETE:
            return new DeleteCommand(bookDetails);
        case ADD:
            return new AddCommand(bookDetails);
        default:
            throw new LeBookException("I don't understand. Try starting with list, add, delete, borrow, return!");
        }
    }
}

