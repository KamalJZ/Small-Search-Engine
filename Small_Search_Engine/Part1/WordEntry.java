import java.util.*;
public class WordEntry{
	MyLinkedList<Position> wordpositions = new MyLinkedList<Position>();
	float termfrequency=0;
	String idword="";
	WordEntry(String word){
		idword = word;
	}
	void addPosition(Position position){
		wordpositions.Insert(position);
		termfrequency++;
	}
	void addPositions(MyLinkedList<Position> positions){
		MyLinkedList<Position>.node<Position> temp = positions.head;
		while(temp.next!=null){
			wordpositions.Insert(temp.data);
			termfrequency++;
			temp = temp.next;
		}
		wordpositions.Insert(temp.data);
		termfrequency++;
			
	}
	MyLinkedList<Position>getAllPositionsForThisWord(){

		return wordpositions;
	}
	float getTermFrequency(String word){

		return termfrequency;
	}
} 