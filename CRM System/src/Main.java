import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Client");
            System.out.println("2. Remove Client");
            System.out.println("3. Change Client Details");
            System.out.println("4. Display Client Database");
            System.out.println("5. Search by industry");
            System.out.println("9. Exit");

            try {
              int  choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        int clientId = 0;

                        while (true) {
                            System.out.println("Enter client ID:");

                            try {
                                clientId = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character
                                break; // Exit the loop if input is valid
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input format. Please enter a valid integer ID:");
                                scanner.nextLine(); // Clear the invalid input
                            }
                        }


                        System.out.println("Enter client name:");
                        String clientName = scanner.nextLine();

                        System.out.println("Enter client company name:");
                        String compName = scanner.nextLine();

                        System.out.println("Enter client industry:");
                        String indName = scanner.nextLine();

                        double clientRevenue = 0;

                        while (true) {
                            System.out.println("Enter client revenue:");

                            try {
                                clientRevenue = scanner.nextDouble();
                                scanner.nextLine(); // Consume newline character
                                break; // Exit the loop if input is valid
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input format. Please enter a valid  value:");
                                scanner.nextLine(); // Clear the invalid input
                            }
                        }

                        Client newClient = new Client();
                        newClient.setId(clientId);
                        newClient.setName(clientName);
                        newClient.setCompanyName(compName);
                        newClient.setIndustry(indName);
                        newClient.setRevenue(clientRevenue);

                        manager.addClient(newClient);
                        break;
                    case 2:
                        int idtoremove = 0;
                        while (true) {
                            System.out.println("Enter client ID:");

                            try {
                                idtoremove = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character
                                break; // Exit the loop if input is valid
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input format. Please enter a valid integer ID:");
                                scanner.nextLine(); // Clear the invalid input
                            }
                        }

                        manager.RemoveClient(idtoremove);
                        break;
                    case 3:
                        int IdToUpdate = 0;

                        while (true) {
                            System.out.println("Enter client ID to update details:");

                            try {
                                IdToUpdate = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character
                                break; // Exit the loop if input is valid
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input format. Please enter a valid integer employee ID:");
                                scanner.nextLine(); // Clear the invalid input
                            }
                        }

                        System.out.println("Enter new client name:");
                        String newName = scanner.nextLine();

                        System.out.println("Enter new Company name:");
                        String newCompanyName = scanner.nextLine();

                        System.out.println("Enter new industry:");
                        String newIndustryName = scanner.nextLine();
                        double newRevenue = 0;

                        while (true) {
                            System.out.println("Enter new employee revenue:");

                            try {
                                newRevenue = scanner.nextDouble();
                                scanner.nextLine(); // Consume newline character
                                break; // Exit the loop if input is valid
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input format. Please enter a valid double value for revenue:");
                                scanner.nextLine(); // Clear the invalid input
                            }
                        }

                        manager.changeClientDetails(IdToUpdate, newName, newCompanyName, newIndustryName, newRevenue);
                        break;
                    case 4:
                        System.out.println("\nCurrent Employee Database:");
                        for (Client client : manager.getEmployeeDatabase()) {
                            System.out.println("ID: " + client.getId() + ", Name : " + client.getName() + ", Company name : " + client.getCompanyName() +
                                    ", Industry : " + client.getIndustry() + ", Client revenue: " + client.getRevenue());
                        }
                        break;
                    case 5:
                        System.out.println("Enter industry name:");
                        String industry = scanner.nextLine();
                        manager.SearchIndustry(industry);
                        break;


                    case 9:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input format. Please enter a valid menu option.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        scanner.close();

    }

}