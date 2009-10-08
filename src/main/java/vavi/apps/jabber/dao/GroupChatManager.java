/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.dao;

import java.util.ArrayList;
import java.util.List;

import vavi.apps.jabber.web.ChatRoom;
import vavi.util.Singleton;


/**
 * GroupChatManager.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040207 nsano initial version <br>
 */
public class GroupChatManager extends Singleton {

    /** */
    private List<ChatRoom> rooms = new ArrayList<ChatRoom>();

    /** */
    private ConnectionManager connectionManager = Singleton.getInstance(ConnectionManager.class);

    /** */
    public boolean isRoomOpen(String server) {
        for (int i = 0; i < rooms.size(); i++) {
            ChatRoom chatRoom = rooms.get(i);

            if (server.equalsIgnoreCase(chatRoom.getRoomName())) {
                return true;
            }
        }

        return false;
    }

    /** */
    public ChatRoom getChat(String server) {
        for (int i = 0; i < rooms.size(); i++) {
            ChatRoom chatRoom = rooms.get(i);

            if (server.equalsIgnoreCase(chatRoom.getRoomName())) {
                return chatRoom;
            }
        }

        return null;
    }
}

/* */
