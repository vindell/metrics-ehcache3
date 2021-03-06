/*
 * Copyright (c) 2018 (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.codahale.metrics.ehcache3;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.CacheDecoratorFactory;

import java.util.Properties;

public class InstrumentedCacheDecoratorFactory extends CacheDecoratorFactory {
	
    @Override
    public Ehcache createDecoratedEhcache(Ehcache cache, Properties properties) {
        final String name = properties.getProperty("metric-registry-name");
        final MetricRegistry registry = SharedMetricRegistries.getOrCreate(name);
        return InstrumentedEhcache.instrument(registry, cache);
    }

    @Override
    public Ehcache createDefaultDecoratedEhcache(Ehcache cache, Properties properties) {
        final String name = properties.getProperty("metric-registry-name");
        final MetricRegistry registry = SharedMetricRegistries.getOrCreate(name);
        return InstrumentedEhcache.instrument(registry, cache);
    }
    
}
