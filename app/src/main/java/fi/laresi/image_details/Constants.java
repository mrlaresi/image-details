package fi.laresi.image_details;

import java.util.HexFormat;

/**
 * Class containing constant values utilized by the application
 */
public final class Constants {
	private Constants() {}
	
	/** PNG Image header, byte representation */
	public static final byte[] PNG_HEADER = 
		HexFormat.of().parseHex("89504E470D0A1A0A");
	
	/** Image header chunk, byte representation */
	public static final byte[] IHDR = 
		HexFormat.of().parseHex("49484452");
	
	/** End-of-file chunk, byte representation */
	public static final byte[] IEND = 
		HexFormat.of().parseHex("49454E44");
}
