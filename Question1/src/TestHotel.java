import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TestHotel {
    @Test
    public void testAssignRoom() {
        Hotel hotel = new Hotel();
        Assert.assertEquals(hotel.assignRoom(), "1A");
        Assert.assertEquals(hotel.assignRoom(), "1B");
        Assert.assertEquals(hotel.assignRoom(), "1C");
        // test case for no available rooms
        for (int i = 0; i < 17; i++) {
            hotel.assignRoom();
        }
        Assert.assertEquals(hotel.assignRoom(), "");
    }

    @Test
    public void testCheckoutRoom() {
        Hotel hotel = new Hotel();
        Assert.assertEquals(hotel.checkoutRoom("1A"), false);
        hotel.assignRoom();
        Assert.assertEquals(hotel.checkoutRoom("1A"), true);
    }

    @Test
    public void testMarkRoomCleaned() {
        Hotel hotel = new Hotel();
        Assert.assertEquals(hotel.markRoomCleaned("1A"), false);
        hotel.assignRoom();
        Assert.assertEquals(hotel.markRoomCleaned("1A"), false);
        hotel.checkoutRoom("1A");
        Assert.assertEquals(hotel.markRoomCleaned("1A"), true);
    }

    @Test
    public void testMarkRoomForRepair() {
        Hotel hotel = new Hotel();
        Assert.assertEquals(hotel.markRoomForRepair("1A"), false);
        hotel.assignRoom();
        Assert.assertEquals(hotel.markRoomForRepair("1A"), false);
        hotel.checkoutRoom("1A");
        Assert.assertEquals(hotel.markRoomForRepair("1A"), true);
    }

    @Test
    public void testListAllAvailableRooms() {
        Hotel hotel = new Hotel();
        String[] list = {
                "1A", "1B", "1C", "1D", "1E",
                "2A", "2B", "2C", "2D", "2E",
                "3A", "3B", "3C", "3D", "3E",
                "4A", "4B", "4C", "4D", "4E",
        };
        Assert.assertTrue(listEquals(Arrays.asList(list), hotel.listAllAvailableRooms()));
        hotel.assignRoom();
        hotel.assignRoom();
        list = new String[] {
                "1C", "1D", "1E",
                "2A", "2B", "2C", "2D", "2E",
                "3A", "3B", "3C", "3D", "3E",
                "4A", "4B", "4C", "4D", "4E",
        };
        Assert.assertTrue(listEquals(Arrays.asList(list), hotel.listAllAvailableRooms()));
    }

    private boolean listEquals(List<String> list1, List<String> list2) {
        if (list1 == null && list2 == null) {
            return true;
        }
        if (list1 == null || list2 == null) {
            return false;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
