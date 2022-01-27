
import controller.SeatsController;
import users.SeatTypes;
import users.User;

import javax.swing.*;
import java.sql.SQLException;

public class Menu {
    SeatsController seatsController = new SeatsController();
    String userChoice;

    public boolean start() throws SQLException {
        boolean random = false;
        String[] choices = {"Manual seat allocation", "Random seat allocation"};
        userChoice = (String) JOptionPane.showInputDialog(null,
                "Welcome to the airplane seat booking system", "WELCOME",
                JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

        if (userChoice == null) {
            handleExit();
        }
        switch (userChoice) {
            case "Manual seat allocation":
                random = false;
                chooseSeat(false);
                break;
            case "Random seat allocation":
                random = true;
                chooseSeat(true);

                break;
            default:
                break;
        }return random;
    }

    public void chooseSeat(boolean random) throws SQLException {
        int seatNumber;
        String[] choices = {"ECONOMY", "BUSINESS"};
        String name = getUserInput("name");
        String type = (String) JOptionPane.showInputDialog(null,
                "Choose client type", "CHOOSE TYPE", JOptionPane.QUESTION_MESSAGE, null,
                choices, choices[0]);

        switch (type) {
            case "ECONOMY":
                seatNumber = seatsController.seatAllocationPlans(random, "ECONOMY");
                System.out.println(seatNumber);
                User user = new User(name, SeatTypes.ECONOMY, seatNumber);
                if (seatNumber >= 0) seatsController.allocateSeat(user);
                else {
                    JOptionPane.showMessageDialog(null, "SORRY, SOLD OUT!");
                }
                break;
            case "BUSINESS":
                seatNumber = seatsController.seatAllocationPlans(random, "BUSINESS");
                System.out.println(seatNumber);
                User userB = new User(name, SeatTypes.BUSINESS, seatNumber);
                if (seatNumber >= 0) seatsController.allocateSeat(userB);
                else {
                    JOptionPane.showMessageDialog(null, "SORRY, SOLD OUT!");
                }
                break;
        }
        start();
    }


    private String getUserInput(String message) {
        return JOptionPane.showInputDialog("Please enter passenger's" + message);
    }

    private void handleExit() throws SQLException {
        int userChoice = JOptionPane.showConfirmDialog(null, "Exit the program?","WARNING",JOptionPane.YES_NO_OPTION);

        if (userChoice == 0) {
            System.exit(0);
        }
        start();
    }
}
