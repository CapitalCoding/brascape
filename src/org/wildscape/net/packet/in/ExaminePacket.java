package org.wildscape.net.packet.in;

import java.util.Arrays;

import org.wildscape.cache.Cache;
import org.wildscape.cache.def.impl.ItemDefinition;
import org.wildscape.cache.def.impl.NPCDefinition;
import org.wildscape.cache.def.impl.ObjectDefinition;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.net.packet.IncomingPacket;
import org.wildscape.net.packet.IoBuffer;

/**
 * Handles the incoming examine packets.
 * @author Emperor
 */
public final class ExaminePacket implements IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		String name;
		switch (buffer.opcode()) {
		case 94: // Object examine
			int id = buffer.getLEShortA();
			if (id < 0 || id > Cache.getObjectDefinitionsSize()) {
				break;
			}
			ObjectDefinition d = ObjectDefinition.forId(id);
			name = d.getExamine();
			player.debug("Object id: " + id + ", models: " + (d.getModelIds() != null ? Arrays.toString(d.getModelIds()) : null) + ", anim: " + d.animationId + ", config: " + (d.getConfigFileId() != -1 ? d.getConfigFileId() + " (file)" : d.getConfigId()) + ".");
			player.getPacketDispatch().sendMessage(""+name+"");
			break;
		case 235:
		case 92: // Item examine
			id = buffer.getLEShortA();
			if (id < 0 || id > Cache.getItemDefinitionsSize()) {
				break;
			}
			player.getPacketDispatch().sendMessage(getItemExamine(id));
			break;
		case 72: // NPC examine
			id = buffer.getShort();
			if (id < 0 || id > Cache.getNPCDefinitionsSize()) {
				break;
			}
			player.debug("NPC id: " + id + ".");
			NPCDefinition def = NPCDefinition.forId(id);
			if (def == null) {
				break;
			}
			player.getPacketDispatch().sendMessage(def.getExamine());
			break;
		}
	}

	/**
	 * Gets the item examine.
	 * @param id the id.
	 * @return the name.
	 */
	public static String getItemExamine(int id) {
		if (id == 995) {
			return "Lovely money!";
		}
		if (ItemDefinition.forId(id).getExamine().length() == 255) {
			return "A set of instructions to be followed.";
		}
		return ItemDefinition.forId(id).getExamine();
	}
}