import java.util.*;
public class MyHashTable{
	Bucket[] hashtable = new Bucket[1000];
	public int getHashIndex(String str){
		int result = 0;
		int x;
		for(int i=0;i<str.length();i++){
			x =(int)str.charAt(i);
			result = result + x*x;
		}
		return result%1000;
	}
	public void addPositionsForWord(WordEntry w){
		int ind = getHashIndex(w.idword);
		if(hashtable[ind]==null){
			hashtable[ind]=new Bucket();
			hashtable[ind].list.Insert(w);
		}
		else if(!hashtable[ind].list.IsMember(w.idword)){
			hashtable[ind].list.Insert(w);
		}
		else{
			WordEntry alexe = hashtable[ind].list.getMember(w.idword);
			alexe.addPositions(w.wordpositions);
		}

	}
}
class Bucket{
	MyLinkedList<WordEntry> list = new MyLinkedList<WordEntry>();
	

}