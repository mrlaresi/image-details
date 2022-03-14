package fi.laresi.image_details;

import java.nio.ByteBuffer;

/**
 * Helper Class that stores the results of a chunk read operation
 */
public class ByteResult {
	private final int readCount;
	private final ByteBuffer readBytes;

	/**
	 * Constructor
	 * @param readCount How many bytes were read
	 * @param readBytes inside a ByteBuffer
	 */
	public ByteResult(int readCount, ByteBuffer readBytes) {
		this.readCount = readCount;
		this.readBytes = readBytes;
	}

	public int getReadCount() {
		return readCount;
	}

	public ByteBuffer getReadBytes() {
		return readBytes;
	}
}
