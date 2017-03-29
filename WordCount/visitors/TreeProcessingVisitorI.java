/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCount.visitors;

import WordCount.treesForStrings.BaseTree;

/**
 *
 * @author Girish
 */
public interface TreeProcessingVisitorI {
    public void visit(BaseTree tree);
}
