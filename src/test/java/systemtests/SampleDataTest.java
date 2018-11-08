package systemtests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//import org.junit.Test;

import org.junit.Test;
import seedu.address.model.Anakin;

//import seedu.address.model.deck.Deck;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAnakin;
import seedu.address.model.util.SampleDataUtil;
//import seedu.address.model.ReadOnlyAnakin;
//import seedu.address.model.deck.Deck;
//import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.TestUtil;
import static org.junit.Assert.assertEquals;


public class SampleDataTest extends AnakinSystemTest {
    /**
     * Returns null to force test app to load data of the file in {@code getDataFileLocation()}.
     */
    @Override
    protected Anakin getInitialData() {
        return null;
    }

    /**
     * Returns a non-existent file location to force test app to load sample data.
     */
    @Override
    protected Path getDataFileLocation() {
        Path filePath = TestUtil.getFilePathInSandboxFolder("SomeFileThatDoesNotExist1234567890.xml");
        deleteFileIfExists(filePath);
        return filePath;
    }

    /**
     * Deletes the file at {@code filePath} if it exists.
     */
    private void deleteFileIfExists(Path filePath) {
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException ioe) {
            throw new AssertionError(ioe);
        }
    }

    @Test
    public void anakin_dataFileDoesNotExist_loadSampleData() {
        final Model sampleAnakin = getModel();
        ReadOnlyAnakin expectedAnakin = SampleDataUtil.getSampleAnakin();
        assertEquals(sampleAnakin.getFilteredDeckList(), expectedAnakin.getDeckList());
    }
}
