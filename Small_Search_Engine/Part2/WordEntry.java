import java.util.*;
public class WordEntry{
	MyLinkedList<Position> wordpositions = new MyLinkedList<Position>();
	AVLtree PosTree = new AVLtree();
	float termfrequency=0;
	String idword="";
	WordEntry(String word){
		idword = word;
	}
	void addPosition(Position position){
		wordpositions.Insert(position);
		PosTree.add(position);
		termfrequency++;
	}
	void addPositions(MyLinkedList<Position> positions){
		MyLinkedList<Position>.node<Position> temp = positions.head;
		while(temp.next!=null){
			wordpositions.Insert(temp.data);
			PosTree.add(temp.data);
			termfrequency++;
			temp = temp.next;
		}
		wordpositions.Insert(temp.data);
		PosTree.add(temp.data);
		termfrequency++;
			
	}
	MyLinkedList<Position>getAllPositionsForThisWord(){

		return wordpositions;
	}
	float getTermFrequency(String word){
		int total = wordpositions.head.data.getPageEntry().wordCount;
		//System.out.println(total);
		termfrequency = wordpositions.NumberOfMembers();
		termfrequency = (float)termfrequency/(total);

		return termfrequency;
	}
	float getInvDocFq(String word, InvertedPageIndex pg){
		return pg.InverseDocFq(word);
	}
	WordEntry Copy(){
		WordEntry copy = new WordEntry(this.idword);
		copy.addPositions(this.wordpositions);
		return copy; 
	}
} 