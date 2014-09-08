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

package org.apache.logging.log4j.core.spi;

import java.io.IOException;
import java.io.InputStream;

/**
 * Simple interface for producing an {@link InputStream}.
 *
 * @see Resource
 * @since 2.1
 */
public interface InputStreamSource {

    /**
     * Returns an {@link InputStream}. Implementations of this interface are expected to provide a <em>fresh</em>
     * InputStream for each invocation.
     *
     * @return the input stream for the underlying resource (not {@code null}
     * @throws IOException if the input stream could not be opened
     */
    InputStream getInputStream() throws IOException;
}
