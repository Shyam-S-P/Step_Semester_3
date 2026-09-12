import java.util.Scanner;

class BusRoute {
    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode,
                    String routeName,
                    int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    public int compareTo(BusRoute other) {
        int result = Integer.compare(this.priority, other.priority);

        if (result != 0)
            return result;

        result = this.routeCode.compareToIgnoreCase(other.routeCode);

        if (result != 0)
            return result;

        result = Integer.compare(this.routeName.length(),
                                 other.routeName.length());

        if (result != 0)
            return result;

        return 0;
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        BusRoute[] result = routes.clone();

        // Stable insertion sort: equal routes are never swapped.
        for (int i = 1; i < result.length; i++) {
            BusRoute current = result[i];
            int j = i - 1;

            while (j >= 0 && result[j].compareTo(current) > 0) {
                result[j + 1] = result[j];
                j--;
            }

            result[j + 1] = current;
        }

        return result;
    }

    public String getRouteCode() {
        return routeCode;
    }
}

public class Week_4_BusRouteRankingEngine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of routes:");
        int n = sc.nextInt();
        sc.nextLine();

        BusRoute[] routes = new BusRoute[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter route code:");
            String code = sc.nextLine();

            System.out.println("Enter route name:");
            String name = sc.nextLine();

            System.out.println("Enter priority, or -1 for default:");
            int priority = sc.nextInt();
            sc.nextLine();

            if (priority == -1)
                routes[i] = new BusRoute(code, name);
            else
                routes[i] = new BusRoute(code, name, priority);
        }

        BusRoute[] ranked = BusRoute.rankRoutes(routes);

        System.out.println("Ranked routes:");
        for (BusRoute route : ranked)
            System.out.println(route.getRouteCode());

        sc.close();
    }
}
