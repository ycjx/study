package com.yxj.dataStructure;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019-03-27 17:30
 */
public class RedBlackTree<T> {

    private Node root;


    private static boolean RED = true;

    private static boolean BLACK = false;


    public void add(T value) {
        root = addNode(root, hash(value), value);
        root.color = BLACK;
    }

    public Node addNode(Node n, int hash, T value) {
        if (n == null)
            return new Node(value, hash, RED);
        if (hash < n.hash) {
            n.left = addNode(n.left, hash, value);
        } else if (hash > n.hash) {
            n.right = addNode(n.right, hash, value);
        } else {
            n.value = value;
        }
        if (isRed(n.right) && !isRed(n.left)) n = rotateLeft(n);
        if (isRed(n.left) && isRed(n.left.left)) n = rotateLeft(n);
        if (isRed(n.left) && isRed(n.right)) flipColors(n);
        return n;
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty())
            root.color = BLACK;
    }

    public void deleteMax() {
        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    public void delete(T value) {
        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, value,hash(value));
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    private Node delete(Node n, T value,int hash) {
        // assert get(h, key) != null;

        if (hash<hash(n.hash))  {
            if (!isRed(n.left) && !isRed(n.left.left))
                n = moveRedLeft(n);
            n.left = delete(n.left, value,hash);
        }
        else {
            if (isRed(n.left))
                n = rotateRight(n);
            if (hash == hash(n.value) && (n.right == null))
                return null;
            if (!isRed(n.right) && !isRed(n.right.left))
                n = moveRedRight(n);
            if (hash(n.hash) == hash) {
                Node x = min(n.right);
                n.hash = x.hash;
                n.value = x.value;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                n.right = deleteMin(n.right);
            }
            else n.right = delete(n.right, value,hash);
        }
        return balance(n);
    }


    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else                return min(x.left);
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private boolean isEmpty() {
        if (root == null)
            return true;
        else
            return false;
    }

    private Node deleteMin(Node n) {
        if (n.left == null)
            return null;
        if (!isRed(n.left) && !isRed(n.left.left))
            n = moveRedLeft(n);
        n.left = deleteMin(n.left);
        return balance(n);
    }

    private Node balance(Node n) {
        if (isRed(n.right)) n = rotateLeft(n);
        if (isRed(n.left) && isRed(n.left.left)) n = rotateLeft(n);
        if (isRed(n.left) && isRed(n.right)) flipColors(n);
        return n;

    }

    private Node moveRedLeft(Node n) {
        flipColors(n);
        if (isRed(n.right.left)) {
            n.right = rotateRight(n.right);
            n = rotateLeft(n);
        }
        return n;
    }


    private boolean isRed(Node n) {
        return n.color;
    }

    private void flipColors(Node n) {
        n.color = !n.color;
        n.left.color = !n.left.color;
        n.right.color = !n.right.color;
    }


    private Node rotateLeft(Node h) {
        Node r = h.right;
        h.right = r.left;
        r.left = h;
        r.color = h.color;
        h.color = RED;
        return r;
    }

    private Node rotateRight(Node h) {
        Node l = h.left;
        h.left = l.right;
        l.right = h;
        l.color = h.color;
        h.color = RED;
        return l;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static final class Node<T> {

        private int hash;

        public T value;

        public Node left, right;

        public Node parent;

        public boolean color;


        Node(T value, int hash, boolean RED) {
            this.value = value;
            this.color = RED;
            this.hash = hash;
        }


    }
}
