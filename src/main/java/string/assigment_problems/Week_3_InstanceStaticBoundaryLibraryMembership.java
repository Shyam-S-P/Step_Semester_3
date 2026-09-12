import java.util.Scanner;

class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }

    /*
     * These fields are static incorrectly because each member needs
     * separate name, memberId and booksIssued values. Static fields
     * are shared by every object, so creating another member overwrites
     * the previous member's data.
     */
}

class LibraryMember {
    private String name;
    private String memberId;
    private int booksIssued;

    static String libraryName = "PageTurner Library";
    static int memberCount = 0;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;
        this.memberId = "LM-" +
                String.format("%04d", 1000 + memberCount);
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }
}

public class Week_3_InstanceStaticBoundaryLibraryMembership {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first member name:");
        String name1 = sc.nextLine();

        System.out.println("Enter first member ID:");
        String id1 = sc.nextLine();

        System.out.println("Enter first member books issued:");
        int books1 = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter second member name:");
        String name2 = sc.nextLine();

        System.out.println("Enter second member ID:");
        String id2 = sc.nextLine();

        System.out.println("Enter second member books issued:");
        int books2 = sc.nextInt();
        sc.nextLine();

        System.out.println("\nBroken version:");

        BrokenLibraryMember broken1 =
                new BrokenLibraryMember(name1, id1, books1);
        BrokenLibraryMember broken2 =
                new BrokenLibraryMember(name2, id2, books2);

        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);

        System.out.println("\nFixed version:");

        LibraryMember member1 =
                new LibraryMember(name1, books1);
        LibraryMember member2 =
                new LibraryMember(name2, books2);

        member1.printMemberCard();
        member2.printMemberCard();

        LibraryMember.printTotalMembers();

        sc.close();
    }
}
