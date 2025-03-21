package ed.lab;

public class E02KthSmallest {
    int Memory=Integer.MAX_VALUE;
    int Contador = 0;
    public int kthSmallest(TreeNode<Integer> root, int k) {
        Smallest(root, k);
        return Memory;
    }


    public void Smallest(TreeNode<Integer> root, int k)
    {
        if (root == null)
            return;

        Smallest(root.left, k);

        Contador++;
        if (Contador == k) {
            Memory = root.value;
            return;
        }

        Smallest(root.right, k);
    }
}