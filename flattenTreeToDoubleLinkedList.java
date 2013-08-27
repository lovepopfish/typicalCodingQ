/**
 * convert			4
 * 			/		\
 * 		  2			  5
 *      	/	\			\
 * 	    1    	  3      		  6
 *       /
 *  0
 * 
 * to
 * 
 * 	0 <-> 1 <-> 2 <-> 3 <-> 4 <-> 5 <-> 6
 * 
 * Sol1: Method --> convert()
 * 		 create a data structure PairNode to record head and tail of current LL 
 * 
 * Sol2: Method --> convert2()
 * 		 just record the head of current LL, do linear search to find the tail
 * 
 * Sol3: Method --> convert3()
 * 		 create circular LL, to find the tail of current LL, use head.pre
 *
 */
public class flattenTreeToDoubleLinkedList {
	private BiNode root = null;
	private class BiNode{
		int val;
		BiNode pre, next;
		public BiNode(int v){
			this.val = v;
		}
	}
	
	// create tree
	public void init(){
		root = new BiNode(4);
		BiNode a = new BiNode(2);
		BiNode b = new BiNode(5);
		BiNode c = new BiNode(1);
		BiNode d = new BiNode(3);
		BiNode e = new BiNode(6);
		BiNode f = new BiNode(0);
		root.pre = a;
		root.next = b;
		a.pre = c;
		a.next = d;
		b.next = e;
		c.pre = f;
	}
	
	//Sol1 data structure
	private class NodePair{
		BiNode head, tail;
		public NodePair(BiNode head, BiNode tail){
			this.head = head;
			this.tail = tail;
		}
	}
	//Sol1
	public NodePair convert(){
		return convert(root);
	}
	private NodePair convert(BiNode root){
		if (root == null) return null;
		NodePair leftPart = convert(root.pre);
		NodePair rightPart = convert(root.next);
		if(leftPart!=null){
			concat(leftPart.tail,root);
		}
		if(rightPart!=null){
			concat(root,rightPart.head);
		}
		return new NodePair(leftPart==null?root:leftPart.head,
							rightPart==null?root:rightPart.tail);
	}
	// attach two BiNode
	private void concat(BiNode x, BiNode y){
		x.next = y;
		y.pre = x;
	}
	

	//Sol2
	public BiNode convert2(){
		return convert2(root);
	}
	private BiNode convert2(BiNode root){
		if (root==null) return null;
		BiNode leftPart = convert2(root.pre);
		BiNode rightPart = convert2(root.next);
		if (leftPart!=null){
			concat(getTail(leftPart),root);
		}
		if (rightPart!=null){
			concat(root,rightPart);
		}
		return leftPart==null?root:leftPart;
	}
	private BiNode getTail(BiNode root){
		if (root==null) return null;
		while(root.next!=null){
			root = root.next;
		}
		return root;
	}
	
	//Sol3
	public BiNode convert3(){
		return convert2(root);
	}
	private BiNode convert3(BiNode root){
		if (root==null) return null;
		BiNode leftPart = convert3(root.pre);
		BiNode rightPart = convert3(root.next);
		if (leftPart==null && rightPart==null){
			root.pre = root;
			root.next = root;
			return root;
		}
		BiNode tail = rightPart==null?null:rightPart.pre; //find the tail of rightPart
		// join left to root;
		if (leftPart==null){ //if leftPart==null attach tail node in rightPart to root
			concat(rightPart.pre,root);
		} else {
			concat(leftPart.pre,root);
		}
		// join right to root
		if (rightPart==null){
			concat(root,leftPart); //if rightPart==null attach tail(it is root now!) to the head of leftPart
		} else {
			concat(root,rightPart);
		}
		// if leftPart !=null && rightPart !=null // attach tail directly
		if (leftPart!=null && rightPart!=null){
			concat(tail,leftPart);
		}
		return leftPart==null?root:leftPart;
	}
	
	
	
	public static void main(String[] args){
		flattenTreeToDoubleLinkedList tree = new flattenTreeToDoubleLinkedList();
		//Sol1 output
		tree.init();
		System.out.println("Tree has been initialized");
		System.out.println("Sol1--------------------->");
		NodePair ret = tree.convert();
		BiNode head = ret.head;
		BiNode tail = ret.tail;
		while(head!=null){
			System.out.print(head.val + "->");
			head = head.next;
		}
		System.out.println();
		while(tail!=null){
			System.out.print(tail.val + "->");
			tail = tail.pre;
		}
		
		//Sol2 output
		System.out.println();
		System.out.println("Sol2--------------------->");
		tree.init();
		System.out.println("Tree has been initialized");
		BiNode ret2 = tree.convert2();
		while(ret2.next!=null){
			System.out.print(ret2.val + "->");
			ret2 = ret2.next;
		}
		System.out.print(ret2.val + "->");
		System.out.println();
		while(ret2.pre!=null){
			System.out.print(ret2.val + "->");
			ret2 = ret2.pre;
		}
		System.out.print(ret2.val + "->");
		
		//Sol3 output
		System.out.println();
		System.out.println("Sol3--------------------->");
		tree.init();
		System.out.println("Tree has been initialized");
		BiNode ret3 = tree.convert3();
		while(ret3.next!=null){
			System.out.print(ret3.val + "->");
			ret3 = ret3.next;
		}
		System.out.print(ret3.val + "->");
		System.out.println();
		while(ret3.pre!=null){
			System.out.print(ret3.val + "->");
			ret3 = ret3.pre;
		}
			System.out.print(ret3.val + "->");		
	}
}
