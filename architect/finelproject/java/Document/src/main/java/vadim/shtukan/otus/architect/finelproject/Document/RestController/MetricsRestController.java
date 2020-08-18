package vadim.shtukan.otus.architect.finelproject.Document.RestController;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@RestController
public class MetricsRestController {
    @GetMapping
    @RequestMapping(value = "metrics", produces = TextFormat.CONTENT_TYPE_004)
    public void getMetrics(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "text/plain; charset=UTF-8");
        try (Writer writer = response.getWriter()) {
            TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
        }
    }
}
