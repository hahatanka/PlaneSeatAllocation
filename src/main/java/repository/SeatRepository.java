package repository;

import users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatRepository {
    private static DBManager dataBaseManager = new DBManager();
    Connection connection = DBManager.getConnection();

    public static void createSeatTable() throws SQLException {
        String query =
                "CREATE TABLE IF NOT EXISTS seats (id int not null auto_increment," +
                "username varchar(100) not null," +
                "type varchar(100) ," +
                "seat_number int not null unique," +
                "primary key(id))";

        PreparedStatement preparedStatement = dataBaseManager.getConnection().prepareStatement(query);
        preparedStatement.execute();
        String query2 = "INSERT INTO seats (seat_number)" +
                "VALUES(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13)," +
                "(14),(15),(16),(17),(18),(19),(20),(21),(22),(23),(24)";
        try {
            PreparedStatement preparedStatement2 = dataBaseManager.getConnection().prepareStatement(query2);
            preparedStatement2.execute();
        }catch(SQLException e){
            System.out.println("table already exists");
        }
        System.out.println("Table ok");
    }


    public void addToDB(User user) throws SQLException {
        String query = "UPDATE seats SET username = ?, type = ? WHERE seat_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getUserType().name());
        preparedStatement.setInt(3,user.getSeatNumber());

        preparedStatement.execute();
    }

    public User searchSeat(int seatNumber) throws SQLException {
        String query = "SELECT * FROM seats WHERE seat_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, seatNumber);
        preparedStatement.execute();

        ResultSet results = preparedStatement.getResultSet();

        User user = null;

        while (results.next()) { //by calling results.next, java checks the db table if there's any info available
            String userName = results.getString("username");
            int seat = results.getInt("seat_number");
            user = new User(userName, seat);
        }
        preparedStatement.close();
        return user;
    }
}
