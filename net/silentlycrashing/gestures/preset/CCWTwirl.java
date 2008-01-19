package net.silentlycrashing.gestures.preset;

/**
 * Holds the regex pattern for a counter-clockwise twirl.
 */
public interface CCWTwirl {
	public static final String CCW_PATTERN = "^(?:ULDR(?:ULDR)*(?:ULD|UL|U)?|LDRU(?:LDRU)*(?:LDR|LD|L)?|DRUL(?:DRUL)*(?:DRU|DR|D)?|RULD(?:RULD)*(?:RUL|RU|R)?)$";	
}
