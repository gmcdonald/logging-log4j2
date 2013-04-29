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

/**
 * The MBean interface for monitoring and managing a {@code LoggerConfig}.
 */
public interface LoggerConfigAdminMBean {
    /** ObjectName pattern for LoggerConfigAdmin MBeans. */
    String PATTERN = "org.apache.logging.log4j2:type=LoggerContext,ctx=%s,sub=LoggerConfig,name=%s";

    /**
     * Returns the name of the instrumented {@code LoggerConfig}.
     * 
     * @return the name of the LoggerConfig
     */
    String getName();

    /**
     * Returns the {@code LoggerConfig} level as a String.
     * 
     * @return the {@code LoggerConfig} level.
     */
    String getLevel();

    /**
     * Sets the {@code LoggerConfig} level to the specified value.
     * 
     * @param level the new {@code LoggerConfig} level.
     * @throws IllegalArgumentException if the specified level is not one of
     *             "OFF", "FATAL", "ERROR", "WARN", "INFO", "DEBUG", "TRACE",
     *             "ALL"
     */
    void setLevel(String level);

    /**
     * Returns whether the instrumented {@code LoggerConfig} is additive.
     * 
     * @return {@code true} if the LoggerConfig is additive, {@code false}
     *         otherwise
     */
    boolean isAdditive();

    /**
     * Sets whether the instrumented {@code LoggerConfig} should be additive.
     * 
     * @param additive {@code true} if the instrumented LoggerConfig should be
     *            additive, {@code false} otherwise
     */
    void setAdditive(boolean additive);

    /**
     * Returns whether the instrumented {@code LoggerConfig} is configured to
     * include location.
     * 
     * @return whether location should be passed downstream
     */
    boolean isIncludeLocation();

    /**
     * Returns a string description of all filters configured for the
     * instrumented {@code LoggerConfig}.
     * 
     * @return a string description of all configured filters for this
     *         LoggerConfig
     */
    String getFilter();

    /**
     * Returns a String array with the appender refs configured for the
     * instrumented {@code LoggerConfig}.
     * 
     * @return the appender refs for the instrumented {@code LoggerConfig}.
     */
    String[] getAppenderRefs();

}