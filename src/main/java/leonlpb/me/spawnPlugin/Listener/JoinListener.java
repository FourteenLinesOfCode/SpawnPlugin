package leonlpb.me.spawnPlugin.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinListener implements Listener {

    private final JavaPlugin plugin;

    public JoinListener(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        boolean firstJoinOnly = plugin.getConfig().getBoolean("settings.first_join_only");
        boolean enabled = plugin.getConfig().getBoolean("settings.tp_on_join");

        if (firstJoinOnly && !player.hasPlayedBefore() && enabled){
            teleportPlayer(player);
            sendWelcome(player);
        }
        else if (!firstJoinOnly && enabled){
            teleportPlayer(player);
            sendWelcome(player);
        }
    }

    private void sendWelcome(Player player){
        String msg = plugin.getConfig().getString("settings.message", "Welcome!");
        player.sendMessage(msg.replace("{player}", player.getName()));
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