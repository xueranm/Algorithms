public class pQueue<E extends Comparable> {
    private MaxHeap myHeap;

    public pQueue (int s) {
        // Build the Constructor
        myHeap = new MaxHeap(s);
    }

    public void insert(E data){
        myHeap.insert(data);
    }

    public Comparable<E> maximum(){
        return myHeap.maximum();
    }

    public Comparable<E> extractMax(){
        return myHeap.extractMax();
    }

    public boolean isEmpty(){
        // Return true when the priority queue is empty
        // Hint: Do the actual printing in your lab3.java
        if (myHeap.getLength()==0){
            return true;
        }else{
            return false;
        }
    }

	public void build(E[] arr){
    	// used for the extra credit
        myHeap.buildHeap(arr);
    }
    
    public void print(){
        // print out ”Current Queue: ” 
        // followed by each element separated by a comma. 
        // Do not add spaces between your elements.
        System.out.print("Current Queue: ");
        if (isEmpty()){
            System.out.print("null");
        }else{
            System.out.print(myHeap.getArray()[1]);
            for (int i = 2; i <= myHeap.getLength();i++){
                System.out.print(","+myHeap.getArray()[i]);
            }
        }
        System.out.print("\n");
    }
}
