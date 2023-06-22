package de.jochengehtab;

import de.jochengehtab.Events.Sneaking;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;

public class ExampleMod implements ModInitializer {
	@Override
	public void onInitialize() {
		AttackEntityCallback.EVENT.register(new Sneaking());
	}
}