class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head, tail;
    int size;

    LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    boolean isEmpty() {
        return head == null;
    }

    void traversal() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    void insertAtEnd(String el) {
        Node node = new Node(el);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    int getSize() {
        return size;
    }

    void updateEl(int index, String el) {
        Node temp = head;
        int i = 0;
        while (temp != null) {
            if (i == index) {
                temp.data = el;
            }
            i++;
            temp = temp.next;
        }
    }

    void removeEl(int index) {
        Node current = head;
        Node previous = null;
        int i = 0;

        if (index == 0) {
            Node ind1 = current.next;
            head = ind1;
            return;
        }

        while (current != null) {
            if (i == index) {
                previous.next = current.next;
                current.next = null;
            } else {
                previous = current;
                current = previous.next;
            }
            i++;
        }
        size--;
    }

    void removeByValue(String value) {
        Node current = head;
        Node previous = null;

        if (current != null && current.data.equals(value)) {
            head = current.next;
            return;
        }

        while (current != null && !current.data.equals(value)) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return;
        }

        previous.next = current.next;
        size--;
    }

}

public class LinkedLists {

    public static void main(String[] args) {
        LinkedList obj = new LinkedList();
        obj.insertAtEnd("Jigme");
        obj.insertAtEnd("Namgyal");
        obj.insertAtEnd("Dorji");
        obj.insertAtEnd("Phuntsho");
        obj.insertAtEnd("Wangdi");
        obj.insertAtEnd("John Doe");
        System.out.println("Size: " + obj.getSize());
        obj.traversal();
        System.out.println();
        obj.updateEl(0, "Phillips");
        obj.removeByValue("Dorji");
        obj.traversal();
        System.out.println();
        System.out.println("Size: " + obj.getSize());

    }
}