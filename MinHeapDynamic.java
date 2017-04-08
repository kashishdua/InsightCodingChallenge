package insightCodingChallenge;

import java.util.ArrayList;
import java.util.List;

class MinHeapDynamic<Item extends Comparable<Item>>{
    private int heapSize;   //total number of elements in the heap, always <= size
    private int size;       //total number of elements, can significantly be less than array.length
    private Item[] heap;

    public MinHeapDynamic(Item[] inputArray){
        this.size = inputArray.length;
        this.heapSize = 0;
        this.heap = (Item[])new Comparable[2*size+1];
        for(int i=0;i<size;i++)
            heap[i+1] = inputArray[i];

        buildHeap();
    }

    public MinHeapDynamic(int size){
        if(size<=0)
            throw new IllegalArgumentException("Size<=0 not allowed.");

        this.size = 0;
        this.heapSize = 0;
        heap = (Item[])new Comparable[size+1];
//        buildHeap();
    }

    private void buildHeap(){
        this.heapSize = size;
        for(int i = size/2;i>=1;i--)
            heapify(i);
    }

    private void heapify(int i){
        int smallest;
        int left = leftChild(i);
        int right = rightChild(i);
        if(left<=heapSize && heap[left].compareTo(heap[i])<0)
            smallest = left;
        else
            smallest = i;

        if(right<=heapSize && heap[right].compareTo(heap[smallest])<0)
            smallest = right;

        if(smallest!=i){
            swap(i, smallest);
            heapify(smallest);
        }
    }

    public void insert(Item item){
        if(heapSize>=heap.length-1)
            resize(heapSize*2);

        heap[++heapSize] = item;
        size++;
        heapUppify(heapSize);
    }

    public Item delete(){
        if(heapSize<=0)
            throw new NullPointerException("Empty heap");
        Item item = heap[1];
        swap(heapSize, 1);
        heapSize--;
        if(heapSize<=0){
            size = 0;
            heap = null;
            return item;
        }
        heapify(1);

        if(heapSize<=heap.length/4 && heapSize>=5)
            resize(heapSize/2);
        return item;
    }

    public int size(){
        return heapSize;
    }

    public void displayHeap(){
        if(heapSize==0)
            System.out.println("Empty Heap");
        System.out.println("Heap of size: "+heapSize);
        for(int i = 1;i<=this.heapSize;i++){
            System.out.print(heap[i]+"  ");
        }
        System.out.println();
    }

    private void resize(int size){
        Item[] copy = (Item[])new Comparable[size+1];
        int min = Math.min(size, heap.length);
        for(int i=1;i<min;i++)
            copy[i] = heap[i];

        heap = copy;
    }

    private void heapUppify(int i){
        int parent = parent(i);
        if(parent>=1 && heap[parent].compareTo(heap[i])>0){
            swap(parent, i);
            heapUppify(parent);
        }
    }

    public Item min(){
        if(heap==null)
            throw new NullPointerException("Empty Heap.");

        return heap[1];
    }

    private void swap(int i, int j){
        Item temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int parent(int i){
        return i/2;
    }

    private int leftChild(int i){
        return 2*i;
    }

    private int rightChild(int i){
        return (2*i+1);
    }

    public static void main(String[] args){
        MinHeapDynamic<Integer> minheap = new MinHeapDynamic<>(4);
        minheap.displayHeap();
        minheap.insert(3);
        minheap.displayHeap();
        minheap.insert(2);
        minheap.displayHeap();
        minheap.insert(1);
        minheap.displayHeap();
        minheap.insert(4);
        minheap.displayHeap();
        minheap.insert(7);
        minheap.displayHeap();
        minheap.insert(33);
        minheap.insert(0);
        minheap.displayHeap();
        System.out.println("Deleted: "+minheap.delete());
        minheap.displayHeap();
        System.out.println("Deleted: "+minheap.delete());
        System.out.println("Deleted: "+minheap.delete());
        System.out.println("Deleted: "+minheap.delete());
        System.out.println("Deleted: "+minheap.delete());
        minheap.displayHeap();
        System.out.println("Deleted: "+minheap.delete());
        minheap.displayHeap();
        System.out.println("Deleted: "+minheap.delete());
        minheap.displayHeap();
    }

}