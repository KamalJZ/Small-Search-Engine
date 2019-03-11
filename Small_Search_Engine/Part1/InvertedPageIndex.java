 import java.util.*;
public class InvertedPageIndex{
	Myset<PageEntry> InvPgIdx = new Myset<PageEntry>();
	MyHashTable Table = new MyHashTable();
	void addPage(PageEntry p){
		if(InvPgIdx.IsMember(p));
		else{
		InvPgIdx.Insert(p);
		MyLinkedList<WordEntry> templist = p.p.getWordEntries();
		MyLinkedList<WordEntry>.node<WordEntry> tempnode = templist.head;
		while(tempnode!=null){
			Table.addPositionsForWord(tempnode.data);
			tempnode = tempnode.next;
		}
	}

	}
	Myset<PageEntry>getPagesWhichContainWord(String str){
		
			Myset<PageEntry>pg = new Myset<PageEntry>();
			/*MyLinkedList<PageEntry>.node<PageEntry> temp = InvPgIdx.first.head;
			
			while(temp!=null){
				PageIndex x = temp.data.getpageIndex();
				if(x.allWords.IsMember(str)){
					pg.Insert(temp.data);
				}
			}*/
			int index = Table.getHashIndex(str);
			if(Table.hashtable[index]!=null){
				if(Table.hashtable[index].list.IsMember(str)){
					WordEntry inwrd = Table.hashtable[index].list.getMember(str);
					MyLinkedList<Position> templist = inwrd.getAllPositionsForThisWord();
					MyLinkedList<Position>.node<Position> tempnode = templist.head;
					while(tempnode!=null){
						pg.Insert(tempnode.data.getPageEntry());
						tempnode = tempnode.next;
					}
				}
			}
			return pg;
	}
	public Boolean IsMemberPg(String s){
		MyLinkedList<PageEntry>.node<PageEntry> tempentry = InvPgIdx.first.head;
		while(tempentry!=null){
			if(tempentry.data.name.equals(s))
				return true;
			tempentry = tempentry.next;
		}
		return false;
	}
	public PageEntry getPg(String s){
		MyLinkedList<PageEntry>.node<PageEntry> tempentry = InvPgIdx.first.head;
		while(tempentry!=null){
			if(tempentry.data.name.equals(s))
				return tempentry.data;
			tempentry = tempentry.next;
		}
		return null;
	}
}