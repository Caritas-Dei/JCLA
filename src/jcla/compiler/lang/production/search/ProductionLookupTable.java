/**
 * Copyright (c) 2018 Andrew Porter.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * <p>
 * This file is part of JCLassAssistant
 * <p>
 * Created on 3/15/2018 at 3:19 PM
 */

package jcla.compiler.lang.production.search;

import jcla.compiler.lang.production.Production;
import jcla.compiler.lang.production.token.Token;

import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Andrew Porter
 */
public class ProductionLookupTable {


	private final Node[] roots = new Node[5];

	public ProductionLookupTable() {

	}

	public void add(Production production) {

	}

	public Navigator navigator() {
		return new Navigator(this);
	}


	class Node {

		protected final Node parent;
		protected final Node[] branches = new Node[5];
		protected final Function<Token[], Production> builder;

		Node(Node parent, Function<Token[], Production> builder) {
			this.parent = parent;
			this.builder = builder;
		}

		Node getBranch(int index) {
			return branches[index];
		}

		void setBranch(int index, Node node) {
			branches[index] = node;
		}

		Production production(Token[] tokens) {
			return builder.apply(tokens);
		}

	}

	public static class Navigator {

		private ProductionLookupTable table;
		private Node current;

		public Navigator(ProductionLookupTable table) {
			this.table = table;
		}

		public Navigator(ProductionLookupTable table, int[] address) {
			this.table = table;
			for (int i : address) {
				current = table.roots[address[i]];
			}
		}

		public Navigator next(int index) {
			current = current.getBranch(index);
			return this;
		}

		public Navigator next(Token token) {
			return next(token.getID());
		}

		public Navigator jump(int... address) {
			for (int index : address) {
				current = current.getBranch(index);
			}

			return this;
		}

		/**
		 * Jumps by Token directly (using {@link Token#getID()}).
		 *
		 * @param address the array of Tokens that points to the correct Production.
		 * @return a Navigator for navigating this ProductionLookupTable
		 */
		public Navigator jump(Token... address) {
			for (Token index : address) {
				current = current.getBranch(index.getID());
			}

			return this;
		}

		public void create(Production production, Function<Token[], Production> builder) {
		}


	}

}
