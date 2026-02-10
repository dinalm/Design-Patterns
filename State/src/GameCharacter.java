public class GameCharacter {
    private String name;
    private int level;
    private int experiencePoints;
    private int healthPoints;
    private CharacterState state;

    public GameCharacter(String name) {
        this.name = name;
        this.level = 1;
        this.experiencePoints = 0;
        this.healthPoints = 100;
        this.state = new NoviceState();
    }

    public void train() {
        state.train(this);
    }

    public void meditate() {
        state.meditate(this);
    }

    public void fight() {
        state.fight(this);
    }

    public void displayStatus() {
        state.displayStatus(this);
    }

    public void setState(CharacterState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void addExperiencePoints(int points) {
        this.experiencePoints += points;
        System.out.println("Gained " + points + " XP!");
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void addHealthPoints(int points) {
        this.healthPoints += points;
        System.out.println("Restored " + points + " HP!");
    }

    public void reduceHealthPoints(int points) {
        this.healthPoints -= points;
        if (this.healthPoints < 0) {
            this.healthPoints = 0;
        }
        System.out.println("Lost " + points + " HP!");
    }

    public String getStateName() {
        return state.getStateName();
    }
}
