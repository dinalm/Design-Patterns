public class MasterState implements CharacterState {

    @Override
    public void train(GameCharacter character) {
        System.out.println("You have mastered all skills! No need to train anymore.");
    }

    @Override
    public void meditate(GameCharacter character) {
        System.out.println("You have reached inner peace. Meditation is no longer needed.");
    }

    @Override
    public void fight(GameCharacter character) {
        System.out.println("You have conquered all challenges. There are no more battles to fight.");
    }

    @Override
    public void displayStatus(GameCharacter character) {
        System.out.println("\n=== Character Status ===");
        System.out.println("Name: " + character.getName());
        System.out.println("Level: " + character.getLevel() + " (MASTER)");
        System.out.println("Experience Points: " + character.getExperiencePoints());
        System.out.println("Health Points: " + character.getHealthPoints());
        System.out.println("\n*** GAME COMPLETED! ***");
        System.out.println("You have become a true master!");
        System.out.println("========================\n");
    }

    @Override
    public String getStateName() {
        return "Master";
    }
}
