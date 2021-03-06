/*
 * Drogon : DrogonEventInfo.java
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

/**
 * 
 * 
 * @author Joseph Monti <joe.monti@gmail.com>
 * @version 1.0
 */
public class DrogonEventInfo {
    private final long source;
    private final short id;
    private final String name;
    private final Class<? extends DrogonEventData> eventObjectClass;
    
    public DrogonEventInfo( long source, short id, String name, Class<? extends DrogonEventData> eventObjectClass ) {
        this.source = source;
        this.id = id;
        this.name = name;
        this.eventObjectClass = eventObjectClass;
    }
    
    public long getSource() {
        return source;
    }
    
    public short getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DrogonEventData createEventObjectInstance() 
            throws InstantiationException, IllegalAccessException {
        return eventObjectClass.newInstance( );
    }
}
