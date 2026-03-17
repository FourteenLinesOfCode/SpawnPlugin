package leonlpb.me.spawnPlugin.Listener;

import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SetSpawnCommand implements CommandExecutor, TabExecutor {

    double x;
    double y;
    double z;

    float yaw;
    float pitch;

    String world;

    JavaPlugin plugin;

    public SetSpawnCommand(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if(strings.length < 0 && !(commandSender instanceof Player)){
            return false;
        }
        else if(strings.length == 5){
             x = Double.parseDouble(strings[0]);
             y = Double.parseDouble(strings[1]);
             z = Double.parseDouble(strings[2]);

             yaw = Float.parseFloat(strings[3]);
             pitch = Float.parseFloat(strings[4]);
             world = strings[5];

            plugin.getConfig().set("settings.x", x);
            plugin.getConfig().set("settings.y", y);
            plugin.getConfig().set("settings.z", z);
            plugin.getConfig().set("settings.yaw", yaw);
            plugin.getConfig().set("settings.pitch", pitch);
            plugin.getConfig().set("settings.world", world);

            plugin.saveConfig();
        }
        else {
            Player player = (Player) commandSender;
            Location loc = player.getLocation();

             x = loc.x();
             y = loc.y();
             z = loc.z();

             yaw = loc.getYaw();
             pitch = loc.getPitch();

             world = loc.getWorld().getName();

             plugin.getConfig().set("settings.x", x);
             plugin.getConfig().set("settings.y", y);
             plugin.getConfig().set("settings.z", z);
             plugin.getConfig().set("settings.yaw", yaw);
             plugin.getConfig().set("settings.pitch", pitch);
             plugin.getConfig().set("settings.world", world);

             plugin.saveConfig();

        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return List.of();
    }

}
