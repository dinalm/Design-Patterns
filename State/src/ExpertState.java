public class ExpertState implements CharacterState {

    @Override
    public void train(GameCharacter character) {
        System.out.println(character.getName() + " is training intensely!");
        character.addExperiencePoints(20);

        if (character.getExperiencePoints() >= 300) {
            System.out.println("\n*** CONGRATULATIONS! You've reached Master level! ***\n");
            character.setLevel(4);
            character.setState(new MasterState());
        }
    }

    @Override
    public void meditate(GameCharacter character) {
        System.out.println(character.getName() + " is meditating deeply...");
        character.addHealthPoints(25);
    }

    @Override
    public void fight(GameCharacter character) {
        if (character.getHealthPoints() <= 20) {
            System.out.println("Your health is too low to fight! Meditate first.");
            return;
        }
        System.out.println(character.getName() + " engages in an epic battle!");
        character.reduceHealthPoints(20);
        character.addExperiencePoints(30);

        if (character.getExperiencePoints() >= 300) {
            System.out.println("\n*** CONGRATULATIONS! You've reached Master level! ***\n");
            character.setLevel(4);
            character.setState(new MasterState());
        }
    }

    @Override
    public void displayStatus(GameCharacter character) {
        System.out.println("\n=== Character Status ===");
        System.out.println("Name: " + character.getName());
        System.out.println("Level: " + character.getLevel() + " (Expert)");
        System.out.println("Experience Points: " + character.getExperiencePoints() + "/300");
        System.out.println("Health Points: " + character.getHealthPoints());
        System.out.println("\nAvailable Actions:");
        System.out.println("1. Train");
        System.out.println("2. Meditate");
        System.out.println("3. Fight");
        System.out.println("========================\n");
    }

    @Override
    public String getStateName() {
        return "Expert";
    }
}