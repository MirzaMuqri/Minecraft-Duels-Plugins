package xyz.mukri.duels.file;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Pig;
import xyz.mukri.duels.Core;
import xyz.mukri.duels.arena.Arena;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static xyz.mukri.duels.Core.*;

public class ArenaFile {

    public File file;
    public FileConfiguration config;

    public ArenaFile() {
        file = new File(Core.getInstance().getDataFolder(), "arena.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    public boolean isFileExists() {
        if (file.exists())
            return true;

        return false;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void createNewFile() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            file.createNewFile();

            config.set("arena", new ArrayList<>());
            //config.set("arena.test.name", "test");
            //config.set("arena.test.spawn", "NONE");

        } catch (IOException e) {
            e.printStackTrace();
        }

        save();
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewArena(String name) {
        // TODO: add 1 to total arena and save everything.

        config.set("arena." + name + ".name", name);
        config.set("arena." + name + ".spawn", "NONE");

        save();
    }

    public void setArenaSpawn(Location loc, Arena arena) {
        String location = getInstance().locationToString(loc);

        config.set("arena." + arena.getArenaName() + ".spawn", location);
    }

}

