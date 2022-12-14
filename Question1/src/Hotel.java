import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private static final int FLOORS = 4;
    private static final int ROOMS_EACH_FLOOR = 5;
    private Room[][] rooms;

    public Hotel() {
        rooms = new Room[FLOORS][ROOMS_EACH_FLOOR];
        for (int floor = 1; floor <= FLOORS; floor++) {
            for (int room_num = 0; room_num < ROOMS_EACH_FLOOR; room_num++) {
                rooms[floor - 1][room_num] = new Room(floor + "" + (char)('A' + room_num));
            }
        }
    }

    // method for requesting for room assignment, which reply with the assigned
    // room number upon success
    public String assignRoom() {
        for (int floor = 1; floor <= FLOORS; floor++) {
            if ((floor & 1) == 1) {
                for (int room_num = 0; room_num < ROOMS_EACH_FLOOR; room_num++) {
                    try {
                        rooms[floor - 1][room_num].checkin();
                    } catch (Exception e) {
                        continue;
                    }
                    return rooms[floor - 1][room_num].getId();
                }
            } else {
                for (int room_num = ROOMS_EACH_FLOOR - 1; room_num >= 0; room_num--) {
                    try {
                        rooms[floor - 1][room_num].checkin();
                    } catch (Exception e) {
                        continue;
                    }
                    return rooms[floor - 1][room_num].getId();
                }
            }
        }
        return "";
    }

    // method to check out of a room
    public boolean checkoutRoom(String roomId) {
        for (int floor = 1; floor <= FLOORS; floor++) {
            for (int room_num = 0; room_num < ROOMS_EACH_FLOOR; room_num++) {
                if (rooms[floor - 1][room_num].getId().equals(floor + "" + (char)('A' + room_num))) {
                    try {
                        rooms[floor - 1][room_num].checkout();
                    } catch (Exception e) {
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // method to mark a room cleaned (Available)
    public boolean markRoomCleaned(String roomId) {
        for (int floor = 1; floor <= FLOORS; floor++) {
            for (int room_num = 0; room_num < ROOMS_EACH_FLOOR; room_num++) {
                if (rooms[floor - 1][room_num].getId().equals(floor + "" + (char)('A' + room_num))) {
                    try {
                        rooms[floor - 1][room_num].clean();
                    } catch (Exception e) {
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // method to mark a room for repair
    public boolean markRoomForRepair(String roomId) {
        for (int floor = 1; floor <= FLOORS; floor++) {
            for (int room_num = 0; room_num < ROOMS_EACH_FLOOR; room_num++) {
                if (rooms[floor - 1][room_num].getId().equals(floor + "" + (char)('A' + room_num))) {
                    try {
                        rooms[floor - 1][room_num].outOfService();
                    } catch (Exception e) {
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // method to list all the available rooms
    public List<String> listAllAvailableRooms() {
        List<String> ans = new ArrayList<>();
        for (int floor = 0; floor < FLOORS; floor++) {
            for (Room room : rooms[floor]) {
                if (room.getStatus().equals(RoomStatus.Available)) {
                    ans.add(room.getId());
                }
            }
        }
        return ans;
    }
}
