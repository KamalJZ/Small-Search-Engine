import java.util.*;
public class SearchResult implements Comparable<SearchResult>{
	PageEntry page;
	float relevance;
	public SearchResult(PageEntry p, float r){
		page = p;
		relevance = r;
	}
	public PageEntry getPageEntry(){
		return page;
	}
	public float getRelevance(){
		return relevance;
	}
	@Override
	public int compareTo(SearchResult otherObject){
		if(otherObject.relevance>this.relevance)
			return 1;
		else if(otherObject.relevance<this.relevance)
			return -1;
		else 
			return 0;
	}
}