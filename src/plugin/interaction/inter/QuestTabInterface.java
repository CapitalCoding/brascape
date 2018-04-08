package plugin.interaction.inter;

import org.wildscape.game.component.Component;
import org.wildscape.game.component.ComponentDefinition;
import org.wildscape.game.component.ComponentPlugin;
import org.wildscape.game.content.global.tutorial.TutorialSession;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.link.diary.AchievementDiary;
import org.wildscape.game.node.entity.player.link.diary.DiaryType;
import org.wildscape.game.node.entity.player.link.quest.Quest;
import org.wildscape.plugin.Plugin;

/**
 * Handles the quest tab action buttons.
 * @author Emperor
 * @author Vexia
 */
public class QuestTabInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(274, this); // Quests
		ComponentDefinition.put(259, this); // Achievement diary
		return this;
	}

	@Override
	public boolean handle(Player p, Component component, int opcode, int button, int slot, int itemId) {
		if (TutorialSession.getExtension(p).getStage() < TutorialSession.MAX_STAGE) {
			return true;
		}
		p.getPulseManager().clear();
		switch (component.getId()) {
		case 274:
			switch (button) {
			case 3:
				p.getAchievementDiaryManager().openTab();
				return true;
			case 10:
				break;
			default:
				Quest quest = p.getQuestRepository().forButtonId(button);
				if (quest != null) {
					p.getInterfaceManager().open(new Component(275));
					quest.drawJournal(p, quest.getStage(p));
					return true;
				}
				return false;
			}
			break;
		case 259:
			switch (button) {
			case 8:
				p.getInterfaceManager().openTab(2, new Component(274));
				return true;
			default:
				AchievementDiary diary = p.getAchievementDiaryManager().getDiary(DiaryType.forChild(button));
				if (diary != null) {
					diary.open(p);
				}
				return true;
			}
		}
		return true;
	}

}