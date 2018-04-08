package org.wildscape.net.producer;

import java.nio.ByteBuffer;

import org.wildscape.net.EventProducer;
import org.wildscape.net.IoReadEvent;
import org.wildscape.net.IoSession;
import org.wildscape.net.IoWriteEvent;
import org.wildscape.net.event.GameReadEvent;
import org.wildscape.net.event.GameWriteEvent;

/**
 * Produces game packet I/O events.
 * @author Emperor
 */
public final class GameEventProducer implements EventProducer {

	@Override
	public IoReadEvent produceReader(IoSession session, ByteBuffer buffer) {
		return new GameReadEvent(session, buffer);
	}

	@Override
	public IoWriteEvent produceWriter(IoSession session, Object context) {
		return new GameWriteEvent(session, context);
	}

}