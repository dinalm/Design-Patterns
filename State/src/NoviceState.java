public class NoviceState implements CharacterState {

    @Override
    public void train(GameCharacter character) {
        System.out.println(character.getName() + " is training hard!");
        character.addExperiencePoints(10);

        if (character.getExperiencePoints() >= 50) {
            System.out.println("\n*** LEVEL UP! You've reached Intermediate level! ***\n");
            character.setLevel(2);
            character.setState(new IntermediateState());
        }
    }

    @Override
    public void meditate(GameCharacter character) {
        System.out.println("You cannot meditate yet. Train to reach Intermediate level first!");
    }

    @Override
    public void fight(GameCharacter character) {
        System.out.println("You cannot fight yet. You need more experience!");
    }

    @Override
    public void displayStatus(GameCharacter character) {
        System.out.println("\n=== Character Status ===");
        System.out.println("Name: " + character.getName());
        System.out.println("Level: " + character.getLevel() + " (Novice)");
        System.out.println("Experience Points: " + character.getExperiencePoints() + "/50");
        System.out.println("Health Points: " + character.getHealthPoints());
        System.out.println("\nAvailable Actions:");
        System.out.println("1. Train");
        System.out.println("========================\n");
    }

    @Override
    public String getStateName() {
        return "Novice";
    }
}
