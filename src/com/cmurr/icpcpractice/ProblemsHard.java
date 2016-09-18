package com.cmurr.icpcpractice;

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
}