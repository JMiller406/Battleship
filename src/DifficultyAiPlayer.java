public class DifficultyAiPlayer extends AiPlayer {
    private final int difficulty; // 1=Easy, 2=Normal, 3=Hard

    public DifficultyAiPlayer(String name, int difficulty) {
        super(name);
        this.difficulty = difficulty;
    }

    @Override
    public Coordinate chooseRandomUntestedCoordinate() {
        if (difficulty <= 1) {
            return super.chooseRandomUntestedCoordinate();
        }

        // For Normal/Hard, inform the user this difficulty isn't implemented and fall back to Easy
        String level = (difficulty == 2) ? "Normal" : "Hard";
        ConsoleHelper.getInput("AI difficulty '" + level + "' is not implemented yet. Press Enter to continue using Easy AI behavior...");
        return super.chooseRandomUntestedCoordinate();
    }
}
