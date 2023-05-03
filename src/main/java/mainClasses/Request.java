package mainClasses;

import java.util.Date;

public class Request {
    String datereq;
    int ReserverIDreq;
    int RoomIDreq;

    public String getDatereq() {
        return datereq;
    }

    public void setDatereq(String datereq) {
        this.datereq = datereq;
    }

    public int getReserverIDreq() {
        return ReserverIDreq;
    }

    public void setReserverIDreq(int reserverIDreq) {
        ReserverIDreq = reserverIDreq;
    }

    public int getRoomIDreq() {
        return RoomIDreq;
    }

    public void setRoomIDreq(int roomIDreq) {
        RoomIDreq = roomIDreq;
    }
}
