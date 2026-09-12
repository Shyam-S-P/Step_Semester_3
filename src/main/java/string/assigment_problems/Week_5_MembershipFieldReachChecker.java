import java.util.Scanner;

class AccessChecker {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("public"))
            return "ALLOWED";

        if (fieldModifier.equals("private"))
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("default"))
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("protected"))
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {
        int privateAllowed = 0, privateDenied = 0;
        int defaultAllowed = 0, defaultDenied = 0;
        int protectedAllowed = 0, protectedDenied = 0;
        int publicAllowed = 0, publicDenied = 0;

        for (String[] attempt : attempts) {
            String modifier = attempt[0];
            String result = classifyAccess(modifier, attempt[1]);

            if (modifier.equals("private")) {
                if (result.equals("ALLOWED")) privateAllowed++;
                else privateDenied++;
            } else if (modifier.equals("default")) {
                if (result.equals("ALLOWED")) defaultAllowed++;
                else defaultDenied++;
            } else if (modifier.equals("protected")) {
                if (result.equals("ALLOWED")) protectedAllowed++;
                else protectedDenied++;
            } else if (modifier.equals("public")) {
                if (result.equals("ALLOWED")) publicAllowed++;
                else publicDenied++;
            }
        }

        return "private: " + privateAllowed + " allowed / " + privateDenied +
               " denied | default: " + defaultAllowed + " allowed / " +
               defaultDenied + " denied | protected: " + protectedAllowed +
               " allowed / " + protectedDenied + " denied | public: " +
               publicAllowed + " allowed / " + publicDenied + " denied";
    }
}

class LibraryMember {
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId, String branchCode,
                         double finesOwed, String displayName) {
        String id = membershipId == null ? "" : membershipId.trim();

        if (id.length() < 4)
            throw new IllegalArgumentException("Invalid membershipId");

        this.membershipId = id;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

public class Week_5_MembershipFieldReachChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter field modifier:");
        String modifier = sc.nextLine();

        System.out.println("Enter accessor context:");
        String context = sc.nextLine();

        System.out.println("Access: " +
                AccessChecker.classifyAccess(modifier, context));

        System.out.println("Enter number of attempts:");
        int n = Integer.parseInt(sc.nextLine());

        String[][] attempts = new String[n][2];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter modifier:");
            attempts[i][0] = sc.nextLine();

            System.out.println("Enter context:");
            attempts[i][1] = sc.nextLine();
        }

        System.out.println(AccessChecker.summarizeByModifier(attempts));

        System.out.println("Enter membership ID:");
        String id = sc.nextLine();

        try {
            new LibraryMember(id, "BR1", 0, "Priya Nair");
            System.out.println("construction succeeded");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        sc.close();
    }
}
