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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Interface to abstract a resource handle. Similar to the standard {@link URL} class, but allows for more flexibility
 * for obtaining resource data from sources such as a ClassLoader or ServletContext. Inspired by the IO classes in
 * <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/resources.html">Spring Core</a>.
 * Unlike the Spring version of this class, all resources <em>must</em> be describable by a {@link URI}.
 *
 * @since 2.1
 */
public interface Resource extends InputStreamSource {

    /**
     * Indicates if this Resource has a physical existence. This allows for resources to be described without actually
     * existing (similar to {@link File}).
     *
     * @return {@code true} if this Resource has a physical existence, or {@code false} otherwise
     */
    boolean exists();

    /**
     * Indicates if this Resource can be read. Naturally, if this Resource does not exist, this method must return
     * {@code false}.
     *
     * @return {@code true} if and only if this Resource exists and can be read
     */
    boolean isReadable();

    /**
     * Indicates if this Resource represents a handle with an open stream. If {@code true}, then this indicates that
     * the {@link java.io.InputStream} read from this Resource cannot be read multiple times and must be read and
     * closed to prevent resource leaks. Most implementations will return {@code false} to indicate that their
     * InputStreams can be read multiple times.
     *
     * @return {@code true} if the underlying resource InputStream is open or {@code false} if the InputStream can be
     * read multiple times
     */
    boolean isOpen();

    /**
     * Returns a {@link URL} handle for this Resource.
     *
     * @return a URL for this Resource
     * @throws IOException if this Resource cannot be resolved as a URL
     */
    URL getURL() throws IOException;

    /**
     * Returns a {@link URI} handle for this Resource. All Resource implementations must support having a URI
     * representation in order to properly integrate with the Log4j API.
     *
     * @return a URI for this Resource
     * @see org.apache.logging.log4j.spi.LoggerContextFactory#getContext(String, ClassLoader, Object, boolean, java.net.URI, String)
     */
    URI getURI();

    /**
     * Returns a File handle for this Resource.
     *
     * @return a File for this Resource
     * @throws IOException if this Resource is not available as a File
     */
    File getFile() throws IOException;

    /**
     * Returns the number of bytes that this Resource takes up.
     *
     * @return the length of this Resource in bytes
     * @throws IOException if this Resource cannot be resolved
     * @see java.io.File#length()
     */
    long contentLength() throws IOException;

    /**
     * Returns the timestamp (in milliseconds) that this Resource was last modified.
     *
     * @return the time that this Resource was last modified
     * @throws IOException if this Resource cannot be resolved
     * @see java.io.File#lastModified()
     */
    long lastModified() throws IOException;

    /**
     * Returns the file name of this Resource. This is typically the final part of a pathname (e.g., "foo.xml").
     *
     * @return the file name of this Resource or {@code null} if this Resource does not have a file name
     */
    String getFilename();

    /**
     * Returns a description of this Resource to be used in error messages.
     *
     * @return a description of this Resource
     */
    String getDescription();
}
