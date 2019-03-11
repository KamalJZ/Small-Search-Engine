import java.util.*;
public class Position{
	int wordposition;
	PageEntry pgentry;
	public Position(PageEntry p, int wordIndex){
		wordposition = wordIndex;
		pgentry = p;
	}
	public  int getWordIndex(){
		return wordposition;
	}
	public PageEntry getPageEntry(){
		return pgentry;
	}
}