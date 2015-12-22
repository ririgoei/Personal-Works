import java.util.EmptyStackException;

public class ListStack implements DStack{

private Node head;
private int size;

//	public static void main(String[] args){
//		DStack s = new ListStack();
//		s.push(70.2);
//		s.push(12.3);
//                s.push(45.5);
//                s.push(50.3);
//		s.pop();
//		s.peek();
//                s.pop();
//                s.peek();
//                s.pop();
//                s.peek();
//                s.pop();
//                s.peek();
//	}


private class Node{
    double d;
    Node next;
	int size;

    public Node(){
        head = null;
        next = null;
		size = 0;
    }

    public Node(double data){
        d = data;
    }
}

@Override
public boolean isEmpty() {
    return ( head == null );
}

@Override
public void push(double d) {
        Node newHead = new Node(d);
        newHead.next = head;
        head = newHead;
        size++;
        System.out.println("Push succeeds. Value pushed = " + head.d + " size: " + size );
}

@Override
public double pop() {
    if(!isEmpty()){
        double d = head.d;
        head = head.next;
        size--;
        System.out.println( "Pop succeeds. Value popped = " + d + " and size= " + size);
        return d;
    }
    else{
        System.out.println("Stack is empty.");
        throw new EmptyStackException();
    }
}

@Override
public double peek() {
    if(!isEmpty()){
        System.out.println("Peeked value = " + head.d );
        return head.d;
    }
    else{
        System.out.println("Stack is empty. Nothing to peek.");
        throw new EmptyStackException();
    }
}
}