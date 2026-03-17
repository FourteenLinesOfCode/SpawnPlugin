package leonlpb.me.spawnPlugin.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpawnCommand implements CommandExecutor, TabExecutor {

    JavaPlugin plugin;

    public SpawnCommand(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("Only player can use this command!");
            return false;
        }
        if(strings.length < 0){
            return false;
        }
        boolean enabled = plugin.getConfig().getBoolean("settings.tp_command_enabled");
        if(!enabled){
            commandSender.sendMessage("the /spawn command is disabled");
            return true;
        }
        Player player = (Player) commandSender;
        teleportPlayer(player);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return List.of();
    }

    private void teleportPlayer(Player player){
        double x = plugin.getConfig().getDouble("settings.x");
        double y = plugin.getConfig().getDouble("settings.y");
        double z = plugin.getConfig().getDouble("settings.z");
        float yaw = (float) plugin.getConfig().getDouble("settings.yaw");
        float pitch = (float) plugin.getConfig().getDouble("settings.pitch");

        World world = Bukkit.getWorlds().getFirst(); // fixed
        Location location = new Location(world, x, y, z, yaw, pitch);
        player.teleport(location);
    }
}
