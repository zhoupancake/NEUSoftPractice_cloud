package com.system.util;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TomcatUtil {

    @Value("${server.additionalPorts}")
    private final String additionalPorts;

    public TomcatUtil(@Value("${server.additionalPorts}") String additionalPorts) {
        this.additionalPorts = additionalPorts;
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
        return tomcat -> {
            Connector[] additionalConnectors = additionalConnector();
            if (additionalConnectors != null && additionalConnectors.length > 0) {
                tomcat.addAdditionalTomcatConnectors(additionalConnectors);
            }
        };
    }

    private Connector[] additionalConnector() {
        if (additionalPorts == null || additionalPorts.isEmpty()) {
            return null;
        }

        String[] ports = additionalPorts.split(",");
        List<Connector> result = new ArrayList<>();
        for (String port : ports) {
            Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
            connector.setScheme("http");
            connector.setPort(Integer.parseInt(port));
            result.add(connector);
        }
        return result.toArray(new Connector[0]);
    }
}