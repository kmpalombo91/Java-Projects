package binarysearchtree;

public class BST<E extends Comparable<E>> {

    Node<E> root;

    class Node<E extends Comparable<E>> {

        Node<E> lChild;
        Node<E> rChild;
        E e;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<E> p) {
        if (p == null) {
            return;
        }
        inOrder(p.lChild);
        System.out.println(p.e);
        inOrder(p.rChild);
    }

    public boolean insert(E e) {
        Node<E> entry = new Node<E>();
        entry.e = e;
        if (this.root == null) {
            root = entry;
        } else {
            Node<E> p1 = root;
            Node<E> p1Trail = p1;

            while (p1 != null) {
                if (e.compareTo(p1.e) == 0) {
                    return false;
                } else if (e.compareTo(p1.e) > 0) {
                    p1Trail = p1;
                    p1 = p1.rChild;
                } else {
                    p1Trail = p1;
                    p1 = p1.lChild;
                }
            }
            if (e.compareTo(p1Trail.e) < 0) {
                p1Trail.lChild = entry;
            } else {
                p1Trail.rChild = entry;
            }
        }
        return true;
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<E> p) {
        if (p == null) {
            return;
        }
        postOrder(p.lChild);
        postOrder(p.rChild);
        System.out.println(p.e);
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node<E> p) {
        if (p == null) {
            return;
        }
        System.out.println(p.e);
        preOrder(p.lChild);
        preOrder(p.rChild);
    }

    public boolean search(E e) {
        Node<E> current = root;
        while (current != null) {
            if (e.compareTo(current.e) < 0) { // go left
                current = current.lChild;
            } else if (e.compareTo(current.e) > 0) { // go right
                current = current.rChild;
            } else {
                return true; // e is found
            }
        }
        return false;
    }

    public Node<E> find(E e) {
        Node<E> current = root;
        while (current != null) {
            if (e.compareTo(current.e) < 0) { // go left
                current = current.lChild;
            } else if (e.compareTo(current.e) > 0) { // go right
                current = current.rChild;
            } else {
                return current; // e is found
            }
        }
        return null;
    }

    public int getSize() {
        return getSize(root);
    }

    private int getSize(Node<E> p) {
        if (p == null) {
            return 0;
        }
        return getSize(p.lChild) + getSize(p.rChild) + 1;
    }

    public boolean remove(E e) {
        Node<E> nodeToBeDeleted = find(e);
        if (nodeToBeDeleted == null) {
            return false;
        }
        if (getSize() == 1) {
            this.root = null;
        } else { //2 or more nodes
            Node<E> p = this.root;
            Node<E> pTrail = p;
            //1. case of node to be deleted has no children
            if (nodeToBeDeleted.lChild == null && nodeToBeDeleted.rChild == null) {
                while (p != nodeToBeDeleted) {
                    pTrail = p;
                    if (nodeToBeDeleted.e.compareTo(p.e) > 0) {
                        p = p.rChild;
                    } else {
                        p = p.lChild;
                    }
                }
                //here the pTrail points to the parent of the node to be deleted
                //set parent's child == null
                if (nodeToBeDeleted.e.compareTo(pTrail.e) > 0) {
                    pTrail.rChild = null;
                } else {
                    pTrail.lChild = null;
                }
            } //2. case of node to be deleted has ONE child
            // --> if has 1 left child and no right child
            else if (nodeToBeDeleted.lChild != null && nodeToBeDeleted.rChild == null) {
                while (p != nodeToBeDeleted) {
                    pTrail = p;
                    if (nodeToBeDeleted.e.compareTo(p.e) > 0) {
                        p = p.rChild;
                    } else {
                        p = p.lChild;
                    }
                }
                if (nodeToBeDeleted.e.compareTo(pTrail.e) > 0) {
                    pTrail.rChild = nodeToBeDeleted.lChild;
                } else {
                    pTrail.lChild = nodeToBeDeleted.lChild;
                }
            } // --> if has 1 right child and no left child
            else if (nodeToBeDeleted.rChild != null && nodeToBeDeleted.lChild == null) {
                while (p != nodeToBeDeleted) {
                    pTrail = p;
                    if (nodeToBeDeleted.e.compareTo(p.e) > 0) {
                        p = p.rChild;
                    } else {
                        p = p.lChild;
                    }
                }
                if (nodeToBeDeleted.e.compareTo(pTrail.e) > 0) {
                    pTrail.rChild = nodeToBeDeleted.rChild;
                } else {
                    pTrail.lChild = nodeToBeDeleted.rChild;
                }
            } //3. case of node to be deleted has TWO child
            else {
                p = nodeToBeDeleted;
                pTrail = p;
                //take a quick left
                p = p.lChild;
                pTrail = p;
                //keep going right until hit null
                while (p.rChild != null) {
                    pTrail = p;
                    p = p.rChild;
                }
                //if after that quick left, there is no right
                if (p == pTrail) {
                    nodeToBeDeleted.lChild = p.lChild;
                } else {
                    pTrail.rChild = p.lChild;
                }
                nodeToBeDeleted.e = p.e;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        /*tree.insert(100);
        tree.insert(70);
        tree.insert(120);
        tree.insert(60);
        tree.insert(40);
        tree.insert(50);
        tree.insert(35);

        tree.inOrder();
        System.out.println("--------------");
        tree.remove(35);
        tree.inOrder();
        System.out.println("--------------");
        tree.remove(120);
        tree.inOrder();*/
        
        tree.insert(100);
        tree.insert(70);
        tree.insert(150);
        tree.insert(80);
        tree.insert(60);
        tree.insert(65);
        tree.insert(62);

        tree.remove(70);
        tree.remove(150);
        tree.remove(60);
        tree.inOrder();
    }
}
