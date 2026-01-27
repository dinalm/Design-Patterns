public class Main {

    public static void main(String[] args) {
        System.out.println("=== Composite Pattern - Organization Structure Demo ===\n");

        // Create the organization
        Organization company = new Organization("TechCorp Inc");

        System.out.println(">>> Building Organization Structure <<<\n");

        // Create Engineering Department
        Department engineering = new Department("Engineering");
        engineering.add(new Employee("Alice Johnson", 95000));
        engineering.add(new Employee("Bob Smith", 87000));
        engineering.add(new Employee("Carol White", 92000));

        // Create a sub-department within Engineering
        Department softwareDev = new Department("Software Development");
        softwareDev.add(new Employee("David Brown", 88000));
        softwareDev.add(new Employee("Eve Davis", 91000));
        softwareDev.add(new Employee("Frank Miller", 85000));

        // Add sub-department to Engineering
        engineering.add(softwareDev);

        // Create Sales Department
        Department sales = new Department("Sales");
        sales.add(new Employee("Grace Lee", 72000));
        sales.add(new Employee("Henry Wilson", 68000));
        sales.add(new Employee("Iris Martinez", 75000));

        // Create HR Department
        Department hr = new Department("Human Resources");
        hr.add(new Employee("Jack Anderson", 65000));
        hr.add(new Employee("Kate Thompson", 67000));

        // Add all departments to the organization
        company.add(engineering);
        company.add(sales);
        company.add(hr);

        // Add a CEO directly to the root
        company.add(new Employee("Linda Chen - CEO", 180000));

        System.out.println("Organization structure created successfully!\n");

        // Demonstrate total salary calculation
        System.out.println("=".repeat(60));
        System.out.println(">>> Demonstrating Total Salary Calculation <<<\n");
        company.printTotalSalary();
        System.out.println();

        // Demonstrate XML printing
        System.out.println("=".repeat(60));
        System.out.println(">>> Printing Organization Structure in XML Format <<<\n");
        company.printXML();
        System.out.println();

        // Demonstrate adding components dynamically
        System.out.println("=".repeat(60));
        System.out.println(">>> Demonstrating Dynamic Addition <<<\n");

        System.out.println("Adding new employee to Sales department...");
        Employee newEmployee = new Employee("Mike Taylor", 70000);
        sales.add(newEmployee);
        System.out.println("Employee added: " + newEmployee);
        System.out.println();

        System.out.println("Adding new department to organization...");
        Department finance = new Department("Finance");
        finance.add(new Employee("Nancy Clark", 78000));
        finance.add(new Employee("Oscar Rodriguez", 76000));
        company.add(finance);
        System.out.println("Finance department added with 2 employees\n");

        System.out.println("Updated total salary:");
        company.printTotalSalary();
        System.out.println();

        // Demonstrate removing components
        System.out.println("=".repeat(60));
        System.out.println(">>> Demonstrating Dynamic Removal <<<\n");

        System.out.println("Removing employee from Sales department...");
        sales.remove(newEmployee);
        System.out.println("Employee removed: " + newEmployee);
        System.out.println();

        System.out.println("Updated total salary:");
        company.printTotalSalary();
        System.out.println();

        // Print final XML structure
        System.out.println("=".repeat(60));
        System.out.println(">>> Final Organization Structure (XML) <<<\n");
        company.printXML();
        System.out.println();

        // Demonstrate department-level salary calculation
        System.out.println("=".repeat(60));
        System.out.println(">>> Department-Level Salary Calculations <<<\n");

        System.out.println("Engineering Department Total: $" +
                String.format("%.2f", engineering.getTotalSalary()));
        System.out.println("  (includes Software Development sub-department)");
        System.out.println();

        System.out.println("Sales Department Total: $" +
                String.format("%.2f", sales.getTotalSalary()));
        System.out.println();

        System.out.println("HR Department Total: $" +
                String.format("%.2f", hr.getTotalSalary()));
        System.out.println();

        System.out.println("Finance Department Total: $" +
                String.format("%.2f", finance.getTotalSalary()));
        System.out.println();

        System.out.println("Software Development Sub-Department Total: $" +
                String.format("%.2f", softwareDev.getTotalSalary()));
        System.out.println();

        // Summary
        System.out.println("=".repeat(60));
        System.out.println(">>> Organization Summary <<<\n");
        company.printSummary();
        System.out.println();

        System.out.println("=".repeat(60));
        System.out.println("Demo completed successfully!");
        System.out.println("The Composite pattern allows uniform treatment of");
        System.out.println("individual employees and groups of employees/departments.");
    }
}
