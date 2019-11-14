import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node {
        Item item;
        Node prev;
        Node next;
    }
    // is the randomized queue empty?
    public boolean isEmpty() {
        return (first == null && last == null);
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (first == null && oldLast == null) first = last;
        else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        Item item;
        int id = StdRandom.uniform(size);
        Node current = first;
        if (current == null) {
            throw new NoSuchElementException();
        }

        for (int i = 0; i < id; i++) {
            if (current.next != null)
                current = current.next;
        }

        item = current.item;
        // Remove current

        Node toDelete = current;
        if (current == first && first.next == null) {
            first = null;
            last = null;
        } else if (current == first) {
            current = current.next;
            first = first.next;
            first.prev = null;
        } else {
            current = current.prev;
            if (toDelete.next == null) {
                current.next = null;
            } else {
                current.next = toDelete.next;
                current.next.prev = toDelete.prev;
            }
        }

        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        Item item;
        int id = StdRandom.uniform(size);
        Node current = first;
        if (current == null) {
            throw new NoSuchElementException();
        }

        for (int i = 0; i < id; i++) {
            current = current.next;
        }
        item = current.item;
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        StdOut.println("RandomizedQueue is Empty: " + rq.isEmpty());
        StdOut.println("size: " + rq.size());

        rq.enqueue(283);
        StdOut.println(rq.sample());

        rq.enqueue(119);

        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());

        for (Integer i = 0; i < 5; i++){
            rq.enqueue(i);
        }

        StdOut.println("size after enqueue: " + rq.size());
        StdOut.println("Deque: " + rq.dequeue());
        StdOut.println("Sample: " + rq.sample());

        Iterator<Integer> jt = rq.iterator();
        while (jt.hasNext()) {
            Integer i = jt.next();
            StdOut.println("it: " + i);
        }

    }
}
