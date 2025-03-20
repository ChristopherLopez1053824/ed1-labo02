package ed.lab;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class E01InvertBT {
    //PREORDER
    public TreeNode<Integer> invertTree(TreeNode<Integer> root) {
        if (root == null)
            return null;
        TreeNode<Integer> Aux = root.right;
        root.right = root.left;
        root.left = Aux;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

}
