public class Room {
    private String id;
    private RoomStatus status;

    public Room(String id) {
        this.id = id;
        status = RoomStatus.Available;
    }

    // available ---checkin--> occupied
    public void checkin() throws Exception{
        if (!status.equals(RoomStatus.Available)) {
            throw new Exception(String.format("Checkin room %s failed: %v", id, status));
        }
        status = RoomStatus.Occupied;
    }

    // occupied ---checkout--> vacant
    public void checkout() throws Exception {
        if (!status.equals(RoomStatus.Occupied)) {
            throw new Exception(String.format("Checkout room %s failed: %v", id, status));
        }
        status = RoomStatus.Vacant;
    }

    // vacant ---clean--> available
    public void clean() throws Exception {
        if (!status.equals(RoomStatus.Vacant)) {
            throw new Exception(String.format("Clean room %s failed: %v", id, status));
        }
        status = RoomStatus.Available;
    }

    // vacant ---out of service--> repair
    public void outOfService() throws Exception {
        if (!status.equals(RoomStatus.Vacant)) {
            throw new Exception(String.format("Out-of-service room %s failed: %v", id, status));
        }
        status = RoomStatus.Repair;
    }

    // repair ---repaired--> vacant
    public void repaired() throws Exception {
        if (!status.equals(RoomStatus.Repair)) {
            throw new Exception(String.format("Repaired room %s failed: %v", id, status));
        }
        status = RoomStatus.Vacant;
    }

    public String getId() {
        return id;
    }

    public RoomStatus getStatus() {
        return status;
    }
}
