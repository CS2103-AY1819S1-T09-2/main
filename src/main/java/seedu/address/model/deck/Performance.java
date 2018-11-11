package seedu.address.model.deck;

/**
 * Represents a card's performance.
 */
public enum Performance {
    DEFAULT("default"),
    EASY("easy"),
    GOOD("good"),
    HARD("hard"),
    REVIEW("review");

    public static final String MESSAGE_PERFORMANCE_CONSTRAINTS =
            "Performance must be one of the strings {easy|good|hard|review}";
    private final String description;

    private Performance(String description) {
        this.description = description;
    }
    public boolean isCorrect() {
        //TODO : Find a better way to right this, ideally want to find if .ordinal() of performance is below len(enum)/2
        return (description.equals("default")||(description.equals("easy"))
                || (description.equals("good")));
    }

    public String toString() {
        return description;
    }
}