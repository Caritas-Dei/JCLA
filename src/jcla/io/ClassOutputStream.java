package jcla.io;

import jcla.jar.JavaArchive;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author link
 */
public class ClassOutputStream extends OutputStream implements AutoCloseable {

	/**
	 * Creates a new ClassOutputStream to the class with {@code className} located in the given {@code jar}.
	 *
	 * @param jar the jar to write into
	 * @param className the name of the class file
	 */
	public ClassOutputStream(JavaArchive jar, String className) throws IOException {

	}

	@Override
	public void write(int b) throws IOException {

	}
}
