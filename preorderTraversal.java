import java.util.Iterator;
import java.util.Stack;


public class preorderTraversal implements Iterable<Integer>{
	private TreeNode root = null;
	private class TreeNode{
		int val;
		TreeNode left,right;
		public TreeNode(int val){
			this.val = val;
		}
	}
	public preorderTraversal(){
		init();
	}
	public void init(){
		root = new TreeNode(5);
		TreeNode a = new TreeNode(3);
		TreeNode b = new TreeNode(7);
		TreeNode d = new TreeNode(1);
		TreeNode e = new TreeNode(4);
		TreeNode f = new TreeNode(8);
		root.left = a;
		root.right = b;
		a.left = d;
		a.right = e;
		b.right = f;
	}
	public Iterator<Integer> iterator(){
		return new preorderIterator(root);
	}
	
	private class preorderIterator implements Iterator<Integer>{
		TreeNode ret = null;
		TreeNode node = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		public preorderIterator(TreeNode root){
			this.node = root;
		}
		public boolean hasNext(){
			if (stack.isEmpty() && node==null) return false;
			return true;
		}
		public Integer next(){
			if (node==null) {
				node = stack.pop();
			}
			ret = node;
			if (node.right!=null){
				stack.push(node.right);
			}
			node = node.left;
			return ret.val;
		}
		public void remove(){
		}
	}
	
	public static void main(String[] args){
		preorderTraversal tree = new preorderTraversal();
		for(Integer i: tree){
			System.out.println(i);
		}
	}
}
