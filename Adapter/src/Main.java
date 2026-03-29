public class Main {
    public static void main(String[] args) {
        NewDateInterface date = new CalendarToNewDateAdapter();

        date.setDay(15);
        date.setMonth(3);
        date.setYear(2025);

        System.out.println("Initial date  : " + formatDate(date));

        date.advanceDays(20);
        System.out.println("After +20 days: " + formatDate(date));

        date.advanceDays(365);
        System.out.println("After +365 days: " + formatDate(date));
    }

    private static String formatDate(NewDateInterface date) {
        return String.format("%02d/%02d/%04d", date.getDay(), date.getMonth(), date.getYear());
    }
}