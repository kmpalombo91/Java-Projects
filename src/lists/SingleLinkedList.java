package lists;

public class SingleLinkedList<E> {

    private Node<E> head;

    class Node<E> {

        E e;
        Node<E> link;
    }

    public void print() {
        Node<E> temp1 = head;
        while (temp1 != null) {
            System.out.print(temp1.e + " ");
            temp1 = temp1.link;
        }
        System.out.println();
    }

    public boolean add(E e) {
        Node<E> entry = new Node<E>();
        entry.e = e;
        if (head == null) {
            head = entry;
        } else {
            Node<E> temp1 = head;
            Node<E> temp2 = head;
            while (temp1 != null) {
                temp2 = temp1;
                temp1 = temp1.link;
            }
            temp2.link = entry;
        }
        return true;
    }

    public boolean remove() {
        if (head == null) {
            return false;
        } else if (head.link == null) {
            head = null;
        } else {
            Node<E> temp1 = head;
            Node<E> temp2 = head;
            while (temp1.link != null) {
                temp2 = temp1;
                temp1 = temp1.link;
            }
            temp2.link = null;
        }
        return true;
    }

    /**
     * Removes the entry at position
     *
     * @param position the position of removal
     * @return true if the entry is removed false otherwise (invalid position)
     */
    public boolean remove(int position) {
        if (head == null) {
            return false;
        } else if (position == 0) {
            head = head.link;
        } else {
            Node<E> temp = head;
            for (int i = 0; i < position - 1; i++) {
                temp = temp.link;
            }
            temp.link = temp.link.link;
        }
        return true;
    }

    /**
     * Removes the entry that matches the parameter value e
     *
     * @param e entry to be removed
     * @return true if entry is removed, false otherwise
     */
    public boolean remove(E e) {
        if (head == null) {
            return false;
        } else if (head.e == e) {
            head = head.link;
        } else {
            Node<E> temp = head;
            while (temp.link != null) {
                if (temp.link.e == e) {
                    temp.link = temp.link.link;
                } else {
                    temp = temp.link;
                }
            }
        }
        return true;
    }

    public boolean add(int position, E e) {
        if (position < 0 || position > size()) {
            return false;
        }
        Node<E> entry = new Node<E>();
        entry.e = e;
        if (size() == 0) {
            head = entry;
        } else if (position == 0) { // add in the front with 1 or more nodes in the list
            entry.link = head;
            head = entry;
        } else { // add in the middle
            Node<E> temp1 = head;
            for (int i = 0; i < position - 1; i++) {
                temp1 = temp1.link;
            }
            entry.link = temp1.link;
            temp1.link = entry;
        }
        return true;
    }

    public int size() {
        int count = 1;
        Node<E> temp = head;
        while (temp.link != null) {
            temp = temp.link;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> l1 = new SingleLinkedList<>();
        l1.add(10);
        l1.add(20);
        l1.add(30);
        l1.add(40);
        l1.print();
        l1.remove(2);
        l1.print();
        l1.remove(2);
        l1.print();
        l1.remove(0);
        l1.print();
    }
}
