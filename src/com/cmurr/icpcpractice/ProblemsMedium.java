package com.cmurr.icpcpractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProblemsMedium {
	
	public static final java.io.PrintStream t = Main.t;
	
	@SuppressWarnings("unused")
	public static int mp3(int[] arr) {
		if (arr.length == 0) {
			return 0;
		}
		int bestSum = arr[0];
		// the description says to find the sequence but only return the sum
		int bestSeqStart = 0;
		int bestSeqEnd = 0;
		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = i; j < arr.length; j++) {
				sum += arr[j];
				if (sum > bestSum) {
					bestSum = sum;
					bestSeqStart = i;
					bestSeqEnd = j;
				}
			}
		}
		return bestSum;
	}
	
	public static void runMp3() {
		t.println("MP3:");
		int[][] arrs = {{2, -8, 3, -2, 4, -10}};
		for (int[] arr : arrs) {
			t.println(Arrays.toString(arr) + ": " + mp3(arr));
		}
	}
	
	// this solution isn't very clever but it works
	public static int[] mp4(int[] arr) {
		int[] res = new int[]{0, arr.length - 1};
		// Arrays.asList won't work. it thinks the array is the first element
		List<Integer> list = new ArrayList<>(arr.length);
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		Collections.sort(list);
		Integer[] arrSorted = list.toArray(new Integer[0]);
		for (res[0] = 0; res[0] < arr.length; res[0]++) {
			if (arr[res[0]] != (int) arrSorted[res[0]]) break;
		}
		for (res[1] = arr.length - 1; res[1] >= 0; res[1]--) {
			if (arr[res[1]] != (int) arrSorted[res[1]]) break;
		}
		return res[0] == arr.length && res[1] == -1 ? new int[]{0, arr.length - 1} : res;
	}
	
	public static void shuffle(int[] arr, int begin, int end) {
		for (int i = begin; i < end; i++) {
			int j = i + 1 + (int) (Math.random() * (end - i));
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
	
	public static void runMp4() {
		t.println("MP4:");
		int[] arrBase = new int[20];
		for (int i = 0; i < 20; i++) {
			arrBase[i] = i + 1;
		}
		t.println("arrBase: " + Arrays.toString(arrBase));
		t.println("mp4(arrBase): " + Arrays.toString(mp4(arrBase)));
		for (int i = 0; i < 5; i++) {
			int[] arr = Arrays.copyOf(arrBase, arrBase.length);
			shuffle(arr, (int) (Math.random() * arrBase.length / 2), arrBase.length / 2 + (int) (Math.random() * arrBase.length / 2));
			t.println("arr: " + Arrays.toString(arr));
			t.println("mp4(arr): " + Arrays.toString(mp4(arr)));
		}
		int[] arr = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
		t.println("arr: " + Arrays.toString(arr));
		t.println("mp4(arr): " + Arrays.toString(mp4(arr)));
	}
	
	// integer 0-n
	public static int rand(int n) {
		return (int) (Math.random() * n);
	}
	
	// given 0-n1, make 0-n2
	public static int mp5Core(int n1, int n2) {
		// find lowest power of n1 that's >= n2
		int pow = 1, exp = n1;
		while (exp < n2) {
			exp *= exp;
			pow++;
		}
		int scale = exp / n2;
		while (true) {
			int sum = 0;
			// roll each digit of a pretend base-n1 number
			for (int i = 0; i < pow; i++) {
				int place = 1;
				for (int j = 0; j < i; j++) {
					place *= n1;
				}
				sum += rand(n1) * place;
			}
			// consider sum possibly falling into one of n2 equally sized regions
			if (sum < scale * n2) {
				return sum / scale;
			}
			// if not, try again
		}
	}
	
	public static int mp5() {
		return mp5Core(5, 7);
	}
	
	public static void runMp5() {
		t.println("MP5:");
		int[] arr = new int[]{0, 0, 0, 0, 0, 0, 0};
		int numRolls = 10_000_000;
		for (int i = 0; i < numRolls; i++) {
			arr[mp5()]++;
		}
		double[] arrD = new double[arr.length];
		for (int i = 0; i < arr.length; i++) {
			arrD[i] = (double) arr[i] / ((double) numRolls / arr.length);
		}
		t.println(Arrays.toString(arr));
		t.println(Arrays.toString(arrD));
	}
	
	public static boolean mp6(int paramA, int paramB) {
		int a = paramA, b = paramB;
		a ^= b;
		b ^= a;
		a ^= b;
		return a == paramB && b == paramA;
	}
	
	// I can't take _full_ credit for this one. before attempting it, I remembered
	//   seeing that someone used XOR to swap values in place, but I didn't remember
	//   exactly how; I arrived at this solution after some thought. I don't know
	//   if it would have naturally occurred to me to try XOR had I not already
	//   seen it done.
	public static void runMp6() {
		t.println("MP6:");
		for (int i = 0; i < 5; i++) {
			int a = (int) (Math.random() * 201) - 100;
			int b = (int) (Math.random() * 201) - 100;
			t.println("[" + a + ", " + b + "]: " + mp6(a, b));
			t.println("[" + b + ", " + a + "]: " + mp6(b, a));
		}
	}
}