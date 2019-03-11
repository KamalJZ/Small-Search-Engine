import java.util.*;
public class MySort{
	ArrayList<SearchResult> sortThisList(Myset<SearchResult> listOfSortableEntries){
		ArrayList<SearchResult> sortedArray = new ArrayList<SearchResult>();
		int i= 0;
		MyLinkedList<SearchResult>.node<SearchResult> temp = listOfSortableEntries.first.head;
		while(temp!=null){
			sortedArray.add(temp.data); 
			i++;
			temp = temp.next;
		}
		Collections.<SearchResult>sort(sortedArray);
		return sortedArray;
	}
}