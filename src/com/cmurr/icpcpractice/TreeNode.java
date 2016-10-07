package com.cmurr.icpcpractice;

public class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<?>> {
	
	public static final java.io.PrintStream t = Main.t;
	private T data;
	private TreeNode<T> leftChild;
	private TreeNode<T> rightChild;
	
	public TreeNode(T data, TreeNode<T> leftChild, TreeNode<T> rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public TreeNode(T data) {
		this(data, null, null);
	}
	
	public T getData() { return data; }
	public void setData(T data) { this.data = data; }
	public TreeNode<T> getLeft() { return leftChild; }
	public void setLeft(TreeNode<T> node) { leftChild = node; }
	public TreeNode<T> getRight() { return rightChild; }
	public void setRight(TreeNode<T> node) { rightChild = node; }
	
	// assumes this is root of a sorted tree
	public TreeNode<T> insert(T data) {
		TreeNode<T> res = new TreeNode<T>(data);
		TreeNode<T> node = this;
		while (true) {
			int diff = res.compareTo(node);
			if (diff == 0) {
				throw new IllegalArgumentException("Inserted data is equivalent to existing data.");
			}
			if (diff < 0) {
				if (node.getLeft() == null) {
					node.setLeft(res);
					break;
				} else {
					node = node.getLeft();
				}
			} else {
				if (node.getRight() == null) {
					node.setRight(res);
					break;
				} else {
					node = node.getRight();
				}
			}
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public int compareTo(TreeNode<?> other) {
		if (data == null && other.data == null) return 0;
		if (data == null && other.data == null || !(data.getClass().isInstance(other.data))) {
			throw new IllegalArgumentException("Incompatible data.");
		}
		return data.compareTo((T) other.data);
	}
	
	public static String treeToString(TreeNode<?> node, String indent, int level) {
		if (node == null) return "";
		String res = "";
		res += treeToString(node.getRight(), indent, level + 1);
		for (int i = 0; i < level; i++) {
			res += indent;
		}
		res += node.data + "\n";
		res += treeToString(node.getLeft(), indent, level + 1);
		return res;
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof TreeNode<?>)) return false;
		Object otherData = ((TreeNode<?>) other).data;
		return data == null ? data == otherData : data.equals(otherData);
	}
	
	public boolean isSubtree(TreeNode<?> subTree) {
		if (subTree == null) return true;
		return equals(subTree) &&
				(getLeft() == null ? subTree.getLeft() == null : getLeft().isSubtree(subTree.getLeft())) &&
				(getRight() == null ? subTree.getRight() == null : getRight().isSubtree(subTree.getRight()));
	}
}