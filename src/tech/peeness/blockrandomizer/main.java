package tech.peeness.blockrandomizer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class main extends JavaPlugin {

    public FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {

        // Set up a config for the server if it doesn't have one.
        config.addDefault("WaitTime", 600);
        config.options().copyDefaults(true);
        saveConfig();


        // Runs the loop with the tick speed the player supplied in the Configuration File, once the player changes the config the server does need to be reloaded.
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                    // Basically grabs a random Item from the material list and gives it to the player
                    Random r = new Random();

                    Material material = null;

                    material = material.values()[r.nextInt(material.values().length)];

                    player.getInventory().addItem(new ItemStack(material, 1));
            }
        }, 20, config.getInt("WaitTime"));

    }

    @Override
    public void onDisable(){

    }
}
