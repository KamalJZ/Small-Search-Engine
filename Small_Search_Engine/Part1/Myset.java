import java.util.*;
public class Myset<X>{
	public  MyLinkedList<X> first;
	Myset(X o){ 
		 first = new MyLinkedList<X>(o);
	}					
	Myset(){ 
		 first = new MyLinkedList<X>();
	}

	public Boolean IsEmpty(){
		if(this.first.IsEmpty()){
			return true;
		}
		else {return false;}
	}
	public Boolean IsMember(X o){
		if(this.first.IsMember(o)){
			return true;
		}
		else{return false;}
	}
	public Boolean IsMember(String o){
		if(this.first.IsMember(o)){
			return true;
		}
		else{return false;}
	}
	
	public void Insert(X o){
		if(!this.first.IsMember(o)){
			this.first.Insert(o);
		}
	}
	public void Delete(X o){
		try{
			this.first.Delete(o);
		}
		catch(Exception e){

		}
	}
	public Myset Union(Myset<X> a){
		Myset<X> union = new Myset<X>();
		MyLinkedList<X>.node<X> temp = this.first.head;
		for(int i=0;i<this.first.NumberOfMembers();i++){
			union.Insert(temp.data);
			temp = temp.next;
		}
		MyLinkedList<X> extra = a.first;
		MyLinkedList<X>.node<X> temp1 = extra.head;
		for(int i=0;i<extra.NumberOfMembers();i++){
			if(!union.first.IsMember(temp1.data)){
				union.Insert(temp1.data);
			}
			temp1 = temp1.next;			
		}
		return union;
	}
	public Myset Intersection(Myset<X> a){
		Myset<X> intersection = new Myset<X>();
		MyLinkedList<X>.node<X> temp = this.first.head;
		for(int i=0;i<this.first.NumberOfMembers();i++){
			if(this.IsMember(temp.data)&&a.IsMember(temp.data)){
				intersection.Insert(temp.data);
			}
			temp=temp.next;
		}
		return intersection;

	}
}
