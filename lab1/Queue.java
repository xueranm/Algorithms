
public class Queue<E> {
	private Node<E> head;
	private Node<E> tail;
	
	public Queue(){
		
		// We want to initialize our Queue to be empty
		// (ie) set head and tail to be null
		this.head = null;
		this.tail = null;
	}
	
	public void enqueue(E newData){
	
		// Create a new node whose data is newData and whose next node is null
		// Update head and tail
		// Hint: Think about what's different for the first node added to the Queue
        Node<E> new_Node = new Node<>(newData,null);
        if (isEmpty()){
            this.head = new_Node;
        }else{
        	this.tail.setNext(new_Node);
        	
        }
        this.tail = new_Node;
	}
	
	public Node<E> dequeue(){
	
		// Return the head of the Queue
		// Update head
		// Hint: The order you implement the above 2 tasks matters, so use a temporary node
	 	//	     to hold important information
		// Hint: Return null on a empty Queue
        if (!isEmpty()){
        	Node<E> temp = this.head;
        	this.head = this.head.getNext();
        	return temp;
        }else{
        	return null;
        }
	}
	
	public boolean isEmpty(){
	
		// Check if the Queue is empty
		if (this.head == null){
			return true;
		}else{
			return false;
		}
	}
	
	public void printQueue(){

		// Loop through your queue and print each Node's data
		Node<E> current = this.head;
		while (current != null && this.tail != null){
			System.out.println(current.getData());
			current = current.getNext();
		}
	}
}
