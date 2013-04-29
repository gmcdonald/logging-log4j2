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
package org.apache.logging.log4j.core.jmx;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * The MBean interface for monitoring and managing a {@code LoggerContext}.
 */
public interface LoggerContextAdminMBean {
    /** ObjectName pattern for LoggerContextAdmin MBeans. */
    String PATTERN = "org.apache.logging.log4j2:type=LoggerContext,ctx=%s";

    /**
     * Notification that the {@code Configuration} of the instrumented
     * {@code LoggerContext} has been reconfigured. Notifications of this type
     * do not carry a message or user data.
     */
    String NOTIF_TYPE_RECONFIGURED = "com.apache.logging.log4j.core.jmx.config.reconfigured";

    /**
     * Returns the status of the instrumented {@code LoggerContext}.
     * 
     * @return the LoggerContext status.
     */
    String getStatus();

    /**
     * Returns the name of the instrumented {@code LoggerContext}.
     * 
     * @return the name of the instrumented {@code LoggerContext}.
     */
    String getName();

    /**
     * Returns the configuration location URI as a String.
     * 
     * @return the configuration location
     */
    String getConfigLocationURI();

    /**
     * Sets the configuration location to the specified URI. This will cause the
     * instrumented {@code LoggerContext} to reconfigure.
     * 
     * @param configLocation location of the configuration file in
     *            {@link java.net.URI} format.
     * @throws URISyntaxException if the format of the specified
     *             configLocationURI is incorrect
     * @throws IOException if an error occurred reading the specified location
     */
    void setConfigLocationURI(String configLocation) throws URISyntaxException,
            IOException;

    /**
     * Returns the configuration text, which may be the contents of the
     * configuration file or the text that was last set with a call to
     * {@code setConfigText}.
     * 
     * @return the configuration text
     * @throws IOException if a problem occurred reading the contents of the
     *             config file.
     */
    String getConfigText() throws IOException;

    /**
     * Sets the configuration text. This does not replace the contents of the
     * configuration file, but <em>does</em> cause the instrumented
     * {@code LoggerContext} to be reconfigured with the specified text.
     * 
     * @param configText the configuration text in XML or JSON format
     * @param charsetName name of the {@code Charset} used to convert the
     *            specified configText to bytes
     * @throws IllegalArgumentException if a problem occurs configuring from the
     *             specified text
     */
    void setConfigText(String configText, String charsetName);

    /**
     * Returns the name of the Configuration of the instrumented LoggerContext.
     * 
     * @return the Configuration name
     */
    String getConfigName();

    /**
     * Returns the class name of the {@code Configuration} of the instrumented
     * LoggerContext.
     * 
     * @return the class name of the {@code Configuration}.
     */
    String getConfigClassName();

    /**
     * Returns a string description of all Filters configured in the
     * {@code Configuration} of the instrumented LoggerContext.
     * 
     * @return a string description of all Filters configured
     */
    String getConfigFilter();

    /**
     * Returns the class name of the object that is monitoring the configuration
     * file for modifications.
     * 
     * @return the class name of the object that is monitoring the configuration
     *         file for modifications
     */
    String getConfigMonitorClassName();

    /**
     * Returns a map with configured properties.
     * 
     * @return a map with configured properties.
     */
    Map<String, String> getConfigProperties();

}