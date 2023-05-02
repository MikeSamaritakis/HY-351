package mainClasses;

import java.util.Date;

public class Reservation {
    String date;
    int ReserverID;
    int RoomID;

    public String getDate() {
        return date;
    }

    public void setDate(String date1) {date = date1;}

    public int getReserverID() {
        return ReserverID;
    }

    public void setReserverID(int reserverID) {
        ReserverID = reserverID;
    }

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int roomID) {
        RoomID = roomID;
    }
}