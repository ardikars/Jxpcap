/**
 * Copyright (C) 2017  Ardika Rommy Sanjaya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.ardikars.jxnet.packet.ip;

import com.ardikars.jxnet.Inet6Address;
import com.ardikars.jxnet.packet.Packet;
import com.ardikars.jxnet.util.Builder;

import java.nio.ByteBuffer;

/**
 * @author Ardika Rommy Sanjaya
 * @since 1.1.0
 */
public class IPv6 extends Packet implements Builder<Packet> {

    public static final int IPV6_HEADER_LENGTH = 40;

    private byte version;
    private byte trafficClass;
    private int flowLabel;
    private short payloadLength;
    private IPProtocolNumber nextHeader;
    private byte hopLimit;
    private Inet6Address sourceAddress = Inet6Address.LOCALHOST;
    private Inet6Address destinationAddress = Inet6Address.LOCALHOST;

    /**
     * IPv6 payload
     */
    private byte[] payload;

    public IPv6() {
        this.setVersion((byte) 6);
        this.setTrafficClass((byte) 0);
        this.setFlowLabel(0);
        this.setPayloadLength((short) 0);
        this.setNextHeader(IPProtocolNumber.UNKNOWN);
        this.setHopLimit((byte) 0);
        this.setSourceAddress(Inet6Address.LOCALHOST);
        this.setDestinationAddress(Inet6Address.LOCALHOST);
        this.setPayload(null);
    }

    public byte getVersion() {
        return (byte) (this.version & 0xf);
    }

    public IPv6 setVersion(final byte version) {
        this.version = (byte) (version & 0xf);
        return this;
    }

    public byte getTrafficClass() {
        return (byte) (this.trafficClass & 0xff);
    }

    public IPv6 setTrafficClass(final byte trafficClass) {
        this.trafficClass = (byte) (trafficClass & 0xff);
        return this;
    }

    public int getFlowLabel() {
        return (int) (this.flowLabel & 0xfffff);
    }

    public IPv6 setFlowLabel(final int flowLabel) {
        this.flowLabel = (int) (flowLabel & 0xfffff);
        return this;
    }

    public short getPayloadLength() {
        return (short) (this.payloadLength & 0xffff);
    }

    public IPv6 setPayloadLength(final short payloadLength) {
        this.payloadLength = (short) (payloadLength & 0xffff);
        return this;
    }

    public IPProtocolNumber getNextHeader() {
        return this.nextHeader;
    }

    public IPv6 setNextHeader(final IPProtocolNumber nextHeader) {
        this.nextHeader = nextHeader;
        return this;
    }

    public byte getHopLimit() {
        return (byte) (this.hopLimit & 0xff);
    }

    public IPv6 setHopLimit(final byte hopLimit) {
        this.hopLimit = (byte) (this.hopLimit & 0xff);
        return this;
    }

    public Inet6Address getSourceAddress() {
        return this.sourceAddress;
    }

    public IPv6 setSourceAddress(final Inet6Address sourceAddress) {
        this.sourceAddress = sourceAddress;
        return this;
    }

    public Inet6Address getDestinationAddress() {
        return this.destinationAddress;
    }

    public IPv6 setDestinationAddress(final Inet6Address destinationAddress) {
        this.destinationAddress = destinationAddress;
        return this;
    }

    public byte[] getPayload() {
        return this.payload;
    }

    public IPv6 setPayload(final byte[] payload) {
        this.payload = payload;
        return this;
    }

    public static IPv6 newInstance(final byte[] bytes) {
        return newInstance(bytes, 0, bytes.length);
    }

    public static IPv6 newInstance(final byte[] bytes, final int offset, final int length) {
        ByteBuffer bb = ByteBuffer.wrap(bytes, offset, length);
        IPv6 ipv6 = new IPv6();
        int iscratch = bb.getInt();
        ipv6.setVersion((byte) (iscratch >> 28 & 0xf));
        ipv6.setTrafficClass((byte) (iscratch >> 20 & 0xff));
        ipv6.setFlowLabel(iscratch & 0xfffff);
        ipv6.setPayloadLength(bb.getShort());
        ipv6.setNextHeader(IPProtocolNumber.getInstance(bb.get()));
        ipv6.setHopLimit(bb.get());

        byte[] addrBuf = new byte[Inet6Address.IPV6_ADDRESS_LENGTH];
        bb.get(addrBuf);
        ipv6.setSourceAddress(Inet6Address.valueOf(addrBuf));

        addrBuf = new byte[Inet6Address.IPV6_ADDRESS_LENGTH];
        bb.get(addrBuf);
        ipv6.setDestinationAddress(Inet6Address.valueOf(addrBuf));

        ipv6.payload = new byte[bb.limit() - IPV6_HEADER_LENGTH];
        bb.get(ipv6.payload);
        return ipv6;
    }

    @Override
    public Packet setPacket(Packet packet) {
        this.payload = packet.toBytes();
        return this;
    }

    @Override
    public Packet getPacket() {
        return null;
    }

    @Override
    public byte[] toBytes() {
        byte[] data = new byte[IPV6_HEADER_LENGTH + ((this.getPayload() == null) ? 0 : this.getPayload().length)];
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.putInt((this.getVersion() & 0xf) << 28 | (this.getTrafficClass() & 0xff) << 20 | this.getFlowLabel() & 0xfffff);
        buffer.putShort(this.getPayloadLength());
        buffer.put(this.getNextHeader().getValue());
        buffer.put(this.getHopLimit());
        buffer.put(this.getSourceAddress().toBytes());
        buffer.put(this.getSourceAddress().toBytes());
        if (this.getPayload() != null) {
            buffer.put(this.getPayload());
        }
        return data;
    }

    @Override
    public Packet build() {
        return this;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("[Version: " + this.getVersion())
                .append(", Traffic Class: " + this.getTrafficClass())
                .append(", Flow Label: " + this.getFlowLabel())
                .append(", Payload Length: " + this.getPayloadLength())
                .append(", Next Header: " + this.getNextHeader())
                .append(", Hop Limit: " + this.getHopLimit())
                .append(", Source Address: " + this.getSourceAddress().toString())
                .append(", Destination Address: " + this.getDestinationAddress().toString())
                .append("]").toString();
    }

}