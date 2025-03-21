package ed.lab;

import java.util.Comparator;

public class E03AVLTree<T> {
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //Metodo comparator
    private final Comparator<T> comparator;
    private TreeNode<T> root;
    public E03AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //Metodos para insertar
    public void insert(T value) {
        root = insert(root, value);
    }
    private TreeNode<T> insert(TreeNode<T> root, T value) {
        if (root == null)
            return new TreeNode<>(value);
        if (comparator.compare(value, root.value) == 0) {
            return root;
        }
        if (comparator.compare(value, root.value) < 0) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        root = balanceBST(root);
        return root;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //Metodos para eliminar
    public void delete(T value) {
        root = remove(root, value);
    }

    private TreeNode<T> remove(TreeNode<T> root, T value) {
        if (root == null)
            return null;

        if (comparator.compare(value, root.value) < 0) {
            root.left = remove(root.left, value);
        }
        else if (comparator.compare(value, root.value) > 0) {
            root.right = remove(root.right, value);
        }
        else
        {
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;
            root.value = findMin(root.right).value;
            root.right = remove(root.right, root.value);
        }
        root = balanceBST(root);
        return root;
    }
    private TreeNode<T> findMin(TreeNode<T> root) {
        if (root == null)
            return null;

        TreeNode<T> current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //Metodo para buscar
    public T search(T value) {
        if(EncontrarK(root,value)==true)
            return value;
        return null;
    }

    public boolean EncontrarK(TreeNode<T> root, T value)
    {
        if(root==null)
            return false;

        if(comparator.compare(value, root.value) ==0)
            return true;

        if(comparator.compare(value, root.value)<0)
            return EncontrarK(root.left,value);
        if(comparator.compare(value, root.value)>0)
            return EncontrarK(root.right,value);
        return false;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //Metodo de obtener altura
    public int height() {
        return Altura(root);
    }

    private int Altura(TreeNode<T> root)
    {
        if(root!=null)
        {
            int L = Altura(root.left);
            int R = Altura(root.right);
            if(L>R)
                return 1+L;
            else
                return 1+R;
        }
        else
            return 0;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // Metodo para obtener
    int Conteo = 0;
    public int size() {
        return countNodes(root);
    }

    public int countNodes(TreeNode<T> root) {
        if(root==null)
        {
            return 0;
        }
        Conteo ++;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //Balance  de arbol
    public TreeNode<T> balanceBST(TreeNode<T> root) {
        if (root == null)
            return root;
        int balanceFactor = FactordeCoreccion(root);
        if (balanceFactor > 1) {
            if (FactordeCoreccion(root.left) > 0) {
                root = RotacionDerecha(root);
            } else {
                root.left = RotacionIzquierda(root.left);
                root = RotacionDerecha(root);
            }
        }
        else if (balanceFactor < -1) {
            if (FactordeCoreccion(root.right) < 0) {
                root = RotacionIzquierda(root);
            } else {
                root.right = RotacionDerecha(root.right);
                root = RotacionIzquierda(root);
            }
        }

        return root;
    }

    private TreeNode<T> RotacionDerecha(TreeNode<T> root) {
        if (root == null || root.left == null) return root;

        TreeNode newRoot = root.left;
        TreeNode Aux = newRoot.right;

        newRoot.right = root;
        root.left = Aux;

        return newRoot;
    }

    private TreeNode<T> RotacionIzquierda(TreeNode<T> root) {
        if (root == null || root.right == null) return root;

        TreeNode newRoot = root.right;
        TreeNode Aux = newRoot.left;

        newRoot.left = root;
        root.right = Aux;

        return newRoot;
    }

    private int FactordeCoreccion(TreeNode<T> root) {
        if(root==null)
        {
            return 0;
        }
        return Altura(root.left) - Altura(root.right);
    }



}

