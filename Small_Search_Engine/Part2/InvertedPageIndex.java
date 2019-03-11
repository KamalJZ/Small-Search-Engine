import java.util.*;
import java.lang.*;
public class InvertedPageIndex{
	Myset<PageEntry> pages=null;
	Myset<PageEntry> InvPgIdx = new Myset<PageEntry>();
	MyHashTable Table = new MyHashTable();
	void addPage(PageEntry p){
		if(InvPgIdx.IsMember(p));
		else{
		InvPgIdx.Insert(p);
		p.idWeb = this;
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
	public Myset<PageEntry>getPagesWhichContainPhrase(String str[]){
		//System.out.println("U r in getPagesWhichContainPhrase");
		Myset<PageEntry>pg = new Myset<PageEntry>();
		Myset<PageEntry>pgInt = getPagesWhichContainAllof(str);
		MyLinkedList<PageEntry>.node<PageEntry>tempnode = pgInt.first.head;
		while(tempnode!=null){
			
	//		System.out.println("Page :: "+tempnode.data.name);
			
			int x = 0;
			MyLinkedList<WordEntry>tempWordies = tempnode.data.p.getWordEntries(); //makes a list of all word entries in that page
			WordEntry tempWord = tempWordies.getMember(str[0]);// gets word entry of first word
			AVLtree temptree = tempWord.PosTree;//AVL tree of str[0] word
			MyLinkedList<Integer> indexS = temptree.InorderList(temptree.root);//Inorder arranged list of indicies of word str[0]
			MyLinkedList<Integer>.node<Integer> temp = indexS.head;
			
			while(temp!=null && temp.data<tempnode.data.totalWcount){		
				int baseKey = temp.data;
				if(IsNextOK(baseKey+1,tempWordies,1,str)){
					x++;
					pg.Insert(tempnode.data);

				}
				temp = temp.next;

			}
			tempnode.data.phraseCount = x;
			tempnode = tempnode.next;
		}

		return pg;
		
	}
	public int phraseCounter(PageEntry p, String str[]){
		return p.phraseCount;
	}
	public Myset<PageEntry>getPagesWhichContainAnyof(String str[]){
		Myset<PageEntry>pg = getPagesWhichContainWord(str[0]);
		for(int i=1;i<str.length;i++){
			pg = pg.Union(getPagesWhichContainWord(str[i]));
		}
		return pg;
		
	}
	public Myset<PageEntry>getPagesWhichContainAllof(String str[]){
		Myset<PageEntry>pg = getPagesWhichContainWord(str[0]);
		for(int i=1;i<str.length;i++){
			pg = pg.Intersection(getPagesWhichContainWord(str[i]));
		}
		return pg;
		
	}
	public Boolean IsNextOK(int index,MyLinkedList<WordEntry>words,int arrayIndex,String str[]){
		MyLinkedList<WordEntry>.node<WordEntry> temp = words.head;
		while(temp!=null){
			AVLtree tempTree =  temp.data.PosTree;
			if(tempTree.Find(tempTree.root,index)){
				if(temp.data.idword.equals(str[arrayIndex])){
					if(arrayIndex==str.length-1){
						return true;
					}
					return IsNextOK(index+1,words,arrayIndex+1,str);
				}
				else if(!temp.data.idword.equals(str[arrayIndex])){
					return false;
				}
			}


			temp = temp.next;
		}
		index++;
		return IsNextOK(index,words,arrayIndex,str);
	}
	public float InverseDocFq(String searchWord){
		Myset<PageEntry> wordContainerPg = getPagesWhichContainWord(searchWord);
		float f = 0.0F;
		f = ((float)InvPgIdx.first.NumberOfMembers()/wordContainerPg.first.NumberOfMembers());
		f = (float)Math.log(f);
		return f;
	}
	public float InverseDocFqPhrase(String str[]){
		Myset<PageEntry> wordContainerPg = pages;
		float f = 0.0F;
		f = ((float)(InvPgIdx.first.NumberOfMembers())/(wordContainerPg.first.NumberOfMembers()));
		f = (float)Math.log(f);
		return f;
	}

	public ArrayList getSortedPagesWhichContainPhrase(String str[]){
	//	System.out.println("U r in getSortedPagesWhichContainPhrase");
		 pages = getPagesWhichContainPhrase(str);
		Myset<SearchResult> sortingSet = new Myset<SearchResult>();
		MyLinkedList<PageEntry>.node<PageEntry>tempP = pages.first.head;
		while(tempP!=null){
			sortingSet.Insert(new SearchResult(tempP.data,tempP.data.getRelevanceOfPage(str, true)));
	//		System.out.println("Inside getSortedPhrasePg page being analyse "+tempP.data.name);
			tempP = tempP.next;
		}
		MySort sorting = new MySort();
		ArrayList sortedArray = sorting.sortThisList(sortingSet);
		return sortedArray;
	}
	public ArrayList getSortedPagesWhichContainAnyof(String str[]){
		Myset<PageEntry> pages = getPagesWhichContainAnyof(str);
		Myset<SearchResult> sortingSet = new Myset<SearchResult>();
		MyLinkedList<PageEntry>.node<PageEntry>tempP = pages.first.head;
		while(tempP!=null){
			//System.out.println(tempP.data.name);
			sortingSet.Insert(new SearchResult(tempP.data,tempP.data.getRelevanceOfPage(str, false)));

			tempP = tempP.next;
		}
		MySort sorting = new MySort();
		ArrayList sortedArray = sorting.sortThisList(sortingSet);
		return sortedArray;
	}
	public ArrayList getSortedPagesWhichContainAllof(String str[]){
		Myset<PageEntry> pages = getPagesWhichContainAllof(str);
		Myset<SearchResult> sortingSet = new Myset<SearchResult>();
		MyLinkedList<PageEntry>.node<PageEntry>tempP = pages.first.head;
		while(tempP!=null){
			sortingSet.Insert(new SearchResult(tempP.data,tempP.data.getRelevanceOfPage(str, false)));
			tempP = tempP.next;
		}
		MySort sorting = new MySort();
		ArrayList sortedArray = sorting.sortThisList(sortingSet);
		return sortedArray;
	}


}