import java.util.Scanner;

public class Week_2_MaskedPhoneNumberFormatter {

    static String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() != 10)
            return "Invalid phone number";

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i)))
                return "Invalid phone number";
        }

        String lastFour = phone.substring(6);
        StringBuilder result = new StringBuilder("XXXXXX");
        result.insert(6, "-");
        result.append(lastFour);

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter phone number:");
        String phone = sc.nextLine();

        System.out.println(maskPhoneNumber(phone));

        sc.close();
    }
}
