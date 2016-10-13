package com.cmurr.icpcpractice;

import java.util.Arrays;

public class ProblemsHard {
	
	public static final java.io.PrintStream t = Main.t;
	
	// this is a bit esoteric but it makes sense when you think about it.
	//   it's basically a software implementation of a hardware bit adder
	public static int hp1(int a, int b) {
		// each element is the number of 1's in its index
		int[] bitAdder = {0, 1, 1, 2, 1, 2, 2, 3};
		int sum = 0;
		int carryBit = 0;
		for (int i = 1; i != 0; i <<= 1) {
			int bitAdderRes = bitAdder[(a & 1) << 2 | (b & 1) << 1 | carryBit];
			carryBit = bitAdderRes >> 1;
			if ((bitAdderRes & 1) == 1) {
				sum |= i;
			}
			a >>= 1;
			b >>= 1;
		}
		return sum;
	}
	
	public static void runHp1() {
		t.println("HP1:");
		for (int[] addends : new int[][]{{23,45},{23,-45},{-23,45},{-23,-45}}) {
			t.println(addends[0] + " + " + addends[1] + " = " + hp1(addends[0], addends[1]));
			t.println(addends[1] + " + " + addends[0] + " = " + hp1(addends[1], addends[0]));
		}
	}
	
	public static void shuffle(int[] arr, int begin, int end) {
		for (int i = begin; i < end; i++) {
			int j = i + 1 + (int) (Math.random() * (end - i));
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
	
	public static void hp2(int[] deck) {
		shuffle(deck, 0, deck.length - 1);
	}
	
	public static void runHp2() {
		t.println("HP2:");
		int numShuffles = 1_000_000;
		int[] baseDeck = new int[52];
		int[] hist = new int[baseDeck.length];
		for (int i = 0; i < baseDeck.length; i++) {
			baseDeck[i] = i + 1;
			hist[i] = 0;
		}
		int[] deck = new int[baseDeck.length];
		for (int i = 0; i < numShuffles; i++) {
			for (int j = 0; j < deck.length; j++) {
				deck[j] = baseDeck[j];
			}
			hp2(deck);
			for (int j = 0; j < deck.length; j++) {
				hist[j] += deck[j];
			}
		}
		double[] histD = new double[hist.length];
		for (int i = 0; i < hist.length; i++) {
			histD[i] = (double) hist[i] / ((double) numShuffles * 6);
		}
		t.println(Arrays.toString(hist));
		t.println(Arrays.toString(histD));
	}
}