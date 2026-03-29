public class CustomComputerBuilder implements ComputerBuilder {
    private Computer computer = new Computer();

    public CustomComputerBuilder processor(String processor) {
        computer.setProcessor(processor);
        return this;
    }

    public CustomComputerBuilder ram(int ramSize) {
        computer.setRamSize(ramSize);
        return this;
    }

    public CustomComputerBuilder hardDrive(String hardDrive) {
        computer.setHardDrive(hardDrive);
        return this;
    }

    public CustomComputerBuilder graphicsCard(String graphicsCard) {
        computer.setGraphicsCard(graphicsCard);
        return this;
    }

    public CustomComputerBuilder operatingSystem(String os) {
        computer.setOperatingSystem(os);
        return this;
    }

    @Override public void buildProcessor() {}
    @Override public void buildRAM() {}
    @Override public void buildHardDrive() {}
    @Override public void buildGraphicsCard() {}
    @Override public void buildOperatingSystem() {}

    @Override
    public Computer getComputer() {
        return computer;
    }
}