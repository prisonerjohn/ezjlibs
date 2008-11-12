/*
  This file is part of the ezjlibs project.
  http://www.silentlycrashing.net/ezgestures/

  Copyright (c) 2007-08 Elie Zananiri

  ezjlibs is free software: you can redistribute it and/or modify it under
  the terms of the GNU General Public License as published by the Free Software 
  Foundation, either version 3 of the License, or (at your option) any later 
  version.

  ezjlibs is distributed in the hope that it will be useful, but WITHOUT ANY
  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR 
  A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with 
  ezjlibs.  If not, see <http://www.gnu.org/licenses/>.
*/

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
