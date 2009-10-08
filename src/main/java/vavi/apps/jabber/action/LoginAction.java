/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.action;

import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

import vavi.apps.jabber.web.Profile;
import vavi.net.im.protocol.Protocol;
import vavix.kumi.Action;
import vavix.kumi.Request;
import vavix.kumi.Response;


/**
 * Login.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040108 nsano initial version <br>
 */
public final class LoginAction extends Action {

    /**
     */
    public void execute(ActionForm form,
                        Request request,
                        Response response)
        throws Exception {

        Profile profile = new Profile();
        BeanUtils.copyProperties(profile, form);
//Debug.print(StringUtil.paramString(profile));

    	// check logged in
        Protocol protocol = new vavi.net.im.protocol.xmpp.JabberProtocol();
        Properties props = new Properties();
        props.setProperty("username", profile.getUsername());
        props.setProperty("password", profile.getPassword());
        protocol.connect(props);

//Debug.print(StringUtil.paramString(jabberProtocol));
        response.set("protocol", protocol);
    }
}

/* */
