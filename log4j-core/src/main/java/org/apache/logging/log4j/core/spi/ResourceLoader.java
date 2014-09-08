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

/**
 * Strategy interface for loading {@link Resource Resources}.
 *
 * @since 2.1
 */
public interface ResourceLoader {

    /**
     * Returns a {@link Resource} handle for the specified resource location.
     *
     * @param location the resource location
     * @return a corresponding Resource handle (not {@code null})
     */
    Resource getResource(String location);

    /**
     * Returns the {@link ClassLoader} used by this ResourceLoader.
     *
     * @return the underlying ClassLoader (not {@code null})
     */
    ClassLoader getClassLoader();
}
