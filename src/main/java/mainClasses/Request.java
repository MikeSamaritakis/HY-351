package mainClasses;

import java.util.Date;

public class Request {
    String datereq;
    int ReserverIDreq;
    int RoomIDreq;

    public String getDatereq() {
        return datereq;
    }

    public void setDatereq(String date) {
        this.datereq = date;
    }

    public int getReserverIDreq() {
        return ReserverIDreq;
    }

    public void setReserverIDreq(int reserverID) {
        ReserverIDreq = reserverID;
    }

    public int getRoomIDreq() {
        return RoomIDreq;
    }

    public void setRoomIDreq(int roomID) {
        RoomIDreq = roomID;
    }
}
