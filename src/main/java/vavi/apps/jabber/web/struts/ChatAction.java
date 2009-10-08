/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web.struts;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import vavi.apps.jabber.dao.ChatManager;
import vavi.apps.jabber.dao.MessageManager;
import vavi.net.im.Buddy;
import vavi.net.im.Message;
import vavi.net.im.Session;
import vavi.net.im.TextComponent;
import vavi.net.im.protocol.xmpp.JabberProtocol;
import vavi.util.Singleton;


/**
 * Chat.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 030130 nsano initial version <br>
 */
public final class ChatAction extends Action {

    /** */
    private ChatManager chatManager = Singleton.getInstance(ChatManager.class);

    /** */
    private MessageManager messageManager = Singleton.getInstance(MessageManager.class);

    /**
     *
     * @forward
     * 	name="success"
     *	path="/pages/login.jsp"
     *
     * @throws SQLException
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception {

        //
        String to = request.getRemoteUser();

        //
        HttpSession session = request.getSession(false);
        JabberProtocol protocol = (JabberProtocol) session.getAttribute("jabberClient");
//Debug.print(StringUtil.paramString(jabberClient));

        DynaActionForm chatForm = (DynaActionForm)form;
        String from = (String)chatForm.get("jid");

        if ("POST".equalsIgnoreCase(request.getMethod())) {

            //
            String function = (String)chatForm.get("function");
            if ("Send".equals(function)) {
                // Chat
                String text = (String)chatForm.get("message");

                Session chat = protocol.startSession(new Buddy(from));
                Message message = new Message();
                message.addComponent(new TextComponent(text));
                chat.sendMessage(message);

                vavi.apps.jabber.web.Chat c = chatManager.getChat(to, from);
                request.setAttribute("chat", c.toString());
                chatForm.set("message", "");

            } else if ("Reload".equals(function)) {
                // Reload
                vavi.apps.jabber.web.Chat c = chatManager.getChat(to, from);
                request.setAttribute("chat", c.toString());
            }
        }

        return mapping.findForward("success");
    }
}

/* */
