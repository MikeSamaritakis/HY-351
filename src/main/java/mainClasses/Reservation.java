package mainClasses;

import java.util.Date;

public class Reservation {
    Date date;
    int ReserverID;
    int RoomID;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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