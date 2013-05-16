public class LinkedLists {
	public static void main(String args[]) {
		LinkedLists ll = new LinkedLists();
		Node head = new Node(1);
		for(int j=0; j<3; j++)
			for(int i=0; i<5; i++) 
				head.appendToTail(i);
		ll.removeDuplicate(head);
		ll.removeDuplicate(null);
		ll.removeDuplicate(new Node(1));		
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
	
	// 2.1
	public Node removeDuplicate(Node head) {
		Node.printList(head);
		
		if(head == null) 
			return null;
		if(head.next == null)
			return head;
		
		Node p1 = head;
		while(p1.next != null) {
			Node p2 = p1;
			while(p2.next != null) {
				if(p2.next.data == p1.data)
					p2.next = p2.next.next;
				else 
					p2 = p2.next;
			}
			if(p1.next != null)
				p1 = p1.next;
		}
		Node.printList(head);
		return head;
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