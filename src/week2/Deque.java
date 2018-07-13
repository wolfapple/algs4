import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;

    public Deque() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (first == null) {
            first = new Node(item);
            last = first;
        } else {
            Node old = first;
            first = new Node(item);
            old.prev = first;
            first.next = old;
        }
        size += 1;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (last == null) {
            last = new Node(item);
            first = last;
        } else {
            Node old = last;
            last = new Node(item);
            old.next = last;
            last.prev = old;
        }
        size += 1;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        size -= 1;
        if (isEmpty()) {
            first = last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        size -= 1;
        if (isEmpty()) {
            first = last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> q = new Deque<>();
        q.addFirst(2);
        q.addFirst(1);
        q.addFirst(0);
        q.addLast(3);
        q.addLast(4);
        q.addLast(5);
        int rf = q.removeFirst();
        int rl = q.removeLast();
        StdOut.println(rf);
        StdOut.println(rl);
        Iterator<Integer> it = q.iterator();
        while (it.hasNext()) {
            int i = it.next();
            StdOut.print(i);
        }
    }
}
