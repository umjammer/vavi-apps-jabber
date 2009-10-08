/*
 */

package vavi.apps.jabber.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


/**
 * SecurityRequestWrapper
 *
 */
public class SecurityRequestWrapper extends HttpServletRequestWrapper {

    /** */
    public static final String PRINCIPAL_SESSION_KEY =
        SecurityRequestWrapper.class.getName() + ".PRINCIPAL";

    /** */
    private String authType;

    /**
     * Construct a new SecurityRequestWrapper.
     *
     * @param request the request to wrap
     * @param authType the SecurityRealmInterface implementation
     */
    public SecurityRequestWrapper(HttpServletRequest request,
                                  String authType) {
        super(request);

        this.authType = authType;
    }
}

/* */
