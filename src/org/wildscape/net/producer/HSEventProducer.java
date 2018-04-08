package org.wildscape.net.producer;

import java.nio.ByteBuffer;

import org.wildscape.net.EventProducer;
import org.wildscape.net.IoReadEvent;
import org.wildscape.net.IoSession;
import org.wildscape.net.IoWriteEvent;
import org.wildscape.net.event.HSReadEvent;
import org.wildscape.net.event.HSWriteEvent;

/**
 * Produces I/O events for the handshake protocol.
 * @author Emperor
 */
public final class HSEventProducer implements EventProducer {

	@Override
	public IoReadEvent produceReader(IoSession session, ByteBuffer buffer) {
		return new HSReadEvent(session, buffer);
	}

	@Override
	public IoWriteEvent produceWriter(IoSession session, Object context) {
		return new HSWriteEvent(session, context);
	}

}