package fi.laresi.image_details;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static fi.laresi.image_details.Constants.IHDR;
import static fi.laresi.image_details.Constants.IEND;

/**
 * Object that represents single chunk of data in a PNG image
 */
public class Chunk {
	private final ByteBuffer dataLength;
	private final ByteBuffer type;
	private final ByteBuffer data;
	/** Chunk header in human friendly format */
	private final String typeString;
	private final boolean isHeaderChunk;
	private final boolean isEndChunk;

	/**
	 * Constructor for Chunk object where
	 * @param length ByteBuffer with details of the Chunk length
	 * @param type ByteBuffer with details of the Chunk type
	 * @param data ByteBuffer with the actual Chunk data
	 */
	public Chunk(ByteBuffer length, ByteBuffer type, ByteBuffer data) {
		this.dataLength = length;
		this.type = type;
		this.data = data;
		this.typeString = new String(type.array(), StandardCharsets.UTF_8);
		this.isHeaderChunk = Arrays.equals(type.array(), IHDR);
		this.isEndChunk = Arrays.equals(type.array(), IEND);
	}

	/**
	 * If chunk is PNG header chunk
	 * @return true
	 */
	public boolean isHeader() {
		return isHeaderChunk;
	}

	/**
	 * If chunk is PNG end chunk
	 * @return true
	 */
	public boolean isEnd() {
		return isEndChunk;
	}

	@Override
	public String toString() {
		return typeString;
	}
}
