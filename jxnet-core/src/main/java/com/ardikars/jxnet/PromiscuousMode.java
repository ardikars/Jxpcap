/**
 * Copyright (C) 2015-2018 Jxnet
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.ardikars.jxnet;

import com.ardikars.common.annotation.Immutable;

/**
 * Pcap promiscuous mode.
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 * @since 1.0.0
 */
@Immutable
public enum PromiscuousMode {

    PROMISCUOUS(1), NON_PROMISCUOUS(0);

    private final int value;

    PromiscuousMode(final int value) {
        this.value = value;
    }

    /**
     * Returns 1 if in promicuous mode, 0 otherwise.
     * @return  returns if in promicuous mode, 0 otherwise.
     */
    public int getValue() {
        return this.value;
    }

}
