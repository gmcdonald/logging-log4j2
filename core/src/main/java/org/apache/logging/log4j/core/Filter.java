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

package org.apache.logging.log4j.core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;

/**
 * Interface that must be implemented to allow custom event filtering. It is highly recommended that
 * applications make use of the Filters provided with this implementation before creating their own.
 *
 * This interface supports "global" filters (i.e. - all events must pass through them first), attached to
 * specific loggers and associated with Appenders. It is recommended that, where possible, Filter implementations
 * create a generic filtering method that can be called from any of the filter methods.
 */
public interface Filter {

    /**
     * The result that can returned from a filter method call.
     */
    public enum Result {
        /**
         * The event will be processed without further filtering based on the log Level.
         */
        ACCEPT,
        /**
         * No decision could be made, further filtering should occur.
         */
        NEUTRAL,
        /**
         * The event should not be processed.
         */
        DENY
    }

    /**
     * Return the result that should be returned when the filter does not match the event.
     * @return the Result that should be returned when the filter does not match the event.
     */
    Result getOnMismatch();
    /**
     * Return the result that should be returned when the filter matches the event.
     * @return the Result that should be returned when the filter matches the event.
     */
    Result getOnMatch();

    /**
     * Filter an event.
     * @param logger The Logger.
     * @param level The event logging Level.
     * @param marker The Marker for the event or null.
     * @param msg String text to filter on.
     * @param params An array of parameters or null.
     * @return the Result.
     */
    Result filter(Logger logger, Level level, Marker marker, String msg, Object... params);

    /**
     * Filter an event.
     * @param logger The Logger.
     * @param level The event logging Level.
     * @param marker The Marker for the event or null.
     * @param msg Any Object.
     * @param t A Throwable or null.
     * @return the Result.
     */
    Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t);

    /**
     * Filter an event.
     * @param logger The Logger.
     * @param level The event logging Level.
     * @param marker The Marker for the event or null.
     * @param msg The Message
     * @param t A Throwable or null.
     * @return the Result.
     */
    Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t);

    /**
     * Filter an event.
     * @param event The Event to filter on.
     * @return the Result.
     */
    Result filter(LogEvent event);

}