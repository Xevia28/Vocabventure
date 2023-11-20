class QueueNode {
    String data;
    QueueNode next;

    QueueNode(String data) {
        this.data = data;
        next = null;
    }
}

public class Queue {
    QueueNode first;
    QueueNode last;
    int size;

    Queue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    boolean isEmpty() {
        return last == null;
    }

    void traversal() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        QueueNode temp = first;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    void enqueue(String el) {
        QueueNode node = new QueueNode(el);
        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
    }

    String dequeue() {
        if (isEmpty()) {
            return ("Cannot dequeue on empty queue");
        }
        String data = first.data;
        if (first == last) {
            // If the queue has only one element, set both first and last to null
            first = null;
            last = null;
        } else {
            first = first.next;
        }
        size--;
        return data;
    }

    public static void main(String[] args) {
        Queue obj = new Queue();
        obj.enqueue("Jigme");
        obj.enqueue("Kinley");
        obj.enqueue("Dorji");
        obj.enqueue("Tashi");

        obj.traversal();
        System.out.println("Dequeued: " + obj.dequeue());
        obj.traversal();
    }
}
