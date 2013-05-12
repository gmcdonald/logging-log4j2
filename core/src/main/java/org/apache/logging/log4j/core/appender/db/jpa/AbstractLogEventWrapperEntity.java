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
package org.apache.logging.log4j.core.appender.db.jpa;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.message.Message;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Map;

/**
 * Users of the JPA appender MUST extend this class, using JPA annotations on the concrete class and all of its
 * accessor methods (as needed) to map them to the proper table and columns. Accessors you do not want persisted should
 * be annotated with {@link Transient @Transient}. All accessors should call {@link #getWrappedEvent()} and delegate the
 * call to the underlying event. Users may want to instead extend {@link LogEventEntity}, which takes care of all of
 * this for you.<br>
 * <br>
 * The concrete class must have two constructors: a public no-arg constructor to convince the JPA provider that it's a
 * valid entity, and a public constructor that takes a single {@link LogEvent event} and passes it to the parent class
 * with {@link #AbstractLogEventWrapperEntity(LogEvent) super(event)}. Furthermore, the concrete class must be annotated
 * {@link javax.persistence.Entity @Entity} and {@link javax.persistence.Table @Table} and must implement a fully
 * mutable ID property annotated with {@link javax.persistence.Id @Id} and
 * {@link javax.persistence.GeneratedValue @GeneratedValue} to tell the JPA provider how to calculate an ID for new
 * events.<br>
 * <br>
 * Many of the return types of {@link LogEvent} methods (e.g., {@link StackTraceElement}, {@link Message},
 * {@link Marker}, {@link Throwable}, {@link ThreadContext.ContextStack}, and {@link Map Map&lt;String, String&gt}) will
 * not be recognized by the JPA provider. In conjunction with {@link javax.persistence.Convert @Convert}, you can use
 * the converters in the {@link org.apache.logging.log4j.core.appender.db.jpa.converter} package to convert these
 * types to database columns. If you want to retrieve log events from the database, you can create a true POJO entity
 * and also use these converters for extracting persisted values.<br>
 * <br>
 * The mutator methods in this class not specified in {@link LogEvent} are no-op methods, implemented to satisfy the JPA
 * requirement that accessor methods have matching mutator methods. If you create additional accessor methods, you must
 * likewise create matching no-op mutator methods.
 *
 * @see LogEventEntity
 */
@MappedSuperclass
public abstract class AbstractLogEventWrapperEntity implements LogEvent {
    /**
     * Generated serial version ID.
     */
    private static final long serialVersionUID = 1L;
    private final LogEvent wrappedEvent;

    @SuppressWarnings("unused") // JPA requires this
    protected AbstractLogEventWrapperEntity() {
        this(null);
    }

    /**
     * Instantiates the base class. All concrete implementations must have two constructors: a no-arg constructor that
     * calls this constructor with a null argument, and a constructor matching this constructor's signature.
     * 
     * @param wrappedEvent
     *            The underlying event from which information is obtained.
     */
    protected AbstractLogEventWrapperEntity(final LogEvent wrappedEvent) {
        this.wrappedEvent = wrappedEvent;
    }

    /**
     * All eventual accessor methods must call this method and delegate the method call to the underlying wrapped event.
     * 
     * @return The underlying event from which information is obtained.
     */
    @Transient
    protected final LogEvent getWrappedEvent() {
        return this.wrappedEvent;
    }

    /**
     * A no-op mutator to satisfy JPA requirements, as this entity is write-only.
     *
     * @param level Ignored.
     */
    @SuppressWarnings("unused")
    public void setLevel(final Level level) {
        // this entity is write-only
    }

    /**
     * A no-op mutator to satisfy JPA requirements, as this entity is write-only.
     *
     * @param loggerName Ignored.
     */
    @SuppressWarnings("unused")
    public void setLoggerName(final String loggerName) {
        // this entity is write-only
    }

    /**
     * A no-op mutator to satisfy JPA requirements, as this entity is write-only.
     *
     * @param source Ignored.
     */
    @SuppressWarnings("unused")
    public void setSource(final StackTraceElement source) {
        // this entity is write-only
    }

    /**
     * A no-op mutator to satisfy JPA requirements, as this entity is write-only.
     *
     * @param message Ignored.
     */
    @SuppressWarnings("unused")
    public void setMessage(final Message message) {
        // this entity is write-only
    }

    /**
     * A no-op mutator to satisfy JPA requirements, as this entity is write-only.
     *
     * @param marker Ignored.
     */
    @SuppressWarnings("unused")
    public void setMarker(final Marker marker) {
        // this entity is write-only
    }

    /**
     * A no-op mutator to satisfy JPA requirements, as this entity is write-only.
     *
     * @param threadName Ignored.
     */
    @SuppressWarnings("unused")
    public void setThreadName(final String threadName) {
        // this entity is write-only
    }

    /**
     * A no-op mutator to satisfy JPA requirements, as this entity is write-only.
     *
     * @param millis Ignored.
     */
    @SuppressWarnings("unused")
    public void setMillis(final long millis) {
        // this entity is write-only
    }

    /**
     * A no-op mutator to satisfy JPA requirements, as this entity is write-only.
     *
     * @param throwable Ignored.
     */
    @SuppressWarnings("unused")
    public void setThrown(final Throwable throwable) {
        // this entity is write-only
    }

    /**
     * A no-op mutator to satisfy JPA requirements, as this entity is write-only.
     *
     * @param contextMap Ignored.
     */
    @SuppressWarnings("unused")
    public void setContextMap(final Map<String, String> contextMap) {
        // this entity is write-only
    }

    /**
     * A no-op mutator to satisfy JPA requirements, as this entity is write-only.
     *
     * @param contextStack Ignored.
     */
    @SuppressWarnings("unused")
    public void setContextStack(final ThreadContext.ContextStack contextStack) {
        // this entity is write-only
    }

    /**
     * A no-op mutator to satisfy JPA requirements, as this entity is write-only.
     *
     * @param fqcn Ignored.
     */
    @SuppressWarnings("unused")
    public void setFQCN(final String fqcn) {
        // this entity is write-only
    }

    @Override
    @Transient
    public final boolean isIncludeLocation() {
        return this.getWrappedEvent().isIncludeLocation();
    }

    @Override
    @Transient
    public final void setIncludeLocation(final boolean locationRequired) {
        this.getWrappedEvent().setIncludeLocation(locationRequired);
    }

    @Override
    @Transient
    public final boolean isEndOfBatch() {
        return this.getWrappedEvent().isEndOfBatch();
    }

    @Override
    @Transient
    public final void setEndOfBatch(final boolean endOfBatch) {
        this.getWrappedEvent().setEndOfBatch(endOfBatch);
    }
}