package org.croanna.middleware;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.croanna.models.RequestLog;
import org.croanna.services.RequestLogService;

import java.io.IOException;
import java.time.LocalDateTime;

@Provider
@Priority(1)
@ApplicationScoped
public class RequestLoggingFilter implements ContainerRequestFilter {

    @Inject
    RequestLogService requestLogService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String method = requestContext.getMethod();
        String path = requestContext.getUriInfo().getPath();

        RequestLog log = new RequestLog();
        log.setMethod(method);
        log.setPath(path);
        log.setTimestamp(LocalDateTime.now());

        requestLogService.createLog(log);
    }
}
