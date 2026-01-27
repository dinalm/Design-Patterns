public class Employee implements OrganizationComponent {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double getTotalSalary() {
        return salary;
    }

    @Override
    public void printXML(int indent) {
        String indentation = getIndentation(indent);
        System.out.println(indentation + "<employee name=\"" + name + "\" salary=\"" + salary + "\" />");
    }

    private String getIndentation(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + "}";
    }
}
