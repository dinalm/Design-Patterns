import java.util.ArrayList;
import java.util.List;

public class Department implements OrganizationComponent {

    private String name;
    private List<OrganizationComponent> children;

    public Department(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void add(OrganizationComponent component) {
        children.add(component);
    }

    @Override
    public void remove(OrganizationComponent component) {
        children.remove(component);
    }

    public List<OrganizationComponent> getChildren() {
        return children;
    }

    @Override
    public double getTotalSalary() {
        double total = 0.0;
        for (OrganizationComponent component : children) {
            total += component.getTotalSalary();
        }
        return total;
    }

    @Override
    public void printXML(int indent) {
        String indentation = getIndentation(indent);

        if (children.isEmpty()) {
            // Self-closing tag if no children
            System.out.println(indentation + "<department name=\"" + name + "\" />");
        } else {
            // Opening tag
            System.out.println(indentation + "<department name=\"" + name + "\">");

            // Print all children recursively
            for (OrganizationComponent component : children) {
                component.printXML(indent + 1);
            }

            // Closing tag
            System.out.println(indentation + "</department>");
        }
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
        return "Department{name='" + name + "', children=" + children.size() + "}";
    }
}
