package jcla.io;

import jcla.jar.JavaArchive;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

/**
 * @author link
 */
public class ClassInputStream extends DataInputStream {

	public ClassInputStream(JavaArchive jar, String className) throws IOException {
		super(jar.getClass(className));
	}

	public ClassInputStream(Path path) throws FileNotFoundException {
		super(new FileInputStream(path.toFile()));
		if (!path.toFile().isFile())
			throw new IllegalArgumentException("The given path is not a file: \"" + path.toString() + "\"");
	}

	public ClassInputStream(String className) {
		super(ClassLoader.getSystemResourceAsStream(className.replace('.', '/') + ".class"));
	}

	public ClassInputStream(Class<?> clazz) {
		this(clazz.getName());
	}

	/**
	 * The class file as a ByteArrayInputStream
	 *
	 * @param bytes the class file as a ByteArrayInputStream
	 */
	public ClassInputStream(ByteArrayInputStream bytes) {
		super(bytes);
	}

}
