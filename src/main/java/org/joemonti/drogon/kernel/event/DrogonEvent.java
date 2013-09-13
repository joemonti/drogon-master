/*
 * Drogon : DrogonEvent.java
 * 
 * This file is part of Drogon.
 *
 * Drogon is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Drogon is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Drogon.  If not, see <http://www.gnu.org/licenses/>. 
 *
 * Author: Joseph Monti <joe.monti@gmail.com>
 * Copyright (c) 2013 Joseph Monti All Rights Reserved, http://joemonti.org/
 */

package org.joemonti.drogon.kernel.event;

import org.joemonti.drogon.util.BytesUtil;

/**
 * 
 * 
 * @author Joseph Monti <joe.monti@gmail.com>
 * @version 1.0
 */
public class DrogonEvent {
    private static final int SOURCE_OFFSET = 0;
    private static final int COMMAND_OFFSET = SOURCE_OFFSET + BytesUtil.SIZEOF_LONG;
    private static final int OBJECT_OFFSET = COMMAND_OFFSET + BytesUtil.SIZEOF_INT;
    
    private final long source;
    private final DrogonEventCommand command;
    private final DrogonEventObject object;
    
    public DrogonEvent( long source, DrogonEventCommand command, DrogonEventObject object ) {
        this.source = source;
        this.command = command;
        this.object = object;
    }
    
    public DrogonEvent( byte[] bytes, DrogonEventInfo eventInfo ) throws InstantiationException, IllegalAccessException {
        this.source = BytesUtil.readLong( bytes, SOURCE_OFFSET );
        this.command = DrogonEventCommand.get( BytesUtil.readInt( bytes, COMMAND_OFFSET ) );
        
        int objectLength = bytes.length - OBJECT_OFFSET;
        byte[] objectBytes = new byte[objectLength];
        BytesUtil.readBytes( bytes, OBJECT_OFFSET, objectBytes, 0, objectLength );
        
        this.object = eventInfo.createEventObjectInstance( );
        this.object.deserialize( objectBytes );
    }

    public long getSource() {
        return source;
    }

    public DrogonEventCommand getCommand() {
        return command;
    }

    public DrogonEventObject getObject() {
        return object;
    }
    
    public byte[] serialize() {
        byte[] objectBytes = this.object.serialize( );
        
        int length = BytesUtil.SIZEOF_LONG + BytesUtil.SIZEOF_INT + objectBytes.length;
        
        byte[] bytes = new byte[length];
        
        BytesUtil.writeLong( bytes, SOURCE_OFFSET, source );
        BytesUtil.writeInt( bytes, COMMAND_OFFSET, command.commandId );
        BytesUtil.writeBytes( bytes, OBJECT_OFFSET, objectBytes, 0, objectBytes.length );
        
        return bytes;
    }
}
