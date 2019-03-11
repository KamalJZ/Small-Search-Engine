import java.util.*;
public class MyLinkedList<X>{
	public  node<X> head;
	MyLinkedList(X a){
		head = new node<X>(a);
	}
	MyLinkedList(){
		head = null;
	}
	public class node<X>{
		X data;
		node<X> next;
		node(){
			next = null;
		}
		node(X a){
			data = a;
			next = null;
		}
	}
	node<X> temp;
	
	public Boolean IsMember(X o){
		temp = head;
		//PageEntry a =(PageEntry) o;
		while(temp != null){
			//PageEntry temp2 =(PageEntry)temp.data;
			if(temp.data==o){return true;}
			temp = temp.next;			 
		}
		return false;
	}
	public Boolean IsMember(String s){
		temp = head;
		while(temp != null){
			WordEntry temp2 =(WordEntry)temp.data;
			String com = temp2.idword;
			if(com.equals(s)){return true;}
			temp = temp.next;			 
		}
		return false;
	}
	public WordEntry getMember(String s){
		temp = head;
		while(temp != null){
			WordEntry temp2 =(WordEntry)temp.data;
			String com = temp2.idword;
			if(com.equals(s)){return temp2;}
			temp = temp.next;			 
		}
		return null;
	}
	
	public Boolean IsEmpty(){
		if(head==null){
			return true;
		}
		else return false;
	}
	public void Insert(X o){
		if(head == null){
			head = new node<X>(o);
		}
		else{
			temp = head;
			while(temp.next!=null){
				temp = temp.next;
			}
			temp.next = new node<X>(o);
		}
	}
	public void Delete(X o) throws Exception{
		if(this.IsMember(o)){
			X x = head.data;
			X y  = o;
			if(x == y){
				this.head = head.next;
			}
			else{
				temp = head;
				X temp2 = temp.next.data;
				while(temp2 != y){
					temp = temp.next;					
					temp2 =temp.next.data;
				} 
				temp.next= temp.next.next;
			}
		}
		else{
			throw new Exception("X not found");
		}
	}
	public int NumberOfMembers(){
		temp = head;
		int count =0;
		while(temp!=null){
			count++;
			temp=temp.next;
		}
		return count;
	}
	public void PrintList(){
		if(!this.IsEmpty()){
			temp = head;
			PageEntry temp2 =(PageEntry)temp.data;
			while(temp.next!=null){
				

				System.out.print(temp2.name+", ");
				temp = temp.next;
				temp2 =(PageEntry)temp.data;
			} 
			System.out.println(temp2.name);
		}
		else{
			System.out.println("No element in given set");
		}
	}	
}