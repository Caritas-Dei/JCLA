package jcla.jar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.jar.JarFile;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * @author link
 */
public class JavaArchive extends ZipFile {

	private final JarFile jarFile;
	private final File file;

	public JavaArchive(Path archive) throws ZipException, IOException {
		this(archive.toFile());
	}

	public JavaArchive(File file) throws ZipException, IOException {
		super(file);
		jarFile = new JarFile(file, true, OPEN_READ | OPEN_DELETE);
		this.file = file;
	}

	/**
	 * Gets the class file entry with the given fully qualified class name, also known as a class's {@linkplain ClassLoader binary name}.
	 * <p>
	 * A fully qualified class name is a dot-delimited String with a class name preceded by its package, for example:
	 * <pre>java.lang.String</pre><br>
	 * is a valid, fully qualified class name.
	 * </p>
	 * <p>
	 * This method is effectively equivalent to the call, {@code getInputStream(getEntry(fullyQualifiedClassName.replace(".","/")))}.
	 * </p>
	 *
	 * @param fullyQualifiedClassName the fully qualified class name of a class presumed to be in this JavaArchive
	 * @return an InputStream to access the class file (with .class extension)
	 */
	public InputStream getClass(String fullyQualifiedClassName) throws IOException {
		return getInputStream(getEntry(fullyQualifiedClassName.replace('.', '/')));
	}

	/**
	 * Gets the JarFile for this JavaArchive.
	 *
	 * @return the JarFile for this JavaArchive
	 */
	public JarFile getJarFile() {
		return jarFile;
	}

	public File getFile() {
		return file;
	}

}
