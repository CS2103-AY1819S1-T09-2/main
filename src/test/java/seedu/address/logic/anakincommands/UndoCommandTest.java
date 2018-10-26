package seedu.address.logic.anakincommands;

import static seedu.address.logic.anakincommands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.anakincommands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.anakincommands.CommandTestUtil.deleteFirstDeck;
import static seedu.address.testutil.TypicalDecks.getTypicalAnakin;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UndoCommandTest {

    private final Model model = new ModelManager(getTypicalAnakin(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAnakin(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        // set up of models' undo/redo history
        deleteFirstDeck(model);
        deleteFirstDeck(model);

        deleteFirstDeck(expectedModel);
        deleteFirstDeck(expectedModel);
    }

    @Test
    public void execute() {
        // multiple undoable states in model
        expectedModel.undoAnakin();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // single undoable state in model
        expectedModel.undoAnakin();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // no undoable states in model
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
    }
}