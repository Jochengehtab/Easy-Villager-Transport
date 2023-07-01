package de.jochengehtab;

import de.jochengehtab.Controls.AddNewControl;
import de.jochengehtab.Events.PlayerBlockBreakEvents.After;
import de.jochengehtab.Events.PlayerBlockBreakEvents.Before;
import de.jochengehtab.Events.Sneaking;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("easy-villager-transport");
	public static final String MOD_ID = "easy-villager-transport";
	@Override
	public void onInitialize() {
		AddNewControl.init();
		AttackEntityCallback.EVENT.register(new Sneaking());
		PlayerBlockBreakEvents.AFTER.register(new After());
		PlayerBlockBreakEvents.BEFORE.register(new Before());
	}
}