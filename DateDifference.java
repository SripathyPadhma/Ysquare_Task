import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateDifference {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter date and time (DD-MM-YYYY hh:mm): ");
        String inputDate = sc.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date enteredDate = dateFormat.parse(inputDate);
            Date currentDate = new Date();

            long diffMillis = enteredDate.getTime() - currentDate.getTime();

            if (diffMillis >= 0) {
                long minutes = diffMillis / (1000 * 60);
                long days = diffMillis / (1000 * 60 * 60 * 24);
                long weeks = days / 7;
                long months = (days * 12) / 365;
                long years = months / 12;

                if (years > 0) {
                    System.out.println("Difference in years: " + years);
                } else if (months > 0) {
                    System.out.println("Difference in months: " + months);
                } else if (weeks > 0) {
                    System.out.println("Difference in weeks: " + weeks);
                } else if (days > 0) {
                    System.out.println("Difference in days: " + days);
                } else {
                    System.out.println("Difference in minutes: " + minutes);
                }
            } else {
                System.out.println("Entered date is in the past");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format");
        }
    }
}
