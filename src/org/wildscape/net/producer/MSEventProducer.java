package org.wildscape.net.producer;

import java.nio.ByteBuffer;

import org.wildscape.net.EventProducer;
import org.wildscape.net.IoReadEvent;
import org.wildscape.net.IoSession;
import org.wildscape.net.IoWriteEvent;
import org.wildscape.net.event.MSReadEvent;
import org.wildscape.net.event.MSWriteEvent;

/**
 * Handles Management server events.
 * @author Emperor
 */
public final class MSEventProducer implements EventProducer {

	@Override
	public IoReadEvent produceReader(IoSession session, ByteBuffer buffer) {
		return new MSReadEvent(session, buffer);
	}

	@Override
	public IoWriteEvent produceWriter(IoSession session, Object context) {
		return new MSWriteEvent(session, context);
	}

}