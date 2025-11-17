public class Menu {

    // scoped enum since only menu uses it
    public enum MenuAction {
        START_GAME,
        TUTORIAL,
        EXIT,
        INVALID;

        // convert int choice to enum
        static MenuAction fromInt(int choice) {
            switch (choice) {
                case 1:
                    return START_GAME;
                case 2:
                    return TUTORIAL;
                case 3:
                    return EXIT;
                default:
                    return INVALID;
            }
        }
    }

    // displayMenu() shows the menu and gets user choice
    public MenuAction displayMenu() {
        System.out.println("=== " + Grid.U_RED + "Battleship" + Grid.RESET + " " + Grid.U_BLUE + "Menu" + Grid.RESET + " ===");
        System.out.println("1. Start Game");
        System.out.println("2. Tutorial");
        System.out.println("3. Exit");
        int choice = ConsoleHelper.getNumberBetween("Enter your choice (1-3): ", 1, 3);
        return MenuAction.fromInt(choice);
    }

    // start() delegates to displayMenu()
    public MenuAction start() {
        return displayMenu();
    }

    // exit() App should handle actual lifecycle/termination
    // public void exit() {
    // System.out.println("Menu.exit() called. App should perform shutdown.");
    // }

    // optional tutorial placeholder (iteration 1: not implemented)
    public void startTutorial() {
        Tutorial.run();
    }
}
