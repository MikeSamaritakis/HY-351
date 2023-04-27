package mainClasses;

import java.util.Date;

public class Invitation{
    int ContactInfo;
    Date date;
    int RoomID;

    public int getContactInfo() {
        return ContactInfo;
    }

    public void setContactInfo(int contactInfo) {
        ContactInfo = contactInfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int roomID) {
        RoomID = roomID;
    }
}
