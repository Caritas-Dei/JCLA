package jcla.compiler.token.tag;

/**
 * @author link
 */
public final class Tag {

	private final String   name;

	public Tag(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
