import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

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
        return (size==0 && first == null && last == null);
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size();
    }

    // add the item
    public void enqueue(Item item) {
        if(item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if(isEmpty()) first = last;
        else {
            oldLast.next = last;
            last.prev = oldLast;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        Item item;
        int id = StdRandom.uniform(size);
        Node current = first;
        for(int i=0; i<size; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        item = current.item;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        Item item;
        int id = StdRandom.uniform(size);
        Node current = first;
        for(int i=0; i<size; i++) {
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

        @Override
        public boolean hasNext() {

            return false;
        }
    }
    // unit testing (required)
    public static void main(String[] args)
}
