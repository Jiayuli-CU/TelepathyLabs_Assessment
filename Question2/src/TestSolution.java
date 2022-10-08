import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSolution {
    @Test
    public void testConvertOperationToTree() {
        Solution solution = new SolutionImplementer();
        TreeNode root = new TreeNode('-');
        root.setLeft(new TreeNode('*'));
        root.setRight(new TreeNode('*'));
        root.getLeft().setLeft(new TreeNode('3'));
        root.getLeft().setRight(new TreeNode('4'));
        root.getRight().setLeft(new TreeNode('2'));
        root.getRight().setRight(new TreeNode('5'));
        Assert.assertTrue(treeEquals(root, solution.convertOperationToTree("3*4-2*5")));
        Assert.assertTrue(treeEquals(null, solution.convertOperationToTree("")));
    }

    private boolean treeEquals(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return root1.getValue() == root2.getValue()
                && treeEquals(root1.getLeft(), root2.getLeft())
                && treeEquals(root1.getRight(), root2.getRight());
    }

    @Test
    public void testCalculateOperation() {
        Solution solution = new SolutionImplementer();
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
