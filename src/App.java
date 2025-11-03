public class App {
    public static void main(String[] args) {
        PrintTitle.printTitle();
        Menu menu = new Menu();

        while (true) {
            Menu.MenuAction action = menu.displayMenu();
            switch (action) {
                case START_GAME:
                    Game game = GameConfiguration.configure();
                    if (game != null) {
                        game.start();
                    }
                    break;
                case TUTORIAL:
                    // Menu owns the tutorial presentation. App just routes
                    menu.startTutorial();
                    break;
                case EXIT:
                    System.out.println("Exiting program.");
                    return;
                case INVALID:
                default:
                    System.out.println("Invalid selection. Try again.");
                    break;
            }
        }
    }
}
