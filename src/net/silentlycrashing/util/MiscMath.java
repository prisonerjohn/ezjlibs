package net.silentlycrashing.util;

public class MiscMath {
	public static int randRange(int end) {
		return (int)Math.floor(computeRandRange(0, end));
	}
	
	public static double randRange(double end) {
		return computeRandRange(0, end);
	}
	
	public static int randRange(int start, int end) {
		return (int)Math.floor(computeRandRange(start, end));
	}
	
	public static double randRange(double start, double end) {
		return computeRandRange(start, end);
	}
	
	public static double computeRandRange(double start, double end) {
		return Math.random()*(end-start)+start;
	}
}
