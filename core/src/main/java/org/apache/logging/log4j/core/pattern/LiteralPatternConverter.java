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
package org.apache.logging.log4j.core.pattern;

import org.apache.logging.log4j.core.LogEvent;


/**
 * Formats a string literal.
 */
public final class LiteralPatternConverter extends LogEventPatternConverter implements ArrayPatternConverter {
    /**
     * String literal.
     */
    private final String literal;

    /**
     * Create a new instance.
     *
     * @param literal string literal.
     */
    public LiteralPatternConverter(final String literal) {
        super("Literal", "literal");
        this.literal = literal;
    }

    /**
     * {@inheritDoc}
     */
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        toAppendTo.append(literal);
    }
    /**
     * {@inheritDoc}
     */
    public void format(final Object obj, final StringBuilder output) {
        output.append(literal);
    }

    /**
     * {@inheritDoc}
     */
    public void format(Object[] objects, final StringBuilder output) {
        output.append(literal);
    }
}
