package lists;

/**
 * 0 nodes, the head is null 1 node, the head points to the node and its left
 * and right links point to the head 2 or more nodes: the right link of the last
 * node points to the head AND the left link of the head points to the head
 *
 * @author Kamryn
 * @param <E>
 */
public class DoubleLinkedListCircular<E> {

    Node<E> head;

    class Node<E> {

        E e;
        Node<E> left;
        Node<E> right;
    }

    public boolean add(E e) { // empty
        Node<E> temp = new Node<E>();
        temp.e = e;
        if (head == null) {
            head = temp;
            head.left = head.right = temp;
        } else if (size() == 1) { // 1 node
            temp.right = head;
            temp.left = head;
            head.right = temp;
            head.left = temp;
        } else { // 2 or more nodes
            head.left.right = temp;
            temp.right = head;
            head.left = temp;
        }
        return true;
    }

    public boolean add(int position, E e) {
        Node<E> temp = new Node<E>();
        temp.e = e;
        if (position < 0 || position > size()) {
            return false;
        }
        if (head == null) {
            head = temp;
            temp.right = head;
        } else if (position == 0) {
            temp.right = head;
            head.right = temp;
            head = temp;
        } else {
            Node<E> newNode = head;
            for (int i = 0; i < position - 1; i++) {
                newNode = newNode.right;
            }
            temp.left = newNode;
            temp.right = newNode.right;
            newNode.right.left = temp;
            newNode.right = temp;
        }
        return true;
    }

    public boolean remove(int pos) {
        if (pos == 0) {
            Node<E> temp = head;
            while (temp.right != head) {
                temp = temp.right;
            }
            temp.right = head.right;
            head = head.right;
        } else if (pos == size() - 1) {
            Node<E> temp = head;
            Node<E> newNode = new Node<E>();
            while (temp.right != head) {
                newNode = temp;
                temp = temp.right;
            }
            newNode.right = head;
        } else {
            Node<E> current = head;
            for (int i = 0; i < pos; i++) {
                current = current.right;
            }
            Node<E> temp = current;
            Node<E> prev = head;
            for (int i = 0; i < pos - 1; i++) {
                prev = prev.right;
            }
            Node<E> newNode = prev;
            newNode.right = temp.right;
        }
        return true;
    }

    public boolean remove(E e) {
        Node<E> temp = head;
        int index = 0;
        if (temp.right == head) {
            return false;
        }
        do {
            if (temp.e.equals(e)) {
                remove(index);
            }
            temp = temp.right;
            index++;
        } while (temp != head);
        return true;
    }

    public void print() {
        Node<E> temp = head;
        for (int i = 0; i < size(); i++) {
            System.out.print(temp.e + " ");
            temp = temp.right;
        }
        System.out.println();
    }

    public int size() {
        int count = 1;
        Node<E> temp = head;
        if (head == null) {
            return 0;
        }
        while (temp.right != head) {
            count++;
            temp = temp.right;
        }
        return count;
    }

    public static void main(String[] args) {
        DoubleLinkedListCircular list = new DoubleLinkedListCircular();

        list.add(0, 10);
        list.add(0, 5);
        list.add(2, 15);
        list.add(2, 12);
        list.print();
        System.out.println("Size: " + list.size());
        System.out.println("---------------------");
        list.remove(0);
        list.remove(2);
        list.remove(1);
        list.print();
        System.out.println("Size: " + list.size());
    }
}
