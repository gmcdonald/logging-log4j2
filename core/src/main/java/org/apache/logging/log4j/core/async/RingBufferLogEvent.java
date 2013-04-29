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
package org.apache.logging.log4j.core.async;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext.ContextStack;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;

import com.lmax.disruptor.EventFactory;

/**
 * When the Disruptor is started, the RingBuffer is populated with event
 * objects. These objects are then re-used during the life of the RingBuffer.
 */
public class RingBufferLogEvent implements LogEvent {
    private static final long serialVersionUID = 8462119088943934758L;

    /**
     * Creates the events that will be put in the RingBuffer.
     */
    private static class Factory implements EventFactory<RingBufferLogEvent> {
        // @Override
        public RingBufferLogEvent newInstance() {
            return new RingBufferLogEvent();
        }
    }

    /** The {@code EventFactory} for {@code RingBufferLogEvent}s. */
    public static final Factory FACTORY = new Factory();

    private AsyncLogger asyncLogger;
    private String loggerName;
    private Marker marker;
    private String fqcn;
    private Level level;
    private Message message;
    private Throwable thrown;
    private Map<String, String> contextMap;
    private ContextStack contextStack;
    private String threadName;
    private StackTraceElement location;
    private long currentTimeMillis;
    private boolean endOfBatch;
    private boolean includeLocation;

    public void setValues(AsyncLogger asyncLogger, String loggerName,
            Marker marker, String fqcn, Level level, Message data, Throwable t,
            Map<String, String> map, ContextStack contextStack,
            String threadName, StackTraceElement location,
            long currentTimeMillis) {
        this.asyncLogger = asyncLogger;
        this.loggerName = loggerName;
        this.marker = marker;
        this.fqcn = fqcn;
        this.level = level;
        this.message = data;
        this.thrown = t;
        this.contextMap = map;
        this.contextStack = contextStack;
        this.threadName = threadName;
        this.location = location;
        this.currentTimeMillis = currentTimeMillis;
    }

    /**
     * Event processor that reads the event from the ringbuffer can call this
     * method.
     * 
     * @param endOfBatch flag to indicate if this is the last event in a batch
     *            from the RingBuffer
     */
    public void execute(boolean endOfBatch) {
        this.endOfBatch = endOfBatch;
        asyncLogger.actualAsyncLog(this);
    }

    /**
     * Returns {@code true} if this event is the end of a batch, {@code false}
     * otherwise.
     * 
     * @return {@code true} if this event is the end of a batch, {@code false}
     *         otherwise
     */
    public boolean isEndOfBatch() {
        return endOfBatch;
    }

    public void setEndOfBatch(boolean endOfBatch) {
        this.endOfBatch = endOfBatch;
    }

    public boolean isIncludeLocation() {
        return includeLocation;
    }

    public void setIncludeLocation(boolean includeLocation) {
        this.includeLocation = includeLocation;
    }

    // @Override
    public String getLoggerName() {
        return loggerName;
    }

    // @Override
    public Marker getMarker() {
        return marker;
    }

    // @Override
    public String getFQCN() {
        return fqcn;
    }

    // @Override
    public Level getLevel() {
        return level;
    }

    // @Override
    public Message getMessage() {
        if (message == null) {
            message = new SimpleMessage("");
        }
        return message;
    }

    // @Override
    public Throwable getThrown() {
        return thrown;
    }

    // @Override
    public Map<String, String> getContextMap() {
        return contextMap;
    }

    // @Override
    public ContextStack getContextStack() {
        return contextStack;
    }

    // @Override
    public String getThreadName() {
        return threadName;
    }

    // @Override
    public StackTraceElement getSource() {
        return location;
    }

    // @Override
    public long getMillis() {
        return currentTimeMillis;
    }

    /**
     * Merges the contents of the specified map into the contextMap, after
     * replacing any variables in the property values with the
     * StrSubstitutor-supplied actual values.
     * 
     * @param properties configured properties
     * @param strSubstitutor used to lookup values of variables in properties
     */
    public void mergePropertiesIntoContextMap(
            Map<Property, Boolean> properties, StrSubstitutor strSubstitutor) {
        if (properties == null) {
            return; // nothing to do
        }

        Map<String, String> map = (contextMap == null) ? new HashMap<String, String>()
                : new HashMap<String, String>(contextMap);

        for (Map.Entry<Property, Boolean> entry : properties.entrySet()) {
            Property prop = entry.getKey();
            if (map.containsKey(prop.getName())) {
                continue; // contextMap overrides config properties
            }
            String value = entry.getValue() ? strSubstitutor.replace(prop
                    .getValue()) : prop.getValue();
            map.put(prop.getName(), value);
        }
        contextMap = map;
    }
}