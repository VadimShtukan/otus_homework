package stukan.vadim.otus.lesson2.controller;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Component
@RestController
public class MetricsController {
    private final CollectorRegistry collectorRegistrySystem = new CollectorRegistry();
    private final PrometheusMeterRegistry meterRegistry =
            new PrometheusMeterRegistry(PrometheusConfig.DEFAULT, collectorRegistrySystem, Clock.SYSTEM);


    public MetricsController() {
        new ClassLoaderMetrics().bindTo(this.meterRegistry);
        new JvmMemoryMetrics().bindTo(this.meterRegistry);
        new JvmGcMetrics().bindTo(this.meterRegistry);
        new ProcessorMetrics().bindTo(this.meterRegistry);
        new JvmThreadMetrics().bindTo(this.meterRegistry);
    }


    public PrometheusMeterRegistry getMeterRegistry() {
        return meterRegistry;
    }

    @GetMapping
    @RequestMapping(value = "metrics", produces = TextFormat.CONTENT_TYPE_004)
    public void getMetrics(HttpServletResponse response) throws IOException {
        try (Writer writer = response.getWriter()) {
            TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
        }
    }

    @GetMapping
    @RequestMapping(value = "metrics_system", produces = TextFormat.CONTENT_TYPE_004)
    public String getMetricsSystem() {
        return meterRegistry.scrape();
    }
}
