import java.util.Scanner;

class LibraryMember {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;

    public LibraryMember() {
        this(null, null);
    }

    public LibraryMember(String name) {
        this(null, name);
    }

    public LibraryMember(String membershipId, String name) {
        this.name = name;

        if (membershipId != null)
            this.membershipId = membershipId;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (membershipId == null)
            membershipId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        if (answer != null)
            securityAnswerHash = Integer.toHexString(answer.hashCode());
    }
}

public class Week_5_LibraryMemberJavaBean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter member name:");
        String name = sc.nextLine();

        System.out.println("Enter membership ID, or press Enter for none:");
        String id = sc.nextLine();

        LibraryMember member;

        if (id.isEmpty())
            member = new LibraryMember(name);
        else
            member = new LibraryMember(id, name);

        System.out.println("Membership ID: " +
                member.getMembershipId());

        System.out.println("Enter membership ID to set:");
        String firstId = sc.nextLine();
        member.setMembershipId(firstId);

        System.out.println("Enter another membership ID:");
        String secondId = sc.nextLine();
        member.setMembershipId(secondId);

        System.out.println("Final membership ID: " +
                member.getMembershipId());

        System.out.println("Enter premium member status (true/false):");
        boolean premium = Boolean.parseBoolean(sc.nextLine());
        member.setPremiumMember(premium);

        System.out.println("Premium member: " +
                member.isPremiumMember());

        System.out.println("Enter security answer:");
        String answer = sc.nextLine();
        member.setSecurityAnswer(answer);

        System.out.println("Security answer stored securely.");

        sc.close();
    }
}
