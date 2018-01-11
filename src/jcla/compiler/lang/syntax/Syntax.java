package jcla.compiler.lang.syntax;

import jcla.compiler.lang.grammar.Grammar;
import jcla.compiler.lang.production.ProductionFactory;
import jcla.util.data.tree.HashTree;
import jcla.util.data.tree.HashTree.Navigator;
import jcla.util.data.tree.HashTree.Node;

import java.util.HashMap;
import java.util.Map;

import static jcla.compiler.lang.grammar.Grammar.*;

/**
 * @author link
 */
public class Syntax {

	protected final Map<Grammar, HashTree<Grammar, ProductionFactory>> syntax;

	private Syntax(int grammars) {
		syntax = new HashMap<>(grammars);
	}

	public HashTree.Navigator first(Grammar grammar) {
		return syntax.get(grammar).nagivator();
	}

	@SuppressWarnings("unchecked")
	private static final class JavaSyntax extends Syntax {

		private static final int JAVA_GRAMMARS = 292;

		private JavaSyntax() {
			super(JAVA_GRAMMARS);

			// possible tokens
			syntax.put(IDENTIFIER, new HashTree<>());
			syntax.put(KEYWORD, new HashTree<>());
			syntax.put(LITERAL, new HashTree<>());
			syntax.put(SEPARATOR, new HashTree<>());
			syntax.put(OPERATOR, new HashTree<>());

			// the navigator for each token
			final Navigator identifier = syntax.get(IDENTIFIER).nagivator();
			final Navigator keyword    = syntax.get(KEYWORD).nagivator();
			final Navigator literal    = syntax.get(LITERAL).nagivator();
			final Navigator separator  = syntax.get(SEPARATOR).nagivator();
			final Navigator operator   = syntax.get(OPERATOR).nagivator();

			final Node<Grammar, ProductionFactory> identifierRoot = identifier.getNode();
			final Node<Grammar, ProductionFactory> keywordRoot = keyword.getNode();
			final Node<Grammar, ProductionFactory> literalRoot = literal.getNode();
			final Node<Grammar, ProductionFactory> separatorRoot = separator.getNode();
			final Node<Grammar, ProductionFactory> operatorRoot = operator.getNode();


			// Annotations (Chapter 9)

			// NormalAnnotation:
			//      @ TypeName ( [ElementValuePairList] )
			//
			// MarkerAnnotation:
			//      @ TypeName
			//
			// SingleElementAnnotation:
			//      @ TypeName ( ElementValue )
			separator.getNode().setChild(IDENTIFIER, Node.create((tokens) -> {
				// parse tokens
				if (tokens[0].getSymbol().equals("@")) {

				}
			}, identifierRoot));
			separator.navigate(IDENTIFIER);
		}
	}

}
