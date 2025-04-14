import services.LoyaltyService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var service = new LoyaltyService();
        System.out.println("Program Started. Please enter the commands:");

        while (scanner.hasNextLine()) {
            var input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            var inputList = input.split("\\s+");
            var command = inputList[0];

            try {
                switch (command.toLowerCase()) {
                    case "onboard":
                        service.onboardUser(inputList[1]);
                        break;

                    case "purchase":
                        if (inputList.length == 4) {
                            String user = inputList[1];
                            double amount = Double.parseDouble(inputList[2]);
                            int pointsToRedeem = Integer.parseInt(inputList[3]);
                            service.purchase(user, amount, pointsToRedeem);
                        } else {
                            System.out.println("Invalid input format for purchase.");
                        }
                        break;

                    case "getuserstats":
                        service.printUserStats(inputList[1]);
                        break;

                    default:
                        System.out.println("Unknown command.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}