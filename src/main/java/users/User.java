package users;

public class User {
    String name;
    SeatTypes userType;
    int seatNumber;

    public User() {
    }

    public User(String name, int seatNumber) {
        this.name = name;
        this.seatNumber = seatNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SeatTypes getUserType() {
        return userType;
    }

    public void setUserType(SeatTypes userType) {
        this.userType = userType;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public User(String name, SeatTypes userType, int seatNumber) {
        this.name = name;
        this.userType = userType;
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "seatNumber=" + seatNumber +
                '}';
    }
}
