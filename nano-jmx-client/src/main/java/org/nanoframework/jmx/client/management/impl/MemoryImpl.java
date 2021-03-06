/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nanoframework.jmx.client.management.impl;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

import javax.management.ObjectName;

import org.nanoframework.jmx.client.JmxClient;
import org.nanoframework.jmx.client.management.AbstractMXBean;
import org.nanoframework.jmx.client.management.MemoryMXBean;

/**
 * 
 * @author yanghe
 * @since 1.1
 */
public class MemoryImpl extends AbstractMXBean implements MemoryMXBean {
	public static final String OBJECT_NAME = ManagementFactory.MEMORY_MXBEAN_NAME;
	public static final String OBJECT_PENDING_FINALIZATION_COUNT = "ObjectPendingFinalizationCount";
	public static final String HEAP_MEMORY_USAGE = "HeapMemoryUsage";
	public static final String NON_HEAP_MEMORY_USAGE = "NonHeapMemoryUsage";
	public static final String VERBOSE = "Verbose";
	public static final String GC = "gc";
	
	public MemoryImpl(JmxClient client) {
		init(client, OBJECT_NAME);
	}
	
	public MemoryImpl(JmxClient client, ObjectName objectName) {
		init(client, objectName);
	}
	
	@Override
	public ObjectName getObjectName() {
		return objectName;
	}

	@Override
	public int getObjectPendingFinalizationCount() {
		return getAttribute(OBJECT_PENDING_FINALIZATION_COUNT);
	}

	@Override
	public MemoryUsage getHeapMemoryUsage() {
		return MemoryUsage.from(getAttribute(HEAP_MEMORY_USAGE));
	}

	@Override
	public MemoryUsage getNonHeapMemoryUsage() {
		return MemoryUsage.from(getAttribute(NON_HEAP_MEMORY_USAGE));
	}

	@Override
	public boolean isVerbose() {
		return getAttribute(VERBOSE);
	}

	@Override
	public void setVerbose(boolean value) {
		setAttribute(VERBOSE, value);
	}

	@Override
	public void gc() {
		invoke(GC);
	}

}
