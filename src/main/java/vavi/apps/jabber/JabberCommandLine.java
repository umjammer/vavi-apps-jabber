/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.swing.JOptionPane;

import vavi.net.im.Buddy;
import vavi.net.im.Group;
import vavi.net.im.Message;
import vavi.net.im.Session;
import vavi.net.im.TextComponent;
import vavi.net.im.event.IMAdapter;
import vavi.net.im.event.IMListener;
import vavi.net.im.protocol.Protocol;
import vavi.util.StringUtil;


/**
 * JabberCommandLine.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 030307 nsano initial version <br>
 */
public class JabberCommandLine {

    /** */
    private Protocol protocol;

    /** */
    public JabberCommandLine(Properties props) throws IOException {

        this.protocol = new vavi.net.im.protocol.xmpp.JabberProtocol();
//        this.protocol = new vavi.net.im.protocol.ymsg.YmsgProtocol();
        protocol.addIMListener(listener);
        protocol.connect(props);
    }

    /** */
    private IMListener listener = new IMAdapter() {
        /** */
        public boolean doConfirmation(String message) {
            int yn = JOptionPane.showConfirmDialog(null, message, "JabberCommandLine", JOptionPane.YES_NO_OPTION);
            return yn == JOptionPane.YES_NO_OPTION;
        }
        /** */
        public void instantMessageReceived(Session session, Message message) {
//Debug.println("message: " + message);
//Debug.println("session: " + session.getParticipants()[0]);
            System.out.printf("%tT %s: %s\n", message.getDate(), session.getParticipants()[0].getUsername(), message);
            System.out.flush();
        }
        /** */
        public void buddyStatusChanged(Buddy buddy) {
            System.out.println("Status of " + buddy.getUsername() + " is now " + buddy.getStatus());
            System.out.flush();
        }
    };

    /** */
    private void interact() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                System.out.print(">");
                String line = reader.readLine();
                if (line == null) {
                    break;
                }

                if (line.length() == 0) {
                    System.err.println("l               list");
                    System.err.println("c jid message   chat");
                    System.err.println("a jid           add entry");
                    System.err.println("g group         add group");
                    System.err.println("m jid group     move entry group");
                    System.err.println("q               quit");
                    continue;
                }

                String[] args = StringUtil.splitCommandLine(line);

                if ("l".equals(args[0])) {
                    // List
System.err.println("* Contacts:");
                    for (Buddy entry : protocol.getDefaultGroup()) {
                        System.err.println(" - " + " " + entry.getAlias());
                    }
                    for (Group group : protocol.getGroups()) {
System.err.println("* " +  group.getName() + ":");
                        for (Buddy buddy : group) {
System.err.println(" - [" + getPresenceChar(buddy.getStatus()) + "] " + buddy.getAlias());
                        }
                    }
                } else if ("c".equals(args[0])) {
                    // Chat:
                    Buddy buddy = new Buddy(args[1]);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        sb.append(args[i]);
                        sb.append(' ');
                    }
                    String text = sb.toString();
                    Session session = protocol.startSession(buddy);
                    Message message = new Message();
                    message.addComponent(new TextComponent(text));
                    session.sendMessage(message);
System.err.printf("%tT %s: %s\n", message.getDate(), args[1], text);
                } else if ("a".equals(args[0])) {
                    // Add: user name
                    Buddy buddy = new Buddy(args[1]);
                    buddy.setAlias(args[2]);
                    protocol.addToBuddyList(buddy);
System.err.println("entry " + args[1] + " added");
                } else if ("a".equals(args[0])) {
                    // add Group: group
                    protocol.addGroup(new Group(args[1]));
System.err.println("group " + args[1] + " added");
                } else if ("m".equals(args[0])) {
                    // Move: jid group
                    Buddy buddy = new Buddy(args[1]);
                    Group newGroup = new Group(args[2]);
                    Group oldGroup = null; // TODO
                    protocol.changeGroupOfBuddy( buddy, oldGroup, newGroup);
                } else if ("q".equals(args[0])) {
                    // Quit:
                    return;
                }
            } catch (Exception e) {
e.printStackTrace(System.err);
            }
        }
    }

    /** */
    private char getPresenceChar(String type) {
        if (type == null) {
            return ' ';
        }
        char c = 0;
        switch (type) {
        case "available":
            c = '@';
            break;
        case "away":
            c = '-';
            break;
        case "chat":
            c = '*';
            break;
        case "do_not_disturb":
            c = 'x';
            break;
        case "extended_away":
            c = '_';
            break;
        case "invisible":
            c = '#';
            break;
        default:
            c = '?';
            break;
        }
        return c;
    }

    //----

    /**
     * @param args 0: username, 1: password, 2: server
     */
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.setProperty("username", args[0]);
        props.setProperty("password", args[1]);
        props.setProperty("server", args[2]);
        props.put("isUsessl", false);
        JabberCommandLine jcl = new JabberCommandLine(props);
        jcl.interact();
        System.exit(0);
    }
}

/* */
