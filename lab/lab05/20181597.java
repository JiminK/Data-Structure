import java.util.*;

/**
 * Generic version of the SortedList class.
 */

class SortedList <T extends Comparable<T> > {

	class SortedListNode <U extends Comparable<U> > {
	    U data; // storage for data
		SortedListNode<U> link;    // link to the next node
	};

	private SortedListNode<T> first; // pointer to the dummy header node
	private SortedListNode<T> av; // pointer to the available list

	public SortedList() {
		av = null;
		first = null;
	}

	void Init(T data) { // SortedList constructor. Create a dummy first node
		first = GetNode();
		first.data = data;
		first.link = first;
		// first값이 null이 되면 link가 비어지는걸까? yes.
	}


	void Clear() {
	// 리스트를 모두 av로 리턴. 리스트 => SortedList
	// first가 가리키고 있는 list를 av로 반환.
	// first가 가리키는 모든 노드를 av로 반환. (헤더 노드 포함!!!!!!)
		SortedListNode<T> p = first;
		
		while(true) {
			
			if (p.link == first) {
				p.link = null;
				break;			
			}
			p = p.link;
		}
		if (av == null)
			av = first;
			
		else if (av != null) {
			p = av;
			
			while(true) {
				if (p.link == null) {
					p.link = first;
					break;
				}
				p = p.link;
			
			}
		
		}
		
		
		first = null;
		
		

	}

	SortedListNode<T> GetNode() { 
	// av에 달린 노드를 하나 떼어 리턴.
	// 노드를 생성하는 모든 곳에서 GetNode() 사용.
		SortedListNode<T> x = null;
		
		if (av == null) {
			x = new SortedListNode();
		}
		
		else if (av.link == null) {
			x = av;
			av = null;
		}
		
		else if (av != null) {
			x = av.link;
			av.link = av.link.link;
			
		}
		return x;
	}


	void Insert(T e) {	
	// 헤더 노드의 데이터 값이 max integer라는 것을 고려하여 구현 -> 위치 찾는데 편리, 같은 값을 가지는 노드 존재해도 그냥 정렬이 되도록 삽입.
	// 삽입할 위치 찾는다. == 삽입할 노드의 바로 앞 노드를 찾는다.
	// 값 비교는 Integer 클래스의 compareTo() 메소드 사용.
	// 이 p가 삽입될 노드의 바로 앞 노드를 가리킴.
	// 새로운 노드 생성할 때는 new아니고 GetNode() 함수 사용.
		SortedListNode<T> x = GetNode();
		x.data = e;
		
		SortedListNode<T> p = first;
		
		while(true) {
			if (p.link.data.compareTo(e) == 1) {
				x.link = p.link;
				p.link = x;
				break;		
			}
			p = p.link;
		}
	}


	public String toString() {
		String str = "";

		if (first == null) return "";
		SortedListNode<T> p = first.link;

		str += "List : ";
		// traverse all the nodes
		while (p != first) {
			str += p.data + "  ";
			p = p.link;
		}
		str += "\n";

		p = av;
		// show the count of av list
		int cnt = 0;
		while (p != null) {
			cnt++;
			p = p.link;
		}
		str +=  "Av : " + cnt;

		return str;
	}
}; 


