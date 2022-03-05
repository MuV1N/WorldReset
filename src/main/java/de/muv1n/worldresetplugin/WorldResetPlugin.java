package de.muv1n.worldresetplugin;

import de.muv1n.worldresetplugin.commands.ResetCommand;
import de.muv1n.worldresetplugin.commands.tabcompleter.ResetCommandTab;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class WorldResetPlugin extends JavaPlugin {
    public static WorldResetPlugin instance;

    @Override
    public void onLoad() {
        instance = this;
        saveConfig();
        if (!getConfig().contains("isReset")){
            getConfig().set("isReset", false);
            saveConfig();
            return;
        }
        if (getConfig().getBoolean("isReset")){
            try {
                File world = new File(Bukkit.getWorldContainer(), "world");
                File nether = new File(Bukkit.getWorldContainer(), "world_nether");
                File end = new File(Bukkit.getWorldContainer(), "world_the_end");
                FileUtils.deleteDirectory(world);
                FileUtils.deleteDirectory(nether);
                FileUtils.deleteDirectory(end);

                world.mkdirs();
                nether.mkdirs();
                end.mkdirs();

                new File(world, "data").mkdirs();
                new File(world, "datapacks").mkdirs();
                new File(world, "playerdata").mkdirs();
                new File(world, "poi").mkdirs();
                new File(world, "region").mkdirs();

                new File(nether, "data").mkdirs();
                new File(nether, "datapacks").mkdirs();
                new File(nether, "playerdata").mkdirs();
                new File(nether, "poi").mkdirs();
                new File(nether, "region").mkdirs();

                new File(end, "data").mkdirs();
                new File(end, "datapacks").mkdirs();
                new File(end, "playerdata").mkdirs();
                new File(end, "poi").mkdirs();
                new File(end, "region").mkdirs();

            } catch (IOException e) {
                e.printStackTrace();
            }

            getConfig().set("isReset", false);
            saveConfig();
        }
    }

    @Override
    public void onEnable() {
        getCommand("reset").setExecutor(new ResetCommand());
        getCommand("reset").setTabCompleter(new ResetCommandTab());
    }
    @Override
    public void onDisable() {
    }
}
