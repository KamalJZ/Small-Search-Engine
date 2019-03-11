import java.util.*;
public class PageIndex{
	MyLinkedList<WordEntry> allWords = new MyLinkedList<WordEntry>();
	void addPositionForWord(String str, Position p){
		if(allWords.IsMember(str)){
			WordEntry w = allWords.getMember(str);
			w.addPosition(p);
			//System.out.println("Inside If||"+ " word: "+str+" page: "+p.pgentry.name+ " index: "+p.wordposition+ " termFq: "+w.termfrequency);
			
		}
		else{
			WordEntry wd = new WordEntry(str);
			wd.addPosition(p);
			allWords.Insert(wd);

			//System.out.println("Inside else||"+ " word: "+str+" page: "+p.pgentry.name+ " index: "+p.wordposition);
		}
	}
	MyLinkedList<WordEntry>getWordEntries(){
		return allWords;
	}
} 