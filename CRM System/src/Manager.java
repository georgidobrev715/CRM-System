import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Manager {
    private List<Client> ClientDatabase;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String DATABASE_FILE = "data.json";

    public Manager() {

        this.ClientDatabase = new ArrayList<>();
        loadClienDatabase(); // Load existing  data from JSON file
    }

    private boolean isValidName(String name) {
        return name != null && name.matches("^[a-zA-Z]+$");
    }

    private boolean isClientIdExists(int Id) {
        for (Client client : ClientDatabase) {
            if (client.getId() == Id) {
                return true;
            }

        }
        return false;
    }
    public void addClient(Client client) {
        if (isClientIdExists(client.getId())) {
            System.out.println("Client with the same ID already exists. Cannot add.");
            return;
        }
        if (!isValidName(client.getName())||!isValidName(client.getIndustry())||!isValidName(client.getCompanyName())) {
            System.out.println("Not valid name.Cannot add");
            return;
        }

        if (client.getRevenue() <= 0) {
            System.out.println("Client revenue must be a positive number. Cannot add.");
            return;
        }

        ClientDatabase.add(client);
        saveEmployeeDatabase();
        System.out.println("Client added successfully.");
    }



    public void RemoveClient(int id) {
        Client ClientToRemove = null;
        for (Client client : ClientDatabase) {
            if (client.getId() == id) {
                ClientToRemove = client;
                break;
            }
        }

        if (ClientToRemove != null) {
            ClientDatabase.remove(ClientToRemove);
            saveEmployeeDatabase();
            System.out.println("Client with ID " + id + " has been removed.");
        } else {
            System.out.println("Client with ID " + id + " not found. No action taken.");
        }
    }
    public void changeClientDetails(int clientId, String newName, String newCompName,String newIndustry,double newRevenue) {
        boolean employeeFound = false;

        if (!isValidName(newName)||!isValidName(newCompName)||!isValidName(newIndustry)) {
            System.out.println("Not valid name.Cannot add");
            return;
        }
        if(newRevenue<=0)
        {
            System.out.println("Not valid revenue.Cannot add");
            return;
        }

        for (Client client : ClientDatabase) {
            if (client.getId() == clientId) {
                client.setName(newName);
                client.setCompanyName(newCompName);
                client.setIndustry(newIndustry);
                client.setRevenue(newRevenue);
                saveEmployeeDatabase();
                System.out.println("Client details updated successfully.");
                employeeFound = true;
                break;
            }
        }

        if (!employeeFound) {
            System.out.println("Client with ID " + clientId + " not found.");
        }
    }
    private void saveEmployeeDatabase() {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(DATABASE_FILE), ClientDatabase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SearchIndustry(String name) {
        boolean found = false;

        for (Client client : getEmployeeDatabase()) {
            if (client.getIndustry().equalsIgnoreCase(name)) {
                System.out.println("ID: " + client.getId() + ", Name: " + client.getName() +
                        ", Company name: " + client.getCompanyName() + ", Industry: " + client.getIndustry() +
                        ", Client revenue: " + client.getRevenue());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No clients found in the industry: " + name);
        }
    }
    public List<Client> getEmployeeDatabase() {
        return ClientDatabase;
    }


    private void loadClienDatabase() {
        try {
            File jsonFile = new File(DATABASE_FILE);
            if (jsonFile.exists()) {
                ClientDatabase = objectMapper.readValue(jsonFile, objectMapper.getTypeFactory().constructCollectionType(List.class, Client.class));
                System.out.println("Client database loaded from JSON file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}