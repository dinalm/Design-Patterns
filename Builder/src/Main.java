import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ComputerDirector director = new ComputerDirector(new GamingComputerBuilder());
        boolean running = true;

        while (running) {
            System.out.println("\n============================");
            System.out.println("   Computer Builder Menu");
            System.out.println("============================");
            System.out.println("1. Gaming Computer");
            System.out.println("2. Office Computer");
            System.out.println("3. Custom Computer");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    director.setBuilder(new GamingComputerBuilder());
                    Computer gaming = director.constructComputer();
                    System.out.println("\n=== Gaming Computer ===");
                    System.out.println(gaming);
                    break;

                case "2":
                    director.setBuilder(new OfficeComputerBuilder());
                    Computer office = director.constructComputer();
                    System.out.println("\n=== Office Computer ===");
                    System.out.println(office);
                    break;

                case "3":
                    Computer custom = buildCustom(scanner);
                    System.out.println("\n=== Custom Computer ===");
                    System.out.println(custom);
                    break;

                case "4":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please enter 1-4.");
            }
        }

        scanner.close();
    }

    private static Computer buildCustom(Scanner scanner) {
        System.out.println("\n--- Custom Computer Builder ---");

        System.out.println("Processor options:");
        System.out.println("  1. Intel Core i9-14900K");
        System.out.println("  2. AMD Ryzen 9 7950X");
        System.out.println("  3. Intel Core i5-13400");
        System.out.print("Choose processor (1-3): ");
        String proc = switch (scanner.nextLine().trim()) {
            case "2" -> "AMD Ryzen 9 7950X";
            case "3" -> "Intel Core i5-13400";
            default  -> "Intel Core i9-14900K";
        };

        System.out.println("RAM options:");
        System.out.println("  1.  8 GB");
        System.out.println("  2. 16 GB");
        System.out.println("  3. 32 GB");
        System.out.println("  4. 64 GB");
        System.out.print("Choose RAM (1-4): ");
        int ram = switch (scanner.nextLine().trim()) {
            case "1" ->  8;
            case "2" -> 16;
            case "4" -> 64;
            default  -> 32;
        };

        System.out.println("Hard drive options:");
        System.out.println("  1. 256 GB SSD");
        System.out.println("  2. 512 GB SSD");
        System.out.println("  3. 1 TB NVMe SSD");
        System.out.println("  4. 2 TB NVMe SSD");
        System.out.print("Choose hard drive (1-4): ");
        String hdd = switch (scanner.nextLine().trim()) {
            case "1" -> "256 GB SSD";
            case "2" -> "512 GB SSD";
            case "4" -> "2 TB NVMe SSD";
            default  -> "1 TB NVMe SSD";
        };

        System.out.println("Graphics card options:");
        System.out.println("  1. NVIDIA GeForce RTX 4090");
        System.out.println("  2. NVIDIA GeForce RTX 4060");
        System.out.println("  3. AMD Radeon RX 7800 XT");
        System.out.println("  4. Intel UHD Graphics 730 (integrated)");
        System.out.print("Choose graphics card (1-4): ");
        String gpu = switch (scanner.nextLine().trim()) {
            case "2" -> "NVIDIA GeForce RTX 4060";
            case "3" -> "AMD Radeon RX 7800 XT";
            case "4" -> "Intel UHD Graphics 730";
            default  -> "NVIDIA GeForce RTX 4090";
        };

        System.out.println("Operating system options:");
        System.out.println("  1. Windows 11 Pro");
        System.out.println("  2. Windows 11 Home");
        System.out.println("  3. Ubuntu 24.04 LTS");
        System.out.println("  4. macOS Sequoia");
        System.out.print("Choose OS (1-4): ");
        String os = switch (scanner.nextLine().trim()) {
            case "2" -> "Windows 11 Home";
            case "3" -> "Ubuntu 24.04 LTS";
            case "4" -> "macOS Sequoia";
            default  -> "Windows 11 Pro";
        };

        return new CustomComputerBuilder()
                .processor(proc)
                .ram(ram)
                .hardDrive(hdd)
                .graphicsCard(gpu)
                .operatingSystem(os)
                .getComputer();
    }
}