import java.util.*;

public class TestMyset{
	public static void main(String args[]){ 
		Myset<Integer> A = new Myset<Integer>(0);
		A.Insert(1);
		A.Insert(2);
		A.Insert(3);
		A.Insert(4);
		A.Insert(5);
		Myset<Integer> B = new Myset<Integer>();
		B.Insert(3);
		B.Insert(4);
		B.Insert(5);
		B.Insert(6);
		B.Insert(7);

		Myset<Integer> C = A.Union(B);
		Myset<Integer> D = B.Intersection(A);
		A.first.PrintList();
		System.out.println("");
		B.first.PrintList();
		System.out.println("");
		C.first.PrintList();
		System.out.println("");
		D.first.PrintList();
		System.out.println("");
		A.Delete(4);
		A.first.PrintList();
		System.out.println("");
		System.out.println(B.IsMember(4));
		System.out.println(A.IsMember(4));
		B.Delete(3);
		B.first.PrintList();
		System.out.println("");
		B.Delete(4);
		B.first.PrintList();
		System.out.println("");
		B.Delete(5);
		B.first.PrintList();
		System.out.println("");
		B.Delete(6);
		B.first.PrintList();
		System.out.println("");
		System.out.println(B.IsEmpty());
		B.Delete(7);
		System.out.println(B.IsEmpty());
		B.first.PrintList();		
		System.out.println(B.IsEmpty());	
	}
}