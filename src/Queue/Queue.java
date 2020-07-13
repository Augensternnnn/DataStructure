package Queue;

import List.List;
import List.LinkedList;

public class Queue<E> {
    private List<E> list = new LinkedList<E>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * 入队
     */
    public void enQueue(E element){
        list.add(element);
    }

    /**
     * 出队
     */
    public E deQueue(){
        return list.remove(0);
    }

    /**
     * 获取队列头元素
     */
    public E front(){
        return list.get(0);
    }

    public void clear(){
        list.clear();
    }
}
