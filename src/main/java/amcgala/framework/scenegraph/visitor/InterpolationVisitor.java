/* 
 * Copyright 2011 Cologne University of Applied Sciences Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package amcgala.framework.scenegraph.visitor;

import amcgala.framework.scenegraph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ein AlphaVisitor besucht in der Updatephase des Scenegraphs jeden Knoten und
 * aktualisiert die dort verwendeten Alphaobjekte.
 *
 * @author Robert Giacinto
 */
public class InterpolationVisitor implements Visitor {
    private static Logger log = LoggerFactory.getLogger(InterpolationVisitor.class);
    @Override
    public void visit(Node node) {
        if (node.getTransformation() != null) {
            node.getTransformation().update();
        }
    }
}
