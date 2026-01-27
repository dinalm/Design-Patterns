public class Organization {

    private Department root;

    public Organization(String organizationName) {
        this.root = new Department(organizationName);
    }

    public Department getRoot() {
        return root;
    }

    public void add(OrganizationComponent component) {
        root.add(component);
    }

    public void remove(OrganizationComponent component) {
        root.remove(component);
    }

    public void printTotalSalary() {
        double total = root.getTotalSalary();
        System.out.println("Total Organization Salary: $" + String.format("%.2f", total));
    }

    public void printXML() {
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        System.out.println("<organization>");

        // Print root department and all its children
        root.printXML(1);

        System.out.println("</organization>");
    }

    public void printSummary() {
        System.out.println("Organization: " + root.getName());
        System.out.println("Total Salary: $" + String.format("%.2f", root.getTotalSalary()));
        System.out.println("Direct Children: " + root.getChildren().size());
    }
}
