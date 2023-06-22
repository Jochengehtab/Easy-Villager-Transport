package de.jochengehtab;

import de.jochengehtab.Events.Sneaking;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("easy-villager-transport");

	@Override
	public void onInitialize() {
		AttackEntityCallback.EVENT.register(new Sneaking());
	}
}