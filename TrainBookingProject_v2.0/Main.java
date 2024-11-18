import java.util.*;
import DataModel.*;

public class Main {
    public static void main(String[] args) {
        TrainTicketSystem train_ticket_system = TrainTicketSystem.getInstance();
        Scanner scanner = new Scanner(System.in);
        User current_LoginedUser = null;

        // Main menu
        // Scenario: current_LoginedUser == null

        while (current_LoginedUser == null) {
            System.out.println("Welcome to Train Ticket System!");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    current_LoginedUser = train_ticket_system.login(username, password);
                    train_ticket_system.setCurrentUser(current_LoginedUser);
                    // System.out.print("\nWelcome " + current_LoginedUser.getUsername() + "!");

                    break;

                case 2:
                    System.out.print("Registeraion Form:\nEnter username: ");
                    String register_username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String register_pwd = scanner.nextLine();

                    boolean result = train_ticket_system.register(register_username, register_pwd);
                    if (result) {
                        System.out.println("Register successfully.");
                    } else {
                        System.out.println("Username already exists.");
                    }

                    System.out.println("");
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            // while loop for normal user
            while (current_LoginedUser != null && current_LoginedUser.getRole() == "normal") {
                // check announcement
                if (current_LoginedUser.isReceivedAnnouncement()) {
                    System.out.println(
                            "You have received an announcement: " + current_LoginedUser.getLatestAnnouncement());
                }

                // display finished orders
                train_ticket_system.displayFinishedOrders(current_LoginedUser.getId(), scanner);

                System.out.println();
                train_ticket_system.displayMainMenu();
                System.out.print("Choose an option: ");

                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        train_ticket_system.orderTicket(scanner);
                        break;

                    case 2:
                        train_ticket_system.checkTicket(scanner);
                        break;

                    // CS function
                    case 3:
                        train_ticket_system.cs(scanner);
                        break;

                    case 4:
                        // Subscribe and receive messages
                        train_ticket_system.subscribeUser(current_LoginedUser.getId());
                        System.out.println("You have subscribed to receive messages.");
                        break;
                    
                    case 5:
                        // Check in
                        train_ticket_system.checkIn();
                        System.out.println(current_LoginedUser.getUsername() + " Points: " + current_LoginedUser.getPoints() );
                        break;

                    case 6:
                        // LOGOUT
                        current_LoginedUser = null;
                        System.out.println("Logged out successfully.\n");
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }

            while (current_LoginedUser != null && current_LoginedUser.getRole() == "admin") {
                System.out.println("1. Manage Train Schedule");
                System.out.println("2. View Reports");
                System.out.println("3. Display Users");
                System.out.println("4. Logout");
                System.out.println("5. Add Keyword and Answer");
                System.out.println("6. Update & publish an announcement");
                System.out.println("7. Cancel announcement");

                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.println("Manage Train Schedule");
                        // ... Implement Manage Train Schedule functionality
                        train_ticket_system.manageTrainSchedule(scanner);// Corrected method call
                        break;
                    case 2:
                        System.out.println("View Reports");
                        break;
                    case 3:
                        System.out.println("Display User List");
                        train_ticket_system.displayUserList();
                        break;
                    case 4:
                        // Logout
                        current_LoginedUser = null;
                        System.out.println("Logged out successfully.\n");
                        break;
                    case 5:
                        train_ticket_system.csAdmin(scanner);
                        break;
                    case 6:
                        // Update an Announcement
                        System.out.println("Enter the announcement:");
                        String announcement = scanner.nextLine();
                        train_ticket_system.updateAnnouncement(announcement);
                        System.out.println("Announcement updated successfully.\n");
                        break;
                    case 7:
                        // Cancel an Announcement
                        train_ticket_system.updateAnnouncement(null);
                        System.out.println("Announcement cancelled successfully.\n");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}
