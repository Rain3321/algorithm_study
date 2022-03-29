import java.util.LinkedList;
import java.util.Queue;

public class LEET110 {

  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public boolean isBalanced(TreeNode root) {
    if (root == null) return true;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode curNode = queue.poll();
      if (curNode == null) {
        continue;
      }
      int leftDepth = getDepth(curNode.left);
      int rightDepth = getDepth(curNode.right);

      if (Math.abs(leftDepth - rightDepth) > 1) {
        return false;
      }
      queue.add(curNode.left);
      queue.add(curNode.right);
    }
    return true;
  }

  public int getDepth(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return Math.max(getDepth(node.left) + 1, getDepth(node.right) + 1);
  }
}
