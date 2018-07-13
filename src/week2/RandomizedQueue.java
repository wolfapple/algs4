import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int size;

    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == arr.length) {
            resize(2 * size);
        }
        arr[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomIdx = StdRandom.uniform(size);
        Item item = arr[randomIdx];
        if (randomIdx != size - 1) {
            arr[randomIdx] = arr[size - 1];
            arr[size - 1] = null;
        }
        size -= 1;
        if (size > 0 && size == arr.length / 4) {
            resize(arr.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return arr[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int itSize;
        private Item[] itArr;

        public RandomIterator() {
            itSize = size;
            itArr = (Item[]) new Object[itSize];
            for (int i = 0; i < itSize; i++) {
                itArr[i] = arr[i];
            }
        }

        @Override
        public boolean hasNext() {
            return itSize > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int randomIdx = StdRandom.uniform(itSize);
            Item item = itArr[randomIdx];
            if (randomIdx != itSize - 1) {
                itArr[randomIdx] = itArr[itSize - 1];
                itArr[itSize - 1] = null;
            }
            itSize -= 1;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        StdOut.println(q.dequeue());
        StdOut.println(q.sample());
        Iterator<Integer> it = q.iterator();
        while (it.hasNext()) {
            int i = it.next();
            StdOut.print(i);
        }
    }
}
