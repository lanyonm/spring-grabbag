package org.lanyonm.grabbag.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

@Configuration
@EnableMetrics
public class MetricsConfig extends MetricsConfigurerAdapter {

	@Override
	public MetricRegistry getMetricRegistry() {
		return SharedMetricRegistries.getOrCreate("springMetrics");
	}

	@Override
	public void configureReporters(MetricRegistry registry) {
		// final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
		// 		.convertRatesTo(TimeUnit.SECONDS)
		// 		.convertDurationsTo(TimeUnit.MILLISECONDS)
		// 		.build();
		// reporter.start(1, TimeUnit.MINUTES);
		final JmxReporter reporter = JmxReporter.forRegistry(registry).build();
		reporter.start();
	}
}
