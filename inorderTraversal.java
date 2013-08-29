import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;


public class inorderTraversal implements Iterable<Integer> {
	private TreeNode root;
	private class TreeNode{
		int val;
		TreeNode left,right;
		public TreeNode(int v){
			this.val = v;
		}
	}
	public inorderTraversal(){
		init();
	}
	
	private class inorderIterator implements Iterator<Integer> {
		TreeNode ret = null;
		TreeNode cur = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		public inorderIterator(TreeNode root){
			this.cur = root;
		}
		
		public boolean hasNext(){
			if (cur==null && stack.isEmpty()) return false;
			return true;
		}
		
		public Integer next(){
			if (!hasNext()){
				throw new NoSuchElementException();
			}
			if (cur!=null){
				while(cur!=null){
					stack.push(cur);
					cur = cur.left;
				}
			}
			TreeNode ret = stack.pop();
			cur = ret;
			cur = cur.right;
			return ret.val;
		}
		
		public void remove(){
		}
	}
	public Iterator<Integer> iterator() {
		return new inorderIterator(root);
	}
	public void init(){
		root = new TreeNode(7);
		TreeNode a = new TreeNode(3);
		TreeNode b = new TreeNode(8);
		TreeNode c = new TreeNode(1);
		TreeNode d = new TreeNode(5);
		TreeNode e = new TreeNode(9);
		TreeNode f = new TreeNode(6);
		root.left = a;
		root.right = b;
		a.left = c;
		a.right = d;
		d.right = f;
		b.right = e;
	}
	
	public static void main(String[] args){
		inorderTraversal tree = new inorderTraversal();
		for(Integer i : tree){
			System.out.println(i);
		}
	}
}
