import java.lang.*;
public class MaxHeap<E extends Comparable> {
    private E[] myArray;
    private int maxSize;
    private int length;

    
    public MaxHeap(int s){
    	// Build the constructor
        myArray = (E[]) new Comparable[s+1];
        myArray[0] = null;
        maxSize=s;
        length=0;
    }

	// helper functions
    public E[] getArray(){
        return myArray;
    }

    public void setArray(E[] newArray){
    	if (newArray.length > maxSize+1){
    		return;
    	}
        myArray = newArray;
        length = newArray.length-1;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public void setMaxSize(int ms){
        maxSize = ms;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int l){
        length = l;
    }

    // Other Methods
    public void insert(E data){
    
    	// Insert an element into your heap.
    	
    	// When adding a node to your heap, remember that for every node n, 
    	// the value in n is less than or equal to the values of its children, 
    	// but your heap must also maintain the correct shape.
		// (ie there is at most one node with one child, and that child is the left child.)
		// (Additionally, that child is farthest left possible.)
        length = length +1;
        myArray[length] = data;
        int i = length;
        while((i>1)&&(myArray[i].compareTo(myArray[i/2])>0)){
            E temp = myArray[i];
            myArray[i] = myArray[i/2];
            myArray[i/2] = temp;
            i = i/2;
        }  
    }

    public Comparable<E> maximum(){
        // return the max value in the heap
        if (length>0){
            return myArray[1];
        }else{
            return myArray[0];
        }

    }

    public Comparable<E> extractMax(){
        // remove and return the max value in the heap
        if (length>0){
            E max = myArray[1];
            myArray[1] = myArray[length];
            length = length-1;
            heapify(1);
            return max;
        }else{
            return myArray[0];
        }
    }
    
    public void heapify(int i){
    	// helper function for reshaping the array
        int largest;
        if((2*i<=length)&&(myArray[2*i].compareTo(myArray[i])>0)){
            largest = 2*i;
        }else{
            largest = i;
        }
        if(((2*i+1)<=length)&&(myArray[2*i+1].compareTo(myArray[largest])>0)){
            largest = 2*i+1;
        }
        if(largest!=i){
            E temp = myArray[i];
            myArray[i] = myArray[largest];
            myArray[largest] = temp;
            heapify(largest);
        }
    }
    
    public void buildHeap(E[] newArr){
		// used for the extra credit
        setArray(newArr);
        for (int i = getLength()/2;i>0;i--){
            heapify(i);
        }
	}
}
