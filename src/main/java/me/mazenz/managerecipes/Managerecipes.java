package me.mazenz.managerecipes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public final class Managerecipes extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults(true);
        saveConfig();

        Logger logger = this.getLogger();
        new UpdateChecker(this, 88223).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("The latest version of ManageRecipes is running (0.1)");
            } else {
                logger.info("There is a new version of ManageRecipes.");
            }
        });

        for (String RecipeName : Objects.requireNonNull(getConfig().getConfigurationSection("recipes")).getKeys(true)) {
            List<String> ingredients = getConfig().getStringList("recipes." + RecipeName);

            ItemStack i = new ItemStack(Material.valueOf(RecipeName));

            NamespacedKey key = new NamespacedKey(this, "recipe" + RecipeName);

            ShapedRecipe recipe = new ShapedRecipe(key, i);

            recipe.shape(
                    "ABC",
                    "DEF",
                    "GHI"
            );

            recipe.setIngredient('A', Material.valueOf(ingredients.get(0)));
            recipe.setIngredient('B', Material.valueOf(ingredients.get(1)));
            recipe.setIngredient('C', Material.valueOf(ingredients.get(2)));
            recipe.setIngredient('D', Material.valueOf(ingredients.get(3)));
            recipe.setIngredient('E', Material.valueOf(ingredients.get(4)));
            recipe.setIngredient('F', Material.valueOf(ingredients.get(5)));
            recipe.setIngredient('G', Material.valueOf(ingredients.get(6)));
            recipe.setIngredient('H', Material.valueOf(ingredients.get(7)));
            recipe.setIngredient('I', Material.valueOf(ingredients.get(8)));

            Bukkit.addRecipe(recipe);

        }

        System.out.println(ChatColor.RED + "ManageRecipes Enabled");
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "ManageRecipes Disabled");
    }
}
