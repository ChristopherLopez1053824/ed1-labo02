package ed.lab;

public class E02KthSmallest {

    public int kthSmallest(TreeNode<Integer> root, int k) {
        if(EncontrarK(root,k)==true)
            return k;

        return Smallest(root, k, Integer.MAX_VALUE);
    }

    public boolean EncontrarK(TreeNode<Integer> root, int k)
    {
        if(root==null)
            return false;

        if(k==root.value)
            return true;

        if(k<root.value)
            return EncontrarK(root.left,k);
        if(k>root.value)
            return EncontrarK(root.right,k);
        return false;
    }

    public int Smallest(TreeNode<Integer> root, int k, int Memory)
    {
        if(root==null)
            return Memory;

        if(root.value>k && root.value<Memory)
            Memory =  root.value;

        if(k<root.value)
            return Smallest(root.left, k, Memory);
        if(k>root.value)
            return Smallest(root.right,k, Memory);

        return Memory;
    }
}