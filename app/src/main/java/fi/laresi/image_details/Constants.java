package fi.laresi.image_details;

import java.util.HexFormat;

public final class Constants {
	private Constants() {}
	
	public static final byte[] PNG_HEADER = 
		HexFormat.of().parseHex("89504E470D0A1A0A");
}
