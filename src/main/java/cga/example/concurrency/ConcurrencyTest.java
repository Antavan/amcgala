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
package cga.example.concurrency;

import cga.Framework;
import java.util.logging.Logger;

/**
 * Ein Testbeispiel, um mögliche Probleme bei nicht-synchronisierten Zugriffen
 * auf den Szenengraph zu evaluieren.
 *
 * @author Robert Giacinto
 */
public class ConcurrencyTest extends Framework {

    private RandomCrossArray crosses;

    public ConcurrencyTest(int width, int height) {
        super(width, height);

    }

    @Override
    public void initGraph() {
        crosses = new RandomCrossArray();
        registerInputEventHandler(crosses);
        add(crosses);
    }

    public static void main(String[] args) {
        Framework test = new ConcurrencyTest(800, 600);
        test.start();

    }
    private static final Logger LOG = Logger.getLogger(ConcurrencyTest.class.getName());
}
