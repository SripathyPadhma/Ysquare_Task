import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataComparison{
    public static void main(String[] args) {
        List<String> eFile1 = readEmailsFromFile("Email1.txt");
        List<String> eFile2 = readEmailsFromFile("Email2.txt");

        Set<String> commonEmails = new HashSet<>(eFile1);
        commonEmails.retainAll(eFile2);

        Set<String> uniqueEmailsFile1 = new HashSet<>(eFile1);
        uniqueEmailsFile1.removeAll(eFile2);

        Set<String> uniqueEmailsFile2 = new HashSet<>(eFile2);
        uniqueEmailsFile2.removeAll(eFile1);

        Set<String> allUniqueEmails = new HashSet<>(eFile1);
        allUniqueEmails.addAll(eFile2);

        System.out.println("Emails present in both files:");
        for (String email : commonEmails) {
            System.out.println(email);
        }

        System.out.println("\nEmails present in file1 but not in file2:");
        for (String email : uniqueEmailsFile1) {
            System.out.println(email);
        }

        System.out.println("\nEmails present in file2 but not in file1:");
        for (String email : uniqueEmailsFile2) {
            System.out.println(email);
        }

        System.out.println("\nAll unique emails in both files:");
        for (String email : allUniqueEmails) {
            System.out.println(email);
        }
    }
    public static List<String> readEmailsFromFile(String filename) {
        List<String> emails = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                emails.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emails;
    }
}
