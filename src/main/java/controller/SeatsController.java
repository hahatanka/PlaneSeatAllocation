package controller;

import repository.SeatRepository;
import users.SeatTypes;
import users.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class SeatsController implements ActionListener {
    JButton[] buttonE = new JButton[24];
    JButton[] buttonB = new JButton[24];
    public int choice = 0;
    JTextField seatChoice = new JTextField(10);
    SeatRepository seatRepository = new SeatRepository();


    public void allocateSeat(User user){
        try {
            seatRepository.addToDB(user);
            System.out.println("seat reserved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int seatAllocationPlans(boolean random, String type) throws SQLException {
        try {
            SeatRepository.createSeatTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (random == false && type.equalsIgnoreCase("ECONOMY")) {
            return createManualPanelEc();
        }else if(random && type.equalsIgnoreCase("ECONOMY")){
            return createRandomPanelEc();
        }else if(random == false && type.equalsIgnoreCase("BUSINESS")){
            return createManualPanelBus();
        }else if(random && type.equalsIgnoreCase("BUSINESS")){
            return createRandomPanelBus();
        }
        return 0;
    }

    private int createRandomPanelBus() throws SQLException {
        JPanel myPanel = new JPanel(new GridLayout(7, 4));
        int i = 0;
        for (i = 0; i < 4; i++) {
            buttonB[i] = new JButton("BUSINESS " + (i + 1));
            buttonB[i].setForeground(Color.BLUE);
            myPanel.add(buttonB[i]);

            checkAvailability(i + 1);
            if (checkAvailability(i + 1) == -1) buttonB[i].setForeground(Color.RED);

            myPanel.add(buttonB[i]);
        }
        for (i = 4; i <= 23; i++) {
            buttonB[i] = new JButton("" + (i + 1));
            buttonB[i].setForeground(Color.GRAY);
            checkAvailability(i + 1);
            if (checkAvailability(i+1) == -1) buttonB[i].setForeground(Color.RED);

            myPanel.add(buttonB[i]);
        }
        myPanel.add(Box.createHorizontalStrut(1));
        //choose a random seaT FROM FREE SEATS
        int min = 0;
        int max = 3;
        int randomSeat;
        do{
            randomSeat = ((int) Math.floor(Math.random() * (max - min + 1) + min));
        } while (buttonB[randomSeat].getForeground() == Color.RED);
        choice = randomSeat + 1;

        myPanel.add(new JLabel("YOU HAVE CHOSEN SEAT"));
        myPanel.add(seatChoice);
        seatChoice.setText(String.valueOf(choice));

        int message= JOptionPane.showConfirmDialog(null, myPanel,
                "CHOOSE A SEAT", JOptionPane.OK_CANCEL_OPTION);
        if(message != 2) JOptionPane.showMessageDialog(null,
                " You have chosen seat " + choice + ", type: " + SeatTypes.BUSINESS);
        return choice;
    }


    private int createManualPanelBus() throws SQLException {
        JPanel myPanel = new JPanel(new GridLayout(7, 4));
            int i = 0;
            for (i = 0; i < 4; i++) {
                buttonB[i] = new JButton("BUSINESS " + (i + 1));
                buttonB[i].setForeground(Color.BLUE);
                checkAvailability(i + 1);
                if (checkAvailability(i + 1) == -1) buttonB[i].setForeground(Color.RED);

                myPanel.add(buttonB[i]);
                buttonB[i].addActionListener(this);
            }
            for (i = 4; i <= 23; i++) {
                buttonB[i] = new JButton("" + (i + 1));
                buttonB[i].setForeground(Color.GRAY);

                checkAvailability(i + 1);
                if (checkAvailability(i + 1) == -1) buttonB[i].setForeground(Color.RED);

                myPanel.add(buttonB[i]);
                buttonB[i].addActionListener(this);
            }
            myPanel.add(Box.createHorizontalStrut(1));
            myPanel.add(new JLabel("YOU HAVE CHOSEN SEAT"));
            myPanel.add(seatChoice);
            seatChoice.setText(String.valueOf(choice));

            JOptionPane.showConfirmDialog(null, myPanel,
                    "CHOOSE A SEAT", JOptionPane.OK_CANCEL_OPTION);

                JOptionPane.showMessageDialog(null, " You have chosen seat " + choice +
                    ", type: " + SeatTypes.BUSINESS);
            return choice;
    }

    private int createRandomPanelEc() throws SQLException {
        JPanel myPanel = new JPanel(new GridLayout(7, 4));

        int i = 0;
        for (i = 0; i < 4; i++) {
            buttonE[i] = new JButton("BUSINESS " + (i + 1));
            buttonE[i].setForeground(Color.LIGHT_GRAY);
            checkAvailability(i + 1);

            if (checkAvailability(i + 1) == -1) {
                buttonE[i].setForeground(Color.RED);
            }
            myPanel.add(buttonE[i]);
        }
        for(i = 4; i <= 23; i++) {
            buttonE[i] = new JButton("" + (i + 1));
            buttonE[i].setForeground(Color.BLUE);

            checkAvailability(i+1);
            if (checkAvailability(i+1) == -1 ){
                buttonE[i].setForeground(Color.RED);
            }
            myPanel.add(buttonE[i]);
        }
        myPanel.add(Box.createHorizontalStrut(1));

        int min = 4;
        int max = 23;
        int randomSeat;
        do {
            randomSeat = ((int) Math.floor(Math.random() * (max - min + 1) + min));
        } while (buttonE[randomSeat].getForeground() == Color.RED);
        choice = randomSeat + 1;

        myPanel.add(new JLabel("THE SEAT CHOSEN: "));
        myPanel.add(seatChoice);
        seatChoice.setText(String.valueOf(choice));

        int message = JOptionPane.showConfirmDialog(null, myPanel,
                "CHOOSE A SEAT", JOptionPane.OK_CANCEL_OPTION);

        if(message != 2) JOptionPane.showMessageDialog(null, " You have chosen seat " +  choice + ", type: " + SeatTypes.ECONOMY);
        return choice;
    }


    private int createManualPanelEc() throws SQLException {
        JPanel myPanel = new JPanel(new GridLayout(7, 4));

            int i = 0;
            for (i = 0; i < 4; i++) {
                buttonE[i] = new JButton("BUSINESS " + (i + 1));
                buttonE[i].setForeground(Color.GRAY);

                checkAvailability(i + 1);
                if (checkAvailability(i + 1) == -1) {
                    buttonE[i].setForeground(Color.RED);
                }

                myPanel.add(buttonE[i]);
                buttonE[i].addActionListener(this);
            }
            for (i = 4; i <= 23; i++) {
                buttonE[i] = new JButton("" + (i + 1));
                buttonE[i].setForeground(Color.BLUE);

                checkAvailability(i + 1);
                if (checkAvailability(i + 1) == -1) {
                    buttonE[i].setForeground(Color.RED);
                }
                myPanel.add(buttonE[i]);
                buttonE[i].addActionListener(this);
            }
            myPanel.add(Box.createHorizontalStrut(1));
            myPanel.add(new JLabel("YOU HAVE CHOSEN SEAT"));
            myPanel.add(seatChoice);
            seatChoice.setText(String.valueOf(choice));

            int message = JOptionPane.showConfirmDialog(null, myPanel,
                    "CHOOSE A SEAT", JOptionPane.OK_CANCEL_OPTION);
            if (message != 2)
                JOptionPane.showMessageDialog(null, " You have chosen seat " + choice + ", type: " + SeatTypes.ECONOMY);
            return choice;
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 23; i++) {
            if(e.getSource() == buttonE[i] && i>3 && (buttonE[i].getForeground() == Color.BLUE)) {
                choice = i + 1;
                seatChoice.setText(String.valueOf(choice));

            }else if (e.getSource() == buttonE[i] && i>3 && (buttonE[i].getForeground() == Color.RED)) {
                JOptionPane.showMessageDialog(null, "This seat is taken. Choose from green-coloured seats");

            }else if (e.getSource() == buttonE[i] && (i <3)) {

                JOptionPane.showMessageDialog(null, "You cannot choose BUSINESS class seats, please choose a different seat ");

            }else if(e.getSource() == buttonB[i] && (i <3)){
                choice = i+1;
                seatChoice.setText(String.valueOf(choice));

            }else if (e.getSource() == buttonB[i] && i<3 && (buttonB[i].getForeground() == Color.RED)) {
                JOptionPane.showMessageDialog(null, "This seat is taken. Choose from blue-coloured seats");

            }else if(e.getSource() == buttonB[i] && (i >3)){
                JOptionPane.showMessageDialog(null,"Please choose from BUSINESS type of seats or change the client type");
            }
        }
    }

    public int checkAvailability(int seatNumber) throws SQLException {
        User user = seatRepository.searchSeat(seatNumber);
        if(user.getName().equalsIgnoreCase("null")) return seatNumber;
        else if(user.getName() != "null") {return -1;}
        //check with DB if user name != null;
        return -1;
    }
}
