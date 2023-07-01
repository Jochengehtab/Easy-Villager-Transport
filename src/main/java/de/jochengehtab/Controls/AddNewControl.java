package de.jochengehtab.Controls;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class AddNewControl {
    public static boolean allowed;
    private static KeyBinding keyBinding;

    public static void init() {
        File file;
        if (!Files.exists(Path.of("EasyVillagerTransportConfig.txt"))) {
            file = new File("EasyVillagerTransportConfig.txt");
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                bufferedWriter.write("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file = new File("EasyVillagerTransportConfig.txt");
            StringBuilder result = new StringBuilder();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            allowed = Boolean.parseBoolean(result.toString());
        }
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "only mine adult plants",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                "Easy Villager Transportation"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                StringBuilder result = new StringBuilder();
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                allowed = !Boolean.parseBoolean(result.toString());
                Objects.requireNonNull(client.player).sendMessage(Text.literal(!result.toString().equals("true") ? "You have enabled 'only mine adult plants'." : "You have disabled 'only mine adult plants'."), false);
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                    bufferedWriter.write("");
                    bufferedWriter.flush();
                    if (result.toString().equals("true")) {
                        bufferedWriter.write("false");
                    } else {
                        bufferedWriter.write("true");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
