package fi.laresi.image_details;
import java.io.IOError;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class Filer {
	private Path filepath;
	private SeekableByteChannel byteChannel;

	/**
	 * Normalizes given filepath and returns it if required permissions are
	 * available
	 * @param url path to the file
	 * @return normalized filepath
	 */
	private Path normalizePath(Path url) {
		try {
			return url.toAbsolutePath().normalize();
		} catch (IOError io) {
			return null;
		} catch (SecurityException sec) {
			return null;
		}
	}


	/**
	 * Loads a file for reading bytes
	 * @param url filepath to file as a string
	 * @throws IOException on read failure
	 */
	public void loadFile(String url) throws IOException {
		filepath = normalizePath(Path.of(url));
		if (Files.notExists(filepath, LinkOption.NOFOLLOW_LINKS)) {
			throw new IOException(String.format(
				"Given filepath '%s' does not exist", url));
		}

		byteChannel = Files.newByteChannel(filepath);
	}


	/**
	 * Closes the opened file
	 * @throws IOException
	 */
	public void closeFile() throws IOException {
		byteChannel.close();
	}

	/**
	 * Reads given amount of bytes from the bytechannel and returns the result
	 * of the read operation
	 * @see #ByteResult
	 * @param byteCount how many bytes will be read
	 * @return containing results of the read operation
	 * @throws IOException if an error happens
	 */
	public ByteResult readBytes(int byteCount) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(byteCount);
		int read = byteChannel.read(buffer);
		return new ByteResult(read, buffer);
	}

	/**
	 * Moves the bytechannel cursor forwards
	 * @param byteCount how many bytes the cursor is moved
	 * @throws IOException if an error happens
	 */
	public void skipBytes(int byteCount) throws IOException{
		byteChannel.position(byteChannel.position() + byteCount);
	}
}
