package plugin.interaction.inter;

import org.wildscape.game.component.CloseEvent;
import org.wildscape.game.component.Component;
import org.wildscape.game.component.ComponentDefinition;
import org.wildscape.game.component.ComponentPlugin;
import org.wildscape.game.content.global.tutorial.TutorialSession;
import org.wildscape.game.content.global.tutorial.TutorialStage;
import org.wildscape.game.node.entity.combat.equipment.WeaponInterface;
import org.wildscape.game.node.entity.combat.equipment.WeaponInterface.WeaponInterfaces;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.info.Rights;
import org.wildscape.game.world.GameWorld;
import org.wildscape.game.world.map.RegionManager;
import org.wildscape.plugin.Plugin;

/**
 * Represents the component plugin used for the game interface.
 * @author Vexia
 * 
 */
public final class GameInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(548, this);
		ComponentDefinition.put(750, this);
		ComponentDefinition.put(751, this);
		ComponentDefinition.put(740, this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Component component, int opcode, int button, int slot, int itemId) {
		switch (component.getId()) {
		case 740:
			switch (button){
			case 3:
				player.getInterfaceManager().closeChatbox();
				break;
			}
			return true;
		case 548:
			int tut_stage = TutorialSession.getExtension(player).getStage();
			if (button >= 38 && button<= 44 || button >= 20 && button <= 26) {
				player.getInterfaceManager().setCurrentTabIndex(getTabIndex(button));
			}
			switch (button) {
			case 21://friends tab
				if (tut_stage == 63) {
					player.getConfigManager().set(1021, 0);
					TutorialStage.load(player, 64, false);
				}
				break;
			case 22://ignore tab
				if (tut_stage == 64) {
					player.getConfigManager().set(1021, 0);
					TutorialStage.load(player, 65, false);
				}
				break;
			case 24://settings tab
				if (tut_stage == 1) {
					TutorialStage.load(player, 2, false);
				}
				break;
			case 38://attack tab
				if (tut_stage == 49) {
					player.getConfigManager().set(1021, 0);
					TutorialStage.load(player, 50, false);
				} else {
					if (TutorialSession.getExtension(player).getStage() > TutorialSession.MAX_STAGE) {
						if (player.getExtension(WeaponInterface.class) == WeaponInterfaces.STAFF) {
							final Component c = new Component(WeaponInterfaces.STAFF.getInterfaceId());
							player.getInterfaceManager().openTab(0, c);
							final WeaponInterface inter = player.getExtension(WeaponInterface.class);
							inter.updateInterface();
						}
					}
				}
				break;
			case 39://skill tab
				if (tut_stage == 10) {
					player.getConfigManager().set(1021, 0);
					TutorialStage.load(player, 11, false);
				}
				break;
			case 40://quest tab
				if (tut_stage == 27) {
					TutorialStage.load(player, 28, false);
				}
				player.getQuestRepository().syncronizeTab(player);
				break;
			case 41://invy tab
				if (tut_stage == 5) {
					player.getConfigManager().set(1021, 0);
					TutorialStage.load(player, 6, false);
				}
				player.getInventory().refresh();
				break;
			case 42://equipment tab
				if (tut_stage == 45) {
					player.getConfigManager().set(1021, 0);
					TutorialStage.load(player, 46, false);
				}
				break;
			case 43://prayer tab
				if (tut_stage == 61) {
					player.getConfigManager().set(1021, 0);
					TutorialStage.load(player, 62, false);
				} 
				break;
			case 44://magic tab
				if (tut_stage == 68) {
					player.getConfigManager().set(1021, 0);
					TutorialStage.load(player, 69, false);
					player.getDialogueInterpreter().open(946, RegionManager.getNpc(player, 946));
				}
				break;
			case 66:
				/*player.getPacketDispatch().sendRunScript(3336, "");//3336
				player.getInterfaceManager().openWindowsPane(new Component(755), true);
				int position = player.getLocation().getX() << 14 | player.getLocation().getY();// | player.getLocation().getZ() << 28;
				player.getConfigManager().set(622, position, true);
				player.getConfigManager().set(674, position, true);*/
				break;
			case 69://Logout
				player.getPacketDispatch().sendString("When you have finished playing " + GameWorld.getName() + ", always use the button below to logout safely. ", 182, 0);
				break;
			}
			return true;
		case 750:
			switch (opcode) {
			case 155:
				switch (button) {
				case 1:
					player.getSettings().toggleRun();
					break;
				}
				break;
			}
			return true;
		case 751:
			switch (opcode) {
			case 155:
				switch (button) {
				case 27:
					openReport(player);
					break;
				}
				break;
			}
			return true;
		}
		return true;
	}

	/**
	 * Method used to open the report interface.
	 * @param player the player.
	 */
	public static void openReport(final Player player) {
		player.getInterfaceManager().open(new Component(553)).setCloseEvent(new CloseEvent() {
			@Override
			public boolean close(Player player, Component c) {
				player.getPacketDispatch().sendRunScript(80, "");
				player.getPacketDispatch().sendRunScript(137, "");
				return true;
			}
		});
		player.getPacketDispatch().sendRunScript(508, "");
		if (player.getDetails().getRights() != Rights.REGULAR_PLAYER) {
			for (int i = 0; i < 18; i++) {
				player.getPacketDispatch().sendInterfaceConfig(553, i, false);
			}
		}
	}

	/**
	 * Gets the tab index.
	 * @param button The button id.
	 * @return The tab index.
	 */
	private static int getTabIndex(int button) {
		int tabIndex = button - 38;
		if (button < 27) {
			tabIndex = (button - 20) + 7;
		}
		return tabIndex;
	}
}