package fi.laresi.image_details;

import static fi.laresi.image_details.Constants.PNG_HEADER;

import java.io.IOException;
import java.util.Arrays;

public class PNGDecoder {
	private int chunkCount;
	private boolean eofFound;
	private Filer fileReader = new Filer();

	/**
	 * Default constructor
	 */
	public PNGDecoder() {
	}

	/**
	 * If filepath is given to the constructor, loads the file 
	 * @param path to the file
	 */
	public PNGDecoder(String path) {
		loadFile(path);
	}

	private void badFilepath() throws IllegalArgumentException {
		throw new IllegalArgumentException("Given filepath didn't point to a PNG image.");
	}
	private void badFilepath(IOException previous) throws IllegalArgumentException {
		throw new IllegalArgumentException("Given filepath didn't point to a PNG image.", previous);
	}


	/**
	 * Loads given filepath to the memory and verifies it's a PNG image
	 * @param path to the file
	 * @throws IllegalArgumentException when filepath doesn't point to a PNG
	 */
	public void loadFile(String path) throws IllegalArgumentException {
		try {
			fileReader.loadFile(path);
			verifyHeader();
		} catch (IOException io) {
			badFilepath(io);
		}
	}

	/**
	 * Verify that the given filepath points to a PNG image
	 * @return true if PNG file, either false
	 * @throws IllegalArgumentException when file doesn't have a PNG header
	 */
	private void verifyHeader() throws IllegalArgumentException {
		try {
			ByteResult result = fileReader.readBytes(8);
			byte[] bytes = result.getReadBytes().array();
			if (!Arrays.equals(bytes, PNG_HEADER)) {
				fileReader.closeFile();
				badFilepath();
			}
		} catch (IOException io) {
			badFilepath(io);
		}
	}

	/**
	 * Reads next chunk from PNG image
	 * @throws IOException on error
	 */
	private void readNextChunk() throws IOException {
		ByteResult chunkLength = fileReader.readBytes(4);
		ByteResult chunkType = fileReader.readBytes(4);
		if (chunkType.getReadCount() < 0) {
			throw new IOException("End of file was reached before end header was found, image file is invalid.");
		}
		int length = chunkLength.getReadBytes().getInt(0);
		fileReader.skipBytes(length + 4);

		String type = new String(chunkType.getReadBytes().array());
		System.out.println(type);
		if (type.equals("IEND")) {
			eofFound = true;
		}

	}

	/**
	 * Read chunks from a PNG file as specified in RFC 2083
	 * 
	 * @param filer to read bytes from a file
	 * @return amount of chunks the file contains
	 */
	public int readChunks() {
		try {
			eofFound = false;
			while (!eofFound) {
				readNextChunk();
				chunkCount++;
			}
			
			fileReader.closeFile();
			return chunkCount;
		} catch (IOException io) {
			System.out.println(io.getMessage());
			return -1;
		}
	}
}
