package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CURRENTLY_REVIEWING_DECK;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_DECK;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.anakinexceptions.DeckImportException;
import seedu.address.model.deck.anakinexceptions.DuplicateDeckException;

/**
 * Imports a deck identified using it's displayed index from Anakin.
 */
public class ImportDeckCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Imports the deck at the specified file location.\n"
        + "Parameters: FILEPATH \n"
        + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_IMPORT_DECK_SUCCESS = "Successfully Imported Deck: %1$s";

    private final String targetPath;

    public ImportDeckCommand(String targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        Deck importedDeck;

        requireNonNull(model);
        if (model.isReviewingDeck()) {
            throw new CommandException(MESSAGE_CURRENTLY_REVIEWING_DECK);
        }

        try {
            importedDeck = model.importDeck(targetPath);
            model.commitAnakin();
            return new CommandResult(String.format(MESSAGE_IMPORT_DECK_SUCCESS, importedDeck));

        } catch (DeckImportException ie) {
            throw new CommandException(ie.getMessage());

        } catch (DuplicateDeckException de) {
            throw new CommandException(MESSAGE_DUPLICATE_DECK);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ImportDeckCommand // instanceof handles nulls
            && targetPath.equals(((ImportDeckCommand) other).targetPath)); // state check
    }
}

