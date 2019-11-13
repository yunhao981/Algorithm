import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node previous;
        Node next;
    }

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (first == null && last == null);
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.previous = null;
        first.next = oldFirst;
        if (oldFirst == null && last == null) last = first;
        else {
            oldFirst.previous = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (first == null && oldLast == null) first = last;
        else {
            oldLast.next = last;
            last.previous = oldLast;
        }
        size++;
    }

    public Item removeFirst() {
        if (first == null)
            throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first == null) last = null;
        else {
            first.previous = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (last == null)
            throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        if (last == null) first = null;
        else last.next = null;
        if (isEmpty()) first = null;
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
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

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        StdOut.println("DequeIsEmpty: " + deque.isEmpty());
        StdOut.println("Deque's Size: " + deque.size());

        Integer a = 1;
        deque.addFirst(1);
        deque.addFirst(2);
        deque.removeFirst();

        deque.addLast(1);
        deque.addLast(2);
        deque.removeFirst();

        deque.addFirst(1);
        deque.removeFirst();

        deque.addLast(1);
        deque.addFirst(a);
        a++;
        deque.addLast(a);
        for (Integer i = 1; i < 10; i++) {
            deque.addFirst(i);
        }

        Iterator<Integer> it = deque.iterator();
        while (it.hasNext()) {
            Integer i = it.next();
            StdOut.println("it: " + i);
        }

        StdOut.println("Size: " + deque.size());
        StdOut.println("Remove First: " + deque.removeFirst());
        StdOut.println("Remove Last: " + deque.removeLast());
        StdOut.println("Size: " + deque.size());
    }

}