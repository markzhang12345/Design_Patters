package menu;

@FunctionalInterface
public interface MenuCommand {
    /**
     * Executes the command and returns whether the menu should exit.
     *
     * @return true if the menu should exit after this command, false otherwise
     */
    boolean execute();
}
