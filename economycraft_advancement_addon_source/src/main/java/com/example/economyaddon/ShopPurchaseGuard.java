
package com.example.economyaddon;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.Map;

/*
 This class simulates a guard that checks if a player can buy an item.
 In a real EconomyCraft integration you would call this method inside
 the shop purchase event.
*/

public class ShopPurchaseGuard {

    private static final Map<String, String> ITEM_REQUIREMENTS = new HashMap<>();

    public static void init() {

        // Example requirements
        ITEM_REQUIREMENTS.put("minecraft:diamond_sword", "economyaddon:level_3");
        ITEM_REQUIREMENTS.put("minecraft:netherite_sword", "economyaddon:level_5");

    }

    public static boolean canBuy(ServerPlayerEntity player, ItemStack stack) {

        String itemId = stack.getItem().toString();

        if (!ITEM_REQUIREMENTS.containsKey(itemId)) {
            return true;
        }

        String advancement = ITEM_REQUIREMENTS.get(itemId);

        boolean unlocked =
                AdvancementChecker.hasAdvancement(player, advancement);

        if (!unlocked) {

            player.sendMessage(
                Text.literal("§cBạn chưa mở khóa cấp độ để mua vật phẩm này!"),
                false
            );

            return false;
        }

        return true;
    }
}
