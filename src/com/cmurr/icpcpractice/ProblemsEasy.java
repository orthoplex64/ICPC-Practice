package com.cmurr.icpcpractice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProblemsEasy {
	
	public static final java.io.PrintStream t = Main.t;
	
	public static boolean ep14(String str) {
		str = str.replace(" ", "").toLowerCase();
		Map<String, Integer> hist = new HashMap<>();
		for (String character : str.split("")) {
			hist.put(character, hist.containsKey(character) ? hist.get(character) + 1 : 1);
		}
		int numOddChars = 0;
		for (String key : hist.keySet()) {
			if (hist.get(key) % 2 == 1) {
				numOddChars++;
			}
		}
		// short for numOddChars > 0 && str.length() % 2 == 0 || numOddChars > 1 && str.length() % 2 == 1
		if (numOddChars > str.length() % 2) {
			return false;
		}
		return true;
	}
	
	public static void runEp14() {
		t.println("EP14:");
		for (String str : "Tact Coa;taco cat;atco cta;atco ctaa;taco tiger".split(";")) {
			t.println(str + ": " + ep14(str));
		}
	}
	
	public static void ep15(int[][] mat) {
		int n = mat.length;
		// 1 1 2 2   1 1 1 2 2
		// 1 1 2 2   1 1 1 2 2
		// 4 4 3 3   4 4 x 2 2
		// 4 4 3 3   4 4 3 3 3
		//           4 4 3 3 3
		for (int ir = 0; ir < n / 2; ir++) {
			for (int ic = 0; ic < (n + 1) / 2; ic++) {
				int row = ir, col = ic;
				// rotate each group of 4 pixels that rotate into each other
				int[] temps = new int[4];
				for (int i = 0; i < 4; i++) {
					temps[i] = mat[row][col];
					int newRow = col, newCol = n - 1 - row;
					row = newRow; col = newCol;
				}
				row = ir; col = ic;
				for (int i = 0; i < 4; i++) {
					int newRow = col, newCol = n - 1 - row;
					row = newRow; col = newCol;
					mat[row][col] = temps[i];
				}
			}
		}
	}
	
	public static void runEp15() {
		t.println("EP15:");
		for (int n = 1; n <= 7; n++) {
			int[][] mat = new int[n][n];
			for (int ir = 0; ir < n; ir++) {
				for (int ic = 0; ic < n; ic++) {
					mat[ir][ic] = (ir + 1) * 10 + ic + 1;
				}
			}
			t.println(Arrays.deepToString(mat).replaceAll("\\], \\[", "],\n ["));
			ep15(mat);
			t.println();
			t.println(Arrays.deepToString(mat).replaceAll("\\], \\[", "],\n ["));
			t.println("----------------");
		}
	}
	
	// the problem only specifies T1 and T2 are binary trees, not necessarily sorted
	public static boolean ep16(TreeNode<?> T1, TreeNode<?> T2) {
		if (T1 == null || T2 == null) return false;
		return T1.isSubtree(T2) ||
				ep16(T1.getLeft(), T2) ||
				ep16(T1.getRight(), T2);
	}
	
	public static void runEp16() {
		t.println("EP16:");
		TreeNode<Integer> bigTree = new TreeNode<>(7);
		for (int i : new int[]{2, 8, 1, 4, 3, 10, 6, 5, 9, -102, 1000000}) {
			bigTree.insert(i);
		}
		t.println("big tree:\n" + TreeNode.treeToString(bigTree, "  ", 0));
		TreeNode<Integer> smallTree = new TreeNode<>(2);
		for (int i : new int[]{1, 4, 3}) {
			smallTree.insert(i);
		}
		t.println("small tree:\n" + TreeNode.treeToString(smallTree, "  ", 0));
		t.println(ep16(bigTree, smallTree) + " " + ep16(smallTree, bigTree));
	}
	
	public static LLNode<?> ep17(LLNode<?> list) {
		Set<LLNode<?>> visited = new HashSet<>();
		while (list != null) {
			if (visited.contains(list)) return list;
			visited.add(list);
			list = list.getNext();
		}
		return null;
	}
	
	public static void runEp17() {
		t.println("EP17:");
		LLNode<String> list = new LLNode<>("A");
		list.setNext(new LLNode<String>("B"));
		list.getNext().setNext(new LLNode<String>("C"));
		list.getNext().getNext().setNext(new LLNode<String>("D"));
		list.getNext().getNext().getNext().setNext(new LLNode<String>("E"));
		list.getNext().getNext().getNext().getNext().setNext(list.getNext().getNext());
		t.println("list: " + list);
		t.println(ep17(list).getData());
	}
}