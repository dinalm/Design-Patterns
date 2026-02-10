public class IntermediateState implements CharacterState {

    @Override
    public void train(GameCharacter character) {
        System.out.println(character.getName() + " is training hard!");
        character.addExperiencePoints(15);

        if (character.getExperiencePoints() >= 150) {
            System.out.println("\n*** LEVEL UP! You've reached Expert level! ***\n");
            character.setLevel(3);
            character.setState(new ExpertState());
        }
    }

    @Override
    public void meditate(GameCharacter character) {
        System.out.println(character.getName() + " is meditating peacefully...");
        character.addHealthPoints(20);
    }

    @Override
    public void fight(GameCharacter character) {
        System.out.println("You cannot fight yet. Train more to reach Expert level!");
    }

    @Override
    public void displayStatus(GameCharacter character) {
        System.out.println("\n=== Character Status ===");
        System.out.println("Name: " + character.getName());
        System.out.println("Level: " + character.getLevel() + " (Intermediate)");
        System.out.println("Experience Points: " + character.getExperiencePoints() + "/150");
        System.out.println("Health Points: " + character.getHealthPoints());
        System.out.println("\nAvailable Actions:");
        System.out.println("1. Train");
        System.out.println("2. Meditate");
        System.out.println("========================\n");
    }

    @Override
    public String getStateName() {
        return "Intermediate";
    }
}
