package com.cmurr.icpcpractice;

import java.util.Arrays;

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
}