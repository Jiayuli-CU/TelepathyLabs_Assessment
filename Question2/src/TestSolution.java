import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSolution {
    @Test
    public void testConvertOperationToTree() {

    }

    @Test
    public void testCalculateOperation() {
        Solution solution = new Solution();
        String[] operations = {
                "15 / (7 - (1 + 1)) * (-3) - (2 + (1 + 1))",
                "",
                "1 - 2 * 3 + 4 * 5 * (9 - 7)",
                "-10"
        };
        int[] expects = {
                -13,
                0,
                35,
                -10
        };
        for (int i = 0; i < operations.length; i++) {
            Assert.assertEquals(solution.calculateOperation(operations[i]), expects[i]);
        }
    }
}
