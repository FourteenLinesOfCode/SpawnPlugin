package leonlpb.me.spawnPlugin.Listener;

import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class SettingsSpawnCommand implements CommandExecutor, TabExecutor {

    JavaPlugin plugin;

    public SettingsSpawnCommand(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {

        if(strings.length == 0){
            sender.sendMessage("§cUsage: /spawnsettings <option>");
            return true;
        }

        if(strings[0].equalsIgnoreCase("reload")){
            plugin.reloadConfig();
            sender.sendMessage("§aConfig reloaded.");
            return true;
        }

        if(strings[0].equalsIgnoreCase("first_join_only")){
            if(strings.length > 1){
                boolean enabled = Boolean.parseBoolean(strings[1]);
                plugin.getConfig().set("settings.first_join_only", enabled);
                plugin.saveConfig();
                sender.sendMessage("§aFirst join only set to " + enabled);
                return true;
            }
        }

        if(strings[0].equalsIgnoreCase("tp_on_join")){
            if(strings.length > 1){
                boolean enabled = Boolean.parseBoolean(strings[1]);
                plugin.getConfig().set("settings.tp_on_join", enabled);
                plugin.saveConfig();
                sender.sendMessage("§aTP on join set to " + enabled);
                return true;
            }
        }

        if(strings[0].equalsIgnoreCase("tp_command_enabled")){
            if(strings.length > 1){
                boolean enabled = Boolean.parseBoolean(strings[1]);
                plugin.getConfig().set("settings.tp_command_enabled", enabled);
                plugin.saveConfig();
                sender.sendMessage("§aTP command enabled set to " + enabled);
                return true;
            }
        }

        if(strings[0].equalsIgnoreCase("message")){
            if(strings.length > 1){
                String message = String.join(" ", Arrays.copyOfRange(strings,1,strings.length));
                plugin.getConfig().set("settings.message", message);
                plugin.saveConfig();
                sender.sendMessage("§aMessage updated.");
                return true;
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] strings) {

        if(strings.length == 1){
            return List.of("reload","first_join_only","tp_on_join","tp_command_enabled","message");
        }

        if(strings.length == 2 && !strings[0].equalsIgnoreCase("reload") && !strings[0].equalsIgnoreCase("message")){
            return List.of("true","false");
        }

        return List.of();
    }
}