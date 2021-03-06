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

import com.ardikars.common.annotation.Mutable;
import com.ardikars.jxnet.exception.OperationNotSupportedException;

/**
 * Class that keeps statistical values on an interface.
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 * @since 1.0.0
 */
@Mutable(volatiles = { "ps_recv", "ps_drop", "ps_ifdrop" })
public final class PcapStat implements Cloneable {

	private volatile long ps_recv;
	
	private volatile long ps_drop;
	
	private volatile long ps_ifdrop;

	protected PcapStat() {
		this(0L, 0L, 0L);
	}

	protected PcapStat(long psRecv, long psDrop, long psIfdrop) {
		this.ps_recv = psRecv;
		this.ps_drop = psDrop;
		this.ps_ifdrop = psIfdrop;
	}

	/**
	 * This method will throws {@code OperationNotSupportedException}.
	 * @return nothing.
	 * @throws OperationNotSupportedException throws {@code OperationNotSupportedException}.
	 */
	public static PcapStat newInstance() throws OperationNotSupportedException {
		throw new OperationNotSupportedException("Cannot instantiated directly.");
	}

	/**
	 * Returns recieved packets.
	 * @return returns number of packets received;
	 */
	public long getPsRecv() {
		return this.ps_recv;
	}

	/**
	 * Returns number of packets dropped because there was no room in the operating
	 * system`s buffer when they arrived, because packets weren`t being read fast enough.
	 * @return returns number of packets dropped because there was no room in the operating system's buffer.
	 */
	public long getPsDrop() {
		return this.ps_drop;
	}

	/**
	 * Returns dropped packets by interface.
	 * @return returns number of packets dropped by the network interface or its driver.
	 */
	public long getPsIfdrop() {
		return this.ps_ifdrop;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final PcapStat pcapStat = (PcapStat) o;

		if (this.ps_recv != pcapStat.ps_recv) {
			return false;
		}
		if (this.ps_drop != pcapStat.ps_drop) {
			return false;
		}
		return this.ps_ifdrop == pcapStat.ps_ifdrop;
	}

	@Override
	public int hashCode() {
		int result = (int) (this.ps_recv ^ (this.ps_recv >>> 32));
		result = 31 * result + (int) (this.ps_drop ^ (this.ps_drop >>> 32));
		result = 31 * result + (int) (this.ps_ifdrop ^ (this.ps_ifdrop >>> 32));
		return result;
	}

	@Override
	public PcapStat clone() throws CloneNotSupportedException {
		return (PcapStat) super.clone();
	}

	@Override
	public String toString() {
		return new StringBuilder(100)
				.append("PcapStat{ps_recv=").append(this.ps_recv)
				.append(", ps_drop=").append(this.ps_drop)
				.append(", ps_ifdrop=").append(this.ps_ifdrop)
				.append('}')
				.toString();
	}

}
