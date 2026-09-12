import java.util.Scanner;

class AccessRuleEngine {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("public")) return "ALLOWED";

        if (fieldModifier.equals("private"))
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("default"))
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE") ||
                accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                return "ALLOWED";
            return "DENIED";
        }

        return "DENIED";
    }

    static String describeContext(String accessorContext) {
        String[] parts = accessorContext.toLowerCase().split("_");
        StringBuilder result = new StringBuilder();

        for (String part : parts) {
            result.append(Character.toUpperCase(part.charAt(0)))
                  .append(part.substring(1))
                  .append(" ");
        }

        return result.toString().trim();
    }
}

public class Week_5_CrossPackageInheritanceReach {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter field modifier:");
        String modifier = sc.nextLine();

        System.out.println("Enter accessor context:");
        String context = sc.nextLine();

        System.out.println("Access: " +
                AccessRuleEngine.classifyAccess(modifier, context));

        System.out.println("Enter context to describe:");
        String descriptionContext = sc.nextLine();

        System.out.println(
                AccessRuleEngine.describeContext(descriptionContext));

        sc.close();
    }
}
