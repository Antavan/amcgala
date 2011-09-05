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

package cga.framework.scenegraph;

import cga.framework.scenegraph.visitor.Visitor;
import cga.framework.shape.Shape;

/**
 * Szenengraph des Frameworks.
 */
public class SceneGraph {

  private Node root = new Node("root");

  public void addNode(Node node) {
    root.addChild(node);
  }

  public void addNode(Node node, String label) {
    root.findNode(label).addChild(node);
  }

  public void removeNode(Node node) {
    root.removeNode(node.label);
  }

  public Node findNode(String label) {
    return root.findNode(label);
  }

  public void addGeometry(String label, Shape renderable) {
    root.addShape(label, renderable);
  }

  public void addGeometry(Shape renderable) {
    addGeometry("root", renderable);
  }

  public void accept(Visitor visitor) {
    root.accept(visitor);
  }

  public void addTransformation(Transformation transformation) {
    root.setTransformation(transformation);
  }
}
