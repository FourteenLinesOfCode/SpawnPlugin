package leonlpb.me.spawnPlugin.Listener;

import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class SettingsSpawnCommand implements CommandExecutor, TabExecutor {

    private final JavaPlugin plugin;

    public SettingsSpawnCommand(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {

        if (!commandSender.hasPermission("SpawnPlugin.settingsspawn")) {
            commandSender.sendMessage("§cNo permission.");
            return true;
        }

        if(strings.length == 0){
            commandSender.sendMessage("§cUsage: /spawnsettings <option>");
            return true;
        }

        switch (strings[0].toLowerCase()) {

            case "reload":
                plugin.reloadConfig();
                commandSender.sendMessage("§aConfig reloaded.");
                return true;

            case "first_join_only":
            case "tp_on_join":
            case "tp_command_enabled":
                if(strings.length < 2){
                    commandSender.sendMessage("§cUsage: /settingsspawn " + strings[0] + " <true/false>");
                    return true;
                }

                if (!strings[1].equalsIgnoreCase("true") && !strings[1].equalsIgnoreCase("false")) {
                    commandSender.sendMessage("§cUse true or false.");
                    return true;
                }

                boolean enabled = Boolean.parseBoolean(strings[1]);
                plugin.getConfig().set("settings." + strings[0].toLowerCase(), enabled);
                plugin.saveConfig();
                commandSender.sendMessage("§a" + strings[0] + " set to " + enabled);
                return true;

            case "message":
                if(strings.length < 2){
                    commandSender.sendMessage("§cUsage: /settingsspawn message <text>");
                    return true;
                }

                String message = String.join(" ", Arrays.copyOfRange(strings,1,strings.length));
                plugin.getConfig().set("settings.message", message);
                plugin.saveConfig();
                commandSender.sendMessage("§aMessage updated.");
                return true;

            case "world":
                if(strings.length < 2){
                    commandSender.sendMessage("§cUsage: /settingsspawn world <world>");
                    return true;
                }

                String world = strings[1];
                plugin.getConfig().set("settings.world", world);
                plugin.saveConfig();
                commandSender.sendMessage("§aWorld set to " + world);
                return true;
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] strings) {

        if(strings.length == 1){
            return List.of("reload","first_join_only","tp_on_join","tp_command_enabled","message","world");
        }

        if(strings.length == 2 &&
                !strings[0].equalsIgnoreCase("reload") &&
                !strings[0].equalsIgnoreCase("message") &&
                !strings[0].equalsIgnoreCase("world")){
            return List.of("true","false");
        }

        return List.of();
    }
}