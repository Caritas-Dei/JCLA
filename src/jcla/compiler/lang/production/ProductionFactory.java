package jcla.compiler.lang.production;

import jcla.compiler.token.Token;

/**
 * @author link
 */
public interface ProductionFactory {

	Production create(Token[] tokens);

}
