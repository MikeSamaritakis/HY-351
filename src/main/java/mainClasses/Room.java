package mainClasses;

public class Room {
    int RoomID;
    int Capacity;
    int EquipementType;
    int AdminID;

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int roomID) {
        RoomID = roomID;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public int getEquipementType() {
        return EquipementType;
    }

    public void setEquipementType(int equipementType) {
        EquipementType = equipementType;
    }

    public int getAdminID() {
        return AdminID;
    }

    public void setAdminID(int adminID) {
        AdminID = adminID;
    }
}
