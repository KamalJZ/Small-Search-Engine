import java.util.*;
public class SearchEngine{
	public InvertedPageIndex webpage;
	public SearchEngine(){
		webpage = new InvertedPageIndex();
	}
	public void performAction(String actionMessage){
		Scanner s = new Scanner(actionMessage);
		String a;
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
				Myset<PageEntry>pages=webpage.getPagesWhichContainWord(a);
				if(!pages.IsEmpty())
					pages.first.PrintList();
				else{
					System.out.println("No webpage contains word "+a);
				}

				break;

			case "queryFindPositionsOfWordInAPage":
				a = s.next();
				a = a.toLowerCase();
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
							if(tempPosi.data.getPageEntry().name.equals(b)){
								System.out.print(tempPosi.data.getWordIndex());
								if(tempPosi2.data.getPageEntry().name.equals(b))
									System.out.print(", ");
							}
							tempPosi = tempPosi.next;

						}
						if(tempPosi.data.getPageEntry().name.equals(b))
							System.out.println(tempPosi.data.getWordIndex());
						else 
							System.out.println("");
								
					}
					else{
						System.out.println("Webpage "+b+" does not contain word "+a );
					}
				}
				else{
					System.out.println("No webpage "+b+" found");
				}
				break;


			case "print":
				webpage.InvPgIdx.first.PrintList();
				break;
		}
	}
}