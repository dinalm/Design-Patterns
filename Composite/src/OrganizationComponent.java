public interface OrganizationComponent {

    String getName();

    double getTotalSalary();

    void printXML(int indent);

    default void add(OrganizationComponent component) {
        throw new UnsupportedOperationException("Cannot add to this component");
    }

    default void remove(OrganizationComponent component) {
        throw new UnsupportedOperationException("Cannot remove from this component");
    }
}
