import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class PageEntry{
	String name;
	PageIndex p = new PageIndex();
	PageEntry(String pageName){
		//System.out.println(pageName);
		name = pageName;
		BufferedReader br = null;
		int windex=0;
		try {
			String word;
			br = new BufferedReader(new FileReader(pageName));
			while((word = br.readLine())!=null){
			
				//System.out.println("before:" + word);
				word = word.toLowerCase();
				word = word.replaceAll("\\{"," ");
				word = word.replaceAll("\\}"," ");
				word = word.replaceAll("\\["," ");
				word = word.replaceAll("\\]"," ");
				word = word.replaceAll("\\<"," ");
				word = word.replaceAll("\\>"," " );
				word = word.replaceAll("\\="," ");
				word = word.replaceAll("\\("," ");
				word = word.replaceAll("\\)"," ");
				word = word.replaceAll("\\."," ");
				word = word.replaceAll("\\,"," ");
				word = word.replaceAll("\\;"," ");
				word = word.replaceAll("\\'"," ");
				word = word.replaceAll("\\?"," ");
				word = word.replaceAll("\\#"," ");
				word = word.replaceAll("\\!"," ");
				word = word.replaceAll("\\-"," ");
				word = word.replaceAll("\\:"," ");
				word = word.replaceAll("\""," ");
				
				Scanner s = new Scanner(word);
				String wordi;
				while(s.hasNext()){
					wordi = s.next();
					windex++;
				if(wordi.equals("stacks"))
					wordi = "stack";
				if(wordi.equals("structures"))
					wordi = "structure";
				if(wordi.equals("applications"))
					wordi = "application";
					
					

				if(wordi.equals("a")||wordi.equals("an")||wordi.equals("the")||wordi.equals("they")||wordi.equals("these")||wordi.equals("this")||wordi.equals("for")||wordi.equals("is")||wordi.equals("are")||wordi.equals("was")||wordi.equals("of")||wordi.equals("or")||wordi.equals("and")||wordi.equals("does")||wordi.equals("will")||wordi.equals("whose"));

				else{
					//System.out.println(wordi);
					Position posi = new Position(this , windex);
					p.addPositionForWord(wordi,posi);
				}
			}
			}
		}			
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	
	PageIndex getpageIndex(){
		return p;
	}
}