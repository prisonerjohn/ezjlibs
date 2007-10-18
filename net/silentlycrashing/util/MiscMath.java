package net.silentlycrashing.util;

public class MiscMath {
	public static int randRange(int start, int end) {
		return (int)Math.floor(Math.random()*(end-start))+start;
	}
	
	public static double randRange(double start, double end) {
		return Math.floor(Math.random()*(end-start))+start;
	}
}
