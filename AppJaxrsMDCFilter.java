package sn.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.slf4j.MDC;

import sn.Fishtag;
import com.google.common.base.Optional;

/**
 * That filter allows to better abstract MSM fishtag aka client-id
 * it is based on KISS and DRY principles 
 * The log4j MDC object is used for thread contextual logging
 *
 */

@Fishtag
@Provider
public class AppJaxrsMDCFilter implements ContainerRequestFilter, ContainerResponseFilter {
	private static final String CLIENT_ID = "client-id";

    @Context
    protected HttpServletRequest r;

    @Override
    public void filter(ContainerRequestContext req) throws IOException {	
        Optional<String> clientId = Optional.fromNullable(r.getHeader("client-id"));
        MDC.put(CLIENT_ID, clientId.or(defaultClientId()));
    }

    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext resp) throws IOException {
        MDC.remove(CLIENT_ID);
    }

    private String defaultClientId(){
        return UUID.randomUUID().toString();
    }
}
