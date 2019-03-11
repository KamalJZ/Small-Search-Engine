import java.util.*;
public class SearchEngine{
	public InvertedPageIndex webpage;
	public SearchEngine(){
		webpage = new InvertedPageIndex();
	}
	public void performAction(String actionMessage){
		Scanner s = new Scanner(actionMessage);
		String a, wordi;
		String str[]= new String[100];
		String[] finalStr;
		int i;
		Myset<PageEntry>pgs = new Myset<PageEntry>();
		ArrayList<SearchResult> array = new ArrayList<SearchResult>();
		//System.out.println(actionMessage);
		switch(s.next()){
			case "addPage":
				a = s.next();

				//System.out.println(a);
				PageEntry entry = new PageEntry(a);
				webpage.addPage(entry);
				break;

			case "queryFindPagesWhichContainWord":
				a = s.next();
				a=a.toLowerCase();
				//System.out.println(a);
					if(a.equals("stacks"))
						a = "stack";
					if(a.equals("structures"))
						a = "structure";
					if(a.equals("applications"))
						a = "application";
				Myset<PageEntry>pages=webpage.getPagesWhichContainWord(a);
				String[] strA = new String[1];
				strA[0]=a;
				array = webpage.getSortedPagesWhichContainAllof(strA);
				/*if(!pages.IsEmpty())
					pages.first.PrintList();
				*/
				if(array.size()!=0){
					int k=0;
					for(k=0;k<array.size()-1;k++){
						System.out.print(array.get(k).getPageEntry().name+", ");
					}
					System.out.println(array.get(k).getPageEntry().name);
				}
				else{
					System.out.println("No webpage contains word "+a);
				}

				break;

			case "queryFindPositionsOfWordInAPage":
				a = s.next();
				a = a.toLowerCase();
					if(a.equals("stacks"))
						a = "stack";
					if(a.equals("structures"))
						a = "structure";
					if(a.equals("applications"))
						a = "application";
				String b = s.next();
				if(webpage.IsMemberPg(b)){
					PageEntry pgE = webpage.getPg(b); 
					MyLinkedList<WordEntry> tempWords = pgE.p.getWordEntries();
					if(tempWords.IsMember(a)){
						WordEntry wd = tempWords.getMember(a);
						MyLinkedList<Position>wdPositions = wd.getAllPositionsForThisWord();
						MyLinkedList<Position>.node<Position> tempPosi = wdPositions.head;
						while(tempPosi.next!=null){
							MyLinkedList<Position>.node<Position> tempPosi2 = tempPosi.next;
							//if(tempPosi.data.getPageEntry().name.equals(b)){
								System.out.print(tempPosi.data.getWordIndex());
							//	if(tempPosi2.data.getPageEntry().name.equals(b))
									System.out.print(", ");
							
							tempPosi = tempPosi.next;

						}
						//if(tempPosi.data.getPageEntry().name.equals(b))
							System.out.println(tempPosi.data.getWordIndex());
							//System.out.println("Term Frequency of "+a+" is "+wd.getTermFrequency(a));
						//else 
						//	System.out.println("");
								
					}
					else{
						System.out.println("Webpage "+b+" does not contain word "+a );
					}
				}
				else{
					System.out.println("No webpage "+b+" found");
				}
				break;

			case "queryFindPagesWhichContainAllWords":	
				i=0;
				while(s.hasNext()){
					wordi = s.next().toLowerCase();
					if(wordi.equals("stacks"))
						wordi = "stack";
					if(wordi.equals("structures"))
						wordi = "structure";
					if(wordi.equals("applications"))
						wordi = "application";
					if(wordi.equals("a")||wordi.equals("an")||wordi.equals("the")||wordi.equals("they")||wordi.equals("these")||wordi.equals("this")||wordi.equals("for")||wordi.equals("is")||wordi.equals("are")||wordi.equals("was")||wordi.equals("of")||wordi.equals("or")||wordi.equals("and")||wordi.equals("does")||wordi.equals("will")||wordi.equals("whose"));
					else{
						str[i]=wordi;
						i++;
					}
				}
				finalStr=new String[i];
				for(int j=0;j<i;j++){
					finalStr[j]=str[j];
				}
				pgs = webpage.getPagesWhichContainAllof(finalStr);
				array = webpage.getSortedPagesWhichContainAllof(finalStr);
				/*if(!pgs.IsEmpty())
					pgs.first.PrintList();
				*/
				if(array.size()!=0){
					int k=0;
					for(k=0;k<array.size()-1;k++){
						System.out.print(array.get(k).getPageEntry().name+", ");
					}
					System.out.println(array.get(k).getPageEntry().name);
				}
				else{
					System.out.println("No webpage contains all of the given words");
				}
				break;


			case "queryFindPagesWhichContainAnyOfTheseWords":
				i=0;
				while(s.hasNext()){
					wordi = s.next().toLowerCase();
					if(wordi.equals("stacks"))
						wordi = "stack";
					if(wordi.equals("structures"))
						wordi = "structure";
					if(wordi.equals("applications"))
						wordi = "application";
					if(wordi.equals("a")||wordi.equals("an")||wordi.equals("the")||wordi.equals("they")||wordi.equals("these")||wordi.equals("this")||wordi.equals("for")||wordi.equals("is")||wordi.equals("are")||wordi.equals("was")||wordi.equals("of")||wordi.equals("or")||wordi.equals("and")||wordi.equals("does")||wordi.equals("will")||wordi.equals("whose"));
					else{
						str[i]=wordi;
						i++;
					}
				}
				finalStr=new String[i];
				for(int j=0;j<i;j++){
					finalStr[j]=str[j];
				}
				pgs = webpage.getPagesWhichContainAnyof(finalStr);
				array = webpage.getSortedPagesWhichContainAnyof(finalStr);
				/*if(!pgs.IsEmpty())
					pgs.first.PrintList();
				*/
				if(array.size()!=0){
					int k=0;
					for(k=0;k<array.size()-1;k++){
						System.out.print(array.get(k).getPageEntry().name+", ");
					}
					System.out.println(array.get(k).getPageEntry().name);
				}
				else{
					System.out.println("No webpage contains any of the given words");
				}	
				break;

			case "queryFindPagesWhichContainPhrase":
				i=0;
				while(s.hasNext()){
					wordi = s.next().toLowerCase();
					if(wordi.equals("stacks"))
						wordi = "stack";
					if(wordi.equals("structures"))
						wordi = "structure";
					if(wordi.equals("applications"))
						wordi = "application";
					if(wordi.equals("a")||wordi.equals("an")||wordi.equals("the")||wordi.equals("they")||wordi.equals("these")||wordi.equals("this")||wordi.equals("for")||wordi.equals("is")||wordi.equals("are")||wordi.equals("was")||wordi.equals("of")||wordi.equals("or")||wordi.equals("and")||wordi.equals("does")||wordi.equals("will")||wordi.equals("whose"));
					else{
						str[i]=wordi;
						i++;
					}
				}
				finalStr=new String[i];
				for(int j=0;j<i;j++){
					finalStr[j]=str[j];
				}
				pgs = webpage.getPagesWhichContainPhrase(finalStr);
				array = webpage.getSortedPagesWhichContainPhrase(finalStr);
				if(array.size()!=0){
					int k=0;
					for(k=0;k<array.size()-1;k++){
						System.out.print(array.get(k).getPageEntry().name+", ");
					}
					System.out.println(array.get(k).getPageEntry().name);
				}
				/*if(!pgs.IsEmpty())
					pgs.first.PrintList();
				*/
				else{
					System.out.println("No webpage contains given phrase");
				}	
				break;

			case "print":
				webpage.InvPgIdx.first.PrintList();
				break;
		}
	}
}