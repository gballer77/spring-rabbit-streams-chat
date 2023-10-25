package software.baller.eventchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.resource.PathResourceResolver;
import org.springframework.web.reactive.resource.ResourceResolverChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

import static java.util.concurrent.TimeUnit.DAYS;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/static//assets/")
                .setCacheControl(CacheControl.maxAge(1, DAYS))
                .resourceChain(true);

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(getResourceResolver());
    }

    private PathResourceResolver getResourceResolver() {
        return new PathResourceResolver() {
            @Override
            public Mono<Resource> resolveResource(ServerWebExchange exchange, String requestPath, List<? extends Resource> locations, ResourceResolverChain chain) {
                var requestedResource = super.resolveResource(exchange, requestPath, locations, chain);
                return requestedResource != null ? requestedResource : super.resolveResource(exchange, "/index.html", locations, chain);
            }
        };
    }
}
