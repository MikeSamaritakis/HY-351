package mainClasses;

public class Room {
    int RoomID;
    int Capacity;
    int Availability;
    int EquipementType;

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

    public int getAvailability() {
        return Availability;
    }

    public void setAvailability(int availability) {
        Availability = availability;
    }

    public int getEquipementType() {
        return EquipementType;
    }

    public void setEquipementType(int equipementType) {
        EquipementType = equipementType;
    }
}
