package com.cmurr.icpcpractice;

import java.util.HashMap;
import java.util.Map;

public class LLNode<T> {
	
	public static final java.io.PrintStream t = Main.t;
	private T data;
	private LLNode<T> next;
	
	public LLNode(T data, LLNode<T> next) {
		this.data = data;
		this.next = next;
	}
	
	public LLNode(T data) {
		this(data, null);
	}
	
	public T getData() { return data; }
	public void setData(T data) { this.data = data; }
	public LLNode<T> getNext() { return next; }
	public void setNext(LLNode<T> next) { this.next = next; }
	
	public String toString() {
		String res = "";
		Map<LLNode<T>, Integer> visited = new HashMap<>();
		LLNode<T> node = this;
		while (node != null) {
			if (node != this) {
				res += " -> ";
			}
			visited.put(node, visited.containsKey(node) ? visited.get(node) + 1 : 1);
			if (visited.get(node) == 3) {
				res += "...";
				break;
			} else {
				res += node.data;
			}
			node = node.getNext();
		}
		return res;
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof LLNode<?>)) return false;
		Object otherData = ((LLNode<?>) other).data;
		return data == null ? data == otherData : data.equals(otherData);
	}
}