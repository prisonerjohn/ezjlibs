package net.silentlycrashing.gestures;

/**
 * Holds the regex pattern for a clockwise twirl.
 */
public interface CWTwirl {
	public static final String CW_PATTERN = "^(?:RDLU(?:RDLU)*(?:RDL|RD|R)?|DLUR(?:DLUR)*(?:DLU|DL|D)?|LURD(?:LURD)*(?:LUR|LU|L)?|ULDR(?:ULDR)*(?:ULD|UL|U)?)$";
}
