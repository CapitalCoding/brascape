package org.wildscape.net.producer;

import java.nio.ByteBuffer;

import org.wildscape.net.EventProducer;
import org.wildscape.net.IoReadEvent;
import org.wildscape.net.IoSession;
import org.wildscape.net.IoWriteEvent;
import org.wildscape.net.event.MSHSReadEvent;
import org.wildscape.net.event.MSHSWriteEvent;

/**
 * Handles the Management server handshake event producing.
 * @author Emperor
 */
public final class MSHSEventProducer implements EventProducer {

	@Override
	public IoReadEvent produceReader(IoSession session, ByteBuffer buffer) {
		return new MSHSReadEvent(session, buffer);
	}

	@Override
	public IoWriteEvent produceWriter(IoSession session, Object context) {
		return new MSHSWriteEvent(session, context);
	}

}