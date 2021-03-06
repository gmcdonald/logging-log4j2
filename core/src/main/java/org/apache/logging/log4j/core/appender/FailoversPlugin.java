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
package org.apache.logging.log4j.core.appender;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.status.StatusLogger;

/**
 * The array of failover Appenders.
 */
@Plugin(name = "failovers", type = "Core")
public final class FailoversPlugin {

    private static Logger logger = StatusLogger.getLogger();

    /**
     * Prevent instantiation.
     */
    private FailoversPlugin() {
    }

    /**
     * Return the appender references.
     * @param refs The references to return.
     * @return The appender references.
     */
    @PluginFactory
    public static String[] createFailovers(@PluginElement("appender-ref") AppenderRef[] refs) {

        if (refs == null) {
            logger.error("failovers must contain an appender-reference");
            return null;
        }
        String[] arr = new String[refs.length];
        for (int i = 0; i < refs.length; ++i) {
            arr[i] = refs[i].getRef();
        }
        return arr;
    }
}
