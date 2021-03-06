/**
 * Copyright (c) 2014-2015 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.library.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.smarthome.core.items.GenericItem;
import org.eclipse.smarthome.core.library.CoreItemFactory;
import org.eclipse.smarthome.core.library.types.DecimalType;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.library.types.PercentType;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.RefreshType;
import org.eclipse.smarthome.core.types.State;
import org.eclipse.smarthome.core.types.UnDefType;

/**
 * A SwitchItem represents a normal switch that can be ON or OFF.
 * Useful for normal lights, presence detection etc.
 *
 * @author Kai Kreuzer - Initial contribution and API
 *
 */
public class SwitchItem extends GenericItem {

    private static List<Class<? extends State>> acceptedDataTypes = new ArrayList<Class<? extends State>>();
    private static List<Class<? extends Command>> acceptedCommandTypes = new ArrayList<Class<? extends Command>>();

    static {
        acceptedDataTypes.add(OnOffType.class);
        acceptedDataTypes.add(UnDefType.class);

        acceptedCommandTypes.add(OnOffType.class);
        acceptedCommandTypes.add(RefreshType.class);
    }

    public SwitchItem(String name) {
        super(CoreItemFactory.SWITCH, name);
    }

    /* package */SwitchItem(String type, String name) {
        super(type, name);
    }

    public void send(OnOffType command) {
        internalSend(command);
    }

    @Override
    public List<Class<? extends State>> getAcceptedDataTypes() {
        return Collections.unmodifiableList(acceptedDataTypes);
    }

    @Override
    public List<Class<? extends Command>> getAcceptedCommandTypes() {
        return Collections.unmodifiableList(acceptedCommandTypes);
    }

    @Override
    public State getStateAs(Class<? extends State> typeClass) {
        if (typeClass == DecimalType.class) {
            return state == OnOffType.ON ? new DecimalType(1) : DecimalType.ZERO;
        } else if (typeClass == PercentType.class) {
            return state == OnOffType.ON ? PercentType.HUNDRED : PercentType.ZERO;
        } else {
            return super.getStateAs(typeClass);
        }
    }
}
