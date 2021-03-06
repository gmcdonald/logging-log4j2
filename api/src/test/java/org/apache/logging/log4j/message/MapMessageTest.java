/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.apache.logging.log4j.message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class MapMessageTest {

    @Test
    public void testMap() {
        String testMsg = "Test message {}";
        MapMessage msg = new MapMessage();
        msg.put("message", testMsg);
        msg.put("project", "Log4j");
        String result = msg.getFormattedMessage();
        String expected = "message=\"Test message {}\" project=\"Log4j\"";
        assertEquals(expected, result);
    }

    @Test
    public void testXML() {
        String testMsg = "Test message {}";
        MapMessage msg = new MapMessage();
        msg.put("message", testMsg);
        msg.put("project", "Log4j");
        String result = msg.getFormattedMessage(new String[]{"XML"});
        String expected = "<Map>\n  <Entry key=message>Test message {}</Entry>\n" +
            "  <Entry key=project>Log4j</Entry>\n" +
            "</Map>";
        assertEquals(expected, result);
    }

    @Test
    public void testJSON() {
        String testMsg = "Test message {}";
        MapMessage msg = new MapMessage();
        msg.put("message", testMsg);
        msg.put("project", "Log4j");
        String result = msg.getFormattedMessage(new String[]{"JSON"});
        String expected = "{\"message\":\"Test message {}\", \"project\":\"Log4j\"}";
        assertEquals(expected, result);
    }

    @Test
    public void testJava() {
        String testMsg = "Test message {}";
        MapMessage msg = new MapMessage();
        msg.put("message", testMsg);
        msg.put("project", "Log4j");
        String result = msg.getFormattedMessage(new String[]{"Java"});
        String expected = "{message=\"Test message {}\", project=\"Log4j\"}";
        assertEquals(expected, result);
    }
}
