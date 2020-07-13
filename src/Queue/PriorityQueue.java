package Queue;

import Heap.BinaryHeap;

import java.util.Comparator;

public class PriorityQueue<E> {
    private BinaryHeap<E> heap;

    public PriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public PriorityQueue() {
        this(null);
    }

    public int size(){
        return heap.size();
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }

    public void clear(){
        heap.clear();
    }

    /**
     * 入队
     */
    public void enQueue(E element){
        heap.add(element);
    }

    /**
     * 优先级最高的元素出队
     */
    public E deQueue(){
        return heap.remove();
    }

    /**
     * 获取队列头元素
     */
    public E front(){
        return heap.get();
    }


}
