import java.util.EmptyStackException;

public class ArrayStack implements DStack{
	double[] arrayStack = new double[10];
	int top = 0;
	
	@Override
	public boolean isEmpty(){
		return ( top == 0 );
	}
	
	@Override
	public void push(double d){
		if( top == arrayStack.length - 1 ){
			double[] newArr = new double[ arrayStack.length * 2 ];
			for(int i = 0; i < arrayStack.length; i++)
				newArr[i] = arrayStack[i];
			arrayStack = newArr;
		}
		arrayStack[top] = d;
		top++;
		System.out.println("Push succeeds. Pushed value = " + arrayStack[top] + " and top is " + top );
	}
	
	/** Pops the top of the stack; returns the value popped, 
	* NOT the new value for the top of the stack.
	*/
	@Override
	public double pop(){
		if(!isEmpty()){
			top--;
			System.out.println("Popped value = " + arrayStack[top] + " and top is now " + top);
			return arrayStack[top];
		}
		else{
			System.out.println("Stack is empty.");
			throw new EmptyStackException();
		}
	}
	
	@Override
	public double peek(){
		if(!isEmpty()){
			System.out.println("Peeked value: " + arrayStack[top - 1]);
			return arrayStack[top - 1];
		}
		else{
			System.out.println("Stack is empty; nothing to peek.");
			throw new EmptyStackException();
		}
	}
}