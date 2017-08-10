
public class Stack<E> {
	private Node<E> top;
	
	public Stack(){
	
		// We want to initialize our Stack to be empty
		// (ie) Set top as null
		this.top = null;
	}
	
	public void push(E newData){
	
		// We want to create a node whose data is newData and next node is top
		// Push this new node onto the stack
		// Update top
        Node<E> new_Node = new Node<>(newData,this.top);
        this.top = new_Node;
	}
	
	public Node<E> pop(){
	
		// Return the Node that currently represents the top of the stack
		// Update top
		// Hint: The order you implement the above 2 tasks matters, so use a temporary node
	 	//	     to hold important information
		// Hint: Return null on a empty stack
        if (!isEmpty()){
        	Node<E> temp = this.top;
        	this.top = this.top.getNext();
        	return temp;
        }else{
        	return null;
        }
	}
	
	public boolean isEmpty(){

		//Check if the Stack is empty
		return this.top == null;
	}
	
	public void printStack(){

		// Loop through your stack and print each Node's data
		Node<E> current = this.top;
		while (current != null){
			System.out.println(current.getData());
			current = current.getNext();
		}
	}
}