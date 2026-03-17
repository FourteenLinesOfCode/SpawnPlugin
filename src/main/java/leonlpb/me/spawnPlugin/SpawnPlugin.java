package leonlpb.me.spawnPlugin;
import leonlpb.me.spawnPlugin.Listener.JoinListener;

import leonlpb.me.spawnPlugin.Listener.SetSpawnCommand;
import leonlpb.me.spawnPlugin.Listener.SettingsSpawnCommand;
import leonlpb.me.spawnPlugin.Listener.SpawnCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("settingsspawn").setExecutor(new SettingsSpawnCommand(this));
    }

    @Override
    public void onDisable() {

    }
}
