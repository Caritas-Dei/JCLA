package jcla.compiler.lang.production;

import jcla.compiler.token.hint.Hint;

import java.util.Arrays;
import java.util.List;

/**
 * A Production is a simple pattern or template made of constituents which can be basic or complex Productions.
 *
 * @author link
 */
public class Production {

	private final List<Production> elements;
	private final List<Hint>           hints;

	/**
	 * Creates a new Production with the given Productions as constituents.
	 *
	 * @param elements the constituents of this Production
	 */
	public Production(Production... elements) {
		this.elements = Arrays.asList(elements);
		hints = List.of();
	}

	public Production(List<Production> elements, List<Hint> hints) {
		this.elements = elements;
		this.hints = hints;
	}

	/**
	 * Creates a new Production with a single Production as its constituent.
	 *
	 * @param production the Production that defines this Production
	 */
	public Production(Production production) {
		this(new Production[]{ production });
	}


	/**
	 * Gets the list of Productions that are the elements which constitute this Production. The elements may consists of
	 * either concrete Symbols or other Productions.
	 *
	 * @return the list of Productions that are the elements which constitute this Production
	 */
	public List<Production> getElements() {
		return elements;
	}

	/**
	 * Gets the list of Hints that are attributed to this Production. Each Hint added to a Production increases the
	 * specificity of the Production.
	 *
	 * @return the list of Hint that are attributed to this Production.
	 */
	public List<Hint> getHints() { return hints; }

	public class Symbol<T> extends Production {

		protected final T symbol;

		public Symbol(T symbol) {
			this.symbol = symbol;
		}

		public T value() {
			return symbol;
		}

		@Override
		public boolean equals(Object other) {
			return this == other || (other.getClass().equals(Symbol.class) && ((Symbol) other).symbol.equals(symbol));
		}

		@Override
		public String toString() {
			return "Production[\"" + symbol + "\"]";
		}

	}

	public class Array<T> extends Symbol<T> {

		public Array(T element) {
			super(element);
		}

		@Override
		public boolean equals(Object other) {
			return this == other || (other instanceof Array && ((Array) other).symbol.equals(symbol));
		}

		@Override
		public String toString() {
			return "Production.Array[\"" + symbol + "\"]";
		}

	}

}
