import java.util.Hashtable;
public class LinkedLists {
	public static void main(String args[]) {
		LinkedLists ll = new LinkedLists();
		Node head = new Node(1);
		for(int j=0; j<3; j++)
			for(int i=0; i<5; i++) 
				head.appendToTail(i);
		ll.removeDuplicate(head);
		for(int j=0; j<3; j++)
			for(int i=0; i<5; i++) 
				head.appendToTail(i);
		ll.getKthItemToEnd(head, 5);
		ll.removeDuplicateWithBuffer(head);
		
		ll.getKthItemToEndWithTwoPointer(head, 1);
		ll.getKthItemToEndWithTwoPointer(head, 30);
		ll.removeDuplicate(null);
		ll.removeDuplicate(new Node(1));	
		
		head = new Node(1);
		Node.printList(head);
		for(int i=2; i<=5; i++) {
			head.appendToTail(i);
		}
		ll.deleteNode(ll.getKthItemToEndWithTwoPointer(head, 3));	
		ll.deleteNode(ll.getKthItemToEndWithTwoPointer(head, 1));	
		Node.printList(head);
		head = new Node(30);
		Node.printList(head);
		for(int i=10; i>0; i--) {
			head.appendToTail(i);
		}
		System.out.println("********** Around Test **************");
		Node.printList(head);
		head = ll.aroundValue(head, 20);
		Node.printList(head);
		head = ll.aroundValue(head, 5);
		Node.printList(head);
		System.out.println("********** Sum List Reverse Test **************");
		Node n1 = new Node(3);
		n1.appendToTail(2);
		n1.appendToTail(9);
		n1.appendToTail(9);
		n1.appendToTail(9);
		n1.appendToTail(9);
		Node n2 = new Node(9);
		n2.appendToTail(8);
		System.out.println("********** Sum List Forward Test **************");
		ll.sumListReverse(n1, n2);
		n1 = new Node(1);
		n2 = new Node(9);
		n2.appendToTail(9);
		ll.sumListForward(n1, n2);
		System.out.println("********** Check Circular **************");
		Node n11 = new Node(1);
		Node n12 = new Node(2);
		Node n13 = new Node(3);
		Node n14 = new Node(4);
		Node n15 = new Node(5);
		
		n11.next = n12;
		n12.next = n13;
		n13.next = n14;
		n14.next = n15;
		n15.next = n13;
		System.out.println(ll.checkCircular(n11).data);
	}
	public Node deleteNode(Node head, int data) {
		if(head.data == data)
			return head.next;
		Node temp = head;
		while(temp.next != null) {
			if(temp.next.data == data) {
				temp.next = temp.next.next;	 
				return head;
			}
			temp = temp.next;
		}
		return head;
	}
	
	// 2.1 with buffer
	public Node removeDuplicateWithBuffer(Node head) {
		Node.printList(head);
		if(head == null)
			return null;
		Hashtable table = new Hashtable();
		Node previous = head;
		Node current = head;
		while(current != null) {
			if(table.containsKey(current.data))
				previous.next = current.next;
			else {
				table.put(current.data, true);
				previous = current;
			}			
			current = current.next;
		}
		Node.printList(head);
		return head;
	}
	
	
	// 2.1 without buffer
	public Node removeDuplicate(Node head) {
		Node.printList(head);
		
		if(head == null) 
			return null;
		
		Node p1 = head;
		while(p1 != null) {
			Node p2 = p1;
			while(p2.next != null) {
				if(p2.next.data == p1.data)
					p2.next = p2.next.next;
				else 
					p2 = p2.next;
			}
			p1 = p1.next;
		}
		Node.printList(head);
		return head;
	}
	
	// 2.2 which is 1 based index
	// trivial, not complex and not efficient!
	public Node getKthItemToEnd(Node head, int indexToEnd) {
		Node.printList(head);
		if(head == null) 
			return null;
			
		int length = 0;
		Node n = head;
		while(n != null) {
			length++;
			n = n.next;
		}
		int index = length + 1  - indexToEnd;
		if(index <= 0)
			return null;
			
		n = head;
		while(index > 1) {
			n = n.next;
			index --;
		}
		System.out.println("the "+indexToEnd+ "th (1 based) item to end is " + n.data);
		System.out.println("the index (1 based) to head is " + (length + 1 - indexToEnd));
		return n;
	}
	public Node getKthItemToEndWithTwoPointer(Node head, int indexToEnd) {
		Node.printList(head);
		if(indexToEnd <= 0)
			return null;
		Node p1 = head, p2 = head;
		for(int i=0; i<indexToEnd-1; i++) {
			if(p2 == null)
				return null;
			p2 = p2.next;
		}
		if(p2 == null)
			return null;
		while(p2.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		System.out.println("the "+indexToEnd+ "th (1 based) item to end is " + p1.data);
		return p1;
	}
	// 2.3 delete node
	public boolean deleteNode(Node n) {
		if(n == null)
			return false;
		if(n.next == null) 
			return false;
			
		Node next = n.next;
		System.out.println("Deleting Node "+ n.data);
		/*
		Not efficient!
		while(n.next != null) {
			n.data = n.next.data;
			pre = n;
			n = n.next;
		}
		*/
		n.data = next.data;
		n.next = next.next;
		return true;
	}

	// 2.4 
	public Node aroundValue(Node head, int value) {
		Node headBefore = null;
		Node endBefore = null;
		Node headAfter = null;
		Node endAfter = null;
		
		Node n = head;
		while(n != null) {
			Node next = n.next;
			n.next = null;
			if(n.data >= value) {
				if(headAfter == null) {
					headAfter = n;
					endAfter = headAfter;
				} else {
					endAfter.next = n;
					endAfter = endAfter.next;
				}
			} else {
				if(headBefore == null) {
					headBefore = n;
					endBefore = headBefore;
				} else {
					endBefore.next = n;
					endBefore = endBefore.next;
				}
			}
			n = next;
		}
		if(endBefore != null) {
			endBefore.next = headAfter;
			return headBefore;
		} else {
			return headAfter;
		}
	}
	
	// 2.5
	public Node sumListReverse(Node n1, Node n2) {
		Node.printList(n1);
		Node.printList(n2);
		
		int rest = 0;
		Node result = null;
		
		while(n1 != null || n2 != null) {
			int sum;
			if(n1 != null && n2 != null) {
				sum = n1.data + n2.data + rest;
			} else if(n1 == null) {
				sum = n2.data + rest;	
			} else {
				sum = n1.data + rest;
			}
			if(result == null) {
				result = new Node(sum % 10);
				rest = sum / 10;
			} else {
				result.appendToTail(sum % 10);
				rest = sum / 10;
			}
			if(n1 != null)
				n1 = n1.next;
			if(n2 != null)
				n2 = n2.next;
		}
		if(rest != 0) {
			result.appendToTail(rest);
		}
		Node.printList(n1);
		Node.printList(n2);
		Node.printList(result);
		return result;
	}
	public Node sumListForward(Node n1, Node n2) {
		
		if(n1 == null && n2 == null) {
			return null;
		}
		Node.printList(n1);
		Node.printList(n2);
		int n1Length = 0, n2Length = 0;
		
		Node temp = n1;
		while(temp != null) {
			n1Length ++;
			temp = temp.next;
		}
		temp = n2;
		while(temp != null) {
			n2Length ++;
			temp = temp.next;
		}
		int rest = 0;
		Node result = null;
		while(n1Length > 0 || n2Length > 0) {
			int sum = rest;
			if(n1Length > 0) {
				temp = n1;
				for(int i = n1Length - 1; i > 0; i--) {
					temp = temp.next;
				}
				sum += temp.data;
				n1Length --;
			}
			if(n2Length > 0) {
				temp = n2;
				for(int i = n2Length - 1; i > 0; i--) {
					temp = temp.next;
				}
				sum += temp.data;
				n2Length --;
			}
			if(result == null) {
				result = new Node(sum % 10);
				rest = sum / 10;
			} else {
				temp = new Node(sum % 10);
				temp.next = result;
				result = temp;
				rest = sum / 10;
			}
		}
		if(rest > 0) {
			temp = new Node(rest);
			temp.next = result;
			result = temp;
		}
		Node.printList(n1);
		Node.printList(n2);
		Node.printList(result);
		return result;
	}
	
	// 2.6 
	public Node checkCircular(Node n) {
		if(n == null) 
			return n;
		if(n.next == null) 
			return n;
		Hashtable<Node, Boolean> table = new Hashtable<Node, Boolean>();
		while(n != null) {
			if(table.containsKey(n)) {
				return n;	
			} else {
				table.put(n, true);
			}
			n = n.next;
		}
		return null;
	}
	
	// 2.7
	public boolean checkPalindrome(Node n) {
		if(n == null) 
			return false;
		if(n.next == null) 
			return true;
			
		int count = 0;
		Node temp = n;
		while(temp != null) {
			count ++;
			temp = temp.next;
		}
		Node n1, n2;
		for(int i=0; i < count/2; i++) {
			n1 = n;
			n2 = n;
			for(int j=0; j < i; j++) 
				n1 = n1.next;
			
			for(int k=0; k < count - i - 1; k++)
				n2 = n2.next;
			if(n1.data != n1.data)
				return false;
		}
		return true;
	}
	
}


	
class Node {
	Node next;
	int data;
	
	public Node(int data) {
		this.data = data;
	}
	public void appendToTail(int data) {
		Node end = new Node(data);
		Node temp = this;
		while(temp.next != null)
			temp = temp.next;
		temp.next = end;
	}
	public static void printList(Node head) {
		if(head == null) 
			return;
		Node n = head.next;
		System.out.print("NodeList: " + head.data);
		while(n != null) {
			System.out.print(" -> " + n.data);
			n = n.next;
		}
		System.out.print(" ;\n");
	}
}