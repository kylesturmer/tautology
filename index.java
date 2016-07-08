
import java.util.*;

public class tautology
{
	private static String infix = "";
    private static LLStackADT S = new LLStackADT();
    
    public static void main(String[] args)
    {
    	Scanner scan = new Scanner(System.in);
    	System.out.println("Enter expression: ");
    	infix = scan.nextLine().replace(" ", "").replace("<-->", "$").replace("-->", "@");	
    	tautology(postfix(infix));
    }
	
    public static void print(LLStackADT p)
    {
    	System.out.print("Stack = ");
       while(!p.empty())
       {
          System.out.print(p.peek());
          p.pop();
       }
       System.out.println("");

    }

    public static String postfix(String node)  
    {
        String output="";
        for(int i=0;i<node.length();i++)  
        {
            char c=node.charAt(i);   
            if(c==('@')||c==('$')||c==('v')||c==('&')||c==('~'))
                {while(!S.empty() && priority(S.peek())>= priority(c))
                    output+=S.pop();
                S.push(c);
                }
            else if(c=='(')
            {
                S.push(c);
            }  
            else if(c==')') 
            {  
                while(S.peek() != '(')  
                       output+=S.pop(); 
                S.pop();   
            }   
            else  
                output+=c;   
        }    
        while(!S.empty()){   
            output+=S.pop();}    
        return output;
    }

    public static int priority(char node)
    {
        if(node =='$')
        	return 1;
        if(node == '@')
        	return 2;
        if(node =='v')
        	return 3;
        if(node =='&')
        	return 4;       
        if(node =='~')
        	return 5;
        if(node =='(')
        	return 0;
    
        return 7;
    }

    static void tautology (String x)
    {
    	if (evaluate('T', 'T', 'T', x) == 'F' || 
    		evaluate('T', 'T', 'F', x) == 'F' ||
    		evaluate('T', 'F', 'F', x) == 'F' || 
    		evaluate('T', 'F', 'T', x) == 'F' || 
    		evaluate('F', 'T', 'T', x) == 'F' || 
    		evaluate('F', 'T', 'F', x) == 'F' || 
    		evaluate('F', 'F', 'T', x) == 'F' ||
    		evaluate('F', 'F', 'F', x) == 'F')
		System.out.println("Not a tautology!");
		else
			System.out.println("Is a tautology");
    }
    
    	public static char evaluate(char pv, char qv, char rv, String x){
        String s= "";
        for(int i = 0; i< x.length(); i++)
        {
            char c=x.charAt(i);
            char z = 'T';
            char y = 'F';
            if (c=='P'){
            	S.push(pv);
            	s+=pv; } 
            if (c=='Q'){
            	S.push(qv);
            	s+=qv; } 
            if (c=='R'){
            	S.push(rv);
            	s+=rv; }         
            else if(c=='@')
            {
               if(S.peek() == 'T' && S.peek2() == 'T'){
                   S.pop();
                   S.pop();
                   S.push(z);}
               else if(S.peek() == 'T' && S.peek2() =='F'){
                   S.pop();
                   S.pop();
                   S.push(z);}
               else if(S.peek() == 'F' && S.peek2() =='T'){
                   S.pop();
                   S.pop();
                   S.push(y);}
               else if (S.peek() == 'F' && S.peek2() == 'F'){
                   S.pop();
                   S.pop();
                   S.push(z);}
          
               s+=c;
            }
            else if(c=='$')
            {
               if(S.peek() == 'T' && S.peek2() == 'T'){
                   S.pop();
                   S.pop();
                   S.push(z);}
               else if(S.peek() == 'T' && S.peek2() == 'F'){
                   S.pop();
                   S.pop();
                   S.push(y);}
               else if(S.peek() == 'F' && S.peek2() == 'T'){
                   S.pop();
                   S.pop();
                   S.push(y);}
               else if (S.peek() == 'F' && S.peek2() == 'F'){
                   S.pop();
                   S.pop();
                   S.push(z);}
     
               s+=c;
            }
            else if(c=='&')
            {   
               if(S.peek() == 'T' && S.peek2() == 'T'){
                   S.pop();
                   S.pop();
                   S.push(z);}
               else if(S.peek() == 'T' && S.peek2() == 'F'){
                   S.pop();
                   S.pop();
                   S.push(y);}
               else if(S.peek() == 'F' && S.peek2() == 'T'){
                   S.pop();
                   S.pop();
                   S.push(y);}
               else if (S.peek() == 'F' && S.peek2() == 'F'){
                   S.pop();
                   S.pop();
                   S.push(y);}
     
               s+=c;
            }
            else if(c=='v')
            {    
               if(S.peek() == 'T' && S.peek2() == 'T'){
            	   S.pop();
            	   S.pop();
                   S.push(z);}
               else if(S.peek() == 'T' && S.peek2() == 'F'){
                   S.pop();
                   S.pop();
                   S.push(z);}
               else if(S.peek() == 'F' && S.peek2() == 'T'){
                   S.pop();
                   S.pop();
                   S.push(z);}
               else if (S.peek() == 'F' && S.peek2() == 'F'){
                   S.pop();
                   S.pop();
                   S.push(y);}
          
               s+=c;
            }
             else if(c=='~')
            {
                if(S.peek() == 'T'){
                    S.pop();
                    S.push(y);}
                else{
                    S.pop();
                    S.push(z);}            
                s+=c;
            }
        }
        return S.pop();
    }



public interface LLStack {
	
   public void push (char item);
   public char pop();
   public int size();
   public boolean empty();
   public char peek();
   public char peek2();
}

static class Node {

   public char data;
   public Node next;

   public Node () {
     this.next = null;
     this.data = ' ';
   }

  public Node (char d) {
     data = d;   
   }

  public Node (char d, Node n) {
     data = d;
     next = n;   
   }

   public void setData (char newData) {
     data = newData;   
}

   public void setNext (Node newNext) {
     next = newNext;   
   }

   public Object getData () {
     return data;   
   }

   public Node getNext () {
      return next;   
   }

   public void displayNode () {
      System.out.print (data);   
   }
}

static class LLStackADT implements LLStack { 

   private Node top = null;
   private int size;

   public LLStackADT () {
     top = null;  
     size = 0;
   }

   public boolean empty () {
     return (top == null);   
   } 
  
   public int size () {
      int count = 0;
      Node current = top; 
      while (current != null) {
         count++;
         current = current.getNext();
      }
      return count;
   }     
   
public void push(char e){
        Node tmp = new Node(e);
        tmp.next = top;
        top = tmp;
        S.size++;
}
public char pop(){
    char e = top.data;
    top = top.next;
    S.size--;
    return e;
}
public char peek(){ 
    char e = top.data;
    return e;
}
public char peek2(){
	char m = S.pop();
	char n = S.peek();
	S.push(m);
	return n;	
}
}
}