import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Game Character Development System!");
        System.out.print("Enter your character's name: ");
        String name = scanner.nextLine();

        GameCharacter character = new GameCharacter(name);

        System.out.println("\nYour journey begins, " + name + "!");

        while (!character.getStateName().equals("Master")) {
            character.displayStatus();

            System.out.print("Choose an action (enter number): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    character.train();
                    break;
                case "2":
                    character.meditate();
                    break;
                case "3":
                    character.fight();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }

        character.displayStatus();
        scanner.close();
    }
}
