package mainClasses;

import java.util.Date;

public class Request {
    String datereq;
    int ReserverIDreq;
    int RoomIDreq;
    int ReqType;
    /*1= make reservation request
    * 2= delete reservation request
    * 3= date modification request
    */

    public int getReqType() {
        return ReqType;
    }

    public void setReqType(int reqType) {
        ReqType = reqType;
    }

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
