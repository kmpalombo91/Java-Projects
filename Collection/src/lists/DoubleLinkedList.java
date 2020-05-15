package lists;

public class DoubleLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;

    class Node<E> {

        E e;
        Node<E> left;
        Node<E> right;
    }

    public void print() {
        Node<E> temp = head;
        while (temp != null) {
            System.out.print(temp.e + " ");
            temp = temp.right;
        }
        System.out.println();
    }

    public boolean add(int position, E e) {
        if (position < 0 || position > size()) {
            return false;
        }
        Node<E> entry = new Node<E>();
        entry.e = e;
        if (head == null) { // list is empty
            head = tail = entry;
        } else if (position == 0) { // deals with entry at beginning of list
            entry.right = head;
            head.left = entry;
            head = entry;
        } else if (position == size()) { // deals with entry at end of list
            entry.left = tail;
            tail.right = entry;
            tail = entry;
        } else { // deals with entry in the middle of the list
            Node<E> temp = head;
            for (int i = 0; i < position - 1; i++) {
                temp = temp.right;
            }
            entry.left = temp;
            entry.right = temp.right;
            temp.right.left = entry;
            temp.right = entry;
        }
        return true;
    }

    public boolean add(E e) {
        return add(size(), e);
    }
    
    public boolean remove(int position) {
        if (position < 0 || position > size() - 1) {
            return false;
        }
        if (size() == 0) {
            return false;
        } else if (size() == 1) {
            head = tail = null;
        } else if (position == 0) {
            head = head.right;
            head.left = null;   
        } else if (position == size() - 1) {
            tail = tail.left;
            tail.left = null;
        } else { // removal in middle of list
            Node<E> temp = head;
            for (int i = 0; i < position; i++) {
                temp = temp.right;
            }
            temp.left.right = temp.right;
            temp.right.left = temp.left;
        }
        return true;
    }
    
    public boolean remove(E e) {
       return false; 
    }

    public int size() {
        int count = 0;
        Node<E> temp = head;
        while (temp != null) {
            count++;
            temp = temp.right;
        }
        return count;
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.add(0, 10);
        list.add(1, 20);
        list.add(2, 30);
        list.add(2, 25);
        list.add(0, 5);
        list.print();
        System.out.println("Size: " + list.size());
        list.add(35);
        list.print();
        System.out.println("Size: " + list.size());
        list.remove(2);
        list.print();
        System.out.println("Size: " + list.size());
        list.remove(0);
        list.print();
        System.out.println("Size: " + list.size());
        list.remove(3);
        list.print();
        System.out.println("Size: " + list.size());
    }
}
