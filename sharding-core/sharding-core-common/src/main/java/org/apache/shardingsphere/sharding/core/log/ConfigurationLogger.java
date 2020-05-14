/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.sharding.core.log;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.underlying.common.yaml.representer.processor.NoneTupleProcessor;
import org.apache.shardingsphere.sharding.core.yaml.swapper.root.RuleRootConfigurationsYamlSwapper;
import org.apache.shardingsphere.underlying.common.auth.Authentication;
import org.apache.shardingsphere.underlying.common.auth.yaml.swapper.AuthenticationYamlSwapper;
import org.apache.shardingsphere.underlying.common.config.RuleConfiguration;
import org.apache.shardingsphere.underlying.common.yaml.engine.YamlEngine;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

/**
 * Configuration logger.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class ConfigurationLogger {
    
    /**
     * Log rule configuration.
     *
     * @param ruleConfigurations rule configurations
     */
    public static void log(final Collection<RuleConfiguration> ruleConfigurations) {
        log("Rule configurations: ", YamlEngine.marshal(new RuleRootConfigurationsYamlSwapper().swap(ruleConfigurations), Collections.singleton(new NoneTupleProcessor())));
    }
    
    /**
     * Log authentication configuration.
     *
     * @param authenticationConfiguration authentication configuration
     */
    public static void log(final Authentication authenticationConfiguration) {
        if (null != authenticationConfiguration) {
            log(authenticationConfiguration.getClass().getSimpleName(), YamlEngine.marshal(new AuthenticationYamlSwapper().swap(authenticationConfiguration)));
        }
    }
    
    /**
     * Log properties.
     *
     * @param properties properties
     */
    public static void log(final Properties properties) {
        if (null != properties) {
            log(properties.getClass().getSimpleName(), YamlEngine.marshal(properties));
        }
    }
    
    private static void log(final String type, final String logContent) {
        log.info("{}:\n{}", type, logContent);
    }
}