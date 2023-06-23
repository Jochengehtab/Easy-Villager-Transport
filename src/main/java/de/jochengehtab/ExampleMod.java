package de.jochengehtab;

import de.jochengehtab.Events.Sneaking;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;

public class ExampleMod implements ModInitializer {
	public static final String MOD_ID = "easy-villager-transport";
	@Override
	public void onInitialize() {
		AttackEntityCallback.EVENT.register(new Sneaking());
	}
}