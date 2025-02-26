/*
 * Copyright 2012-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.start.site.extension.dependency.activemq;

import io.spring.initializr.generator.condition.ConditionalOnPlatformVersion;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.spring.build.BuildCustomizer;
import io.spring.initializr.generator.spring.documentation.HelpDocumentCustomizer;

import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

/**
 * Configuration for generation of projects that depend on ActiveMQ.
 *
 * @author Stephane Nicoll
 */
@ProjectGenerationConfiguration
@ConditionalOnRequestedDependency("activemq")
public class ActiveMQProjectGenerationConfiguration {

	@ConditionalOnPlatformVersion("[3.0.0-M1,3.1.0-RC1)")
	static class SpringBoot30Configuration {

		@Bean
		BuildCustomizer<?> activeMQNotSupportedBuildCustomizer() {
			return BuildCustomizer.ordered(Ordered.HIGHEST_PRECEDENCE + 5,
					(build) -> build.dependencies().remove("activemq"));
		}

		@Bean
		HelpDocumentCustomizer activeMQNotSupportedHelpDocumentCustomizer() {
			return (helpDocument) -> helpDocument.getWarnings()
				.addItem("ActiveMQ is not supported with Spring Boot 3.0");
		}

	}

}
