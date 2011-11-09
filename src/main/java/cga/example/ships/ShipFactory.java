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
package cga.example.ships;

/**
 * Ein Schiff auf dem Spielfeld. 
 * Im Spiel sind Schiffe der Größe 2,3,4 und 5 vorgesehen.
 * @author Robert Giacinto
 */
public class ShipFactory {
    
   public static void createShip2(Orientation orientation, BoardCell[][] board, int x, int y){
       if(x > board.length-1 || 
               y > board.length - 1||
               x < 0 || 
               y < 0 ||
               orientation.equals(Orientation.HORIZONTAL) && x + 2 > board.length-1 ||
               orientation.equals(Orientation.VERTICAL) && y + 2 > board[0].length-1){
           throw new IllegalArgumentException("Ungültige Position des Schiffs!");
       }else{
           
       }
   }

}
