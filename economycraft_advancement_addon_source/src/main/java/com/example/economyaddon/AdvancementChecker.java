
package com.example.economyaddon;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.util.Identifier;

public class AdvancementChecker {

    public static boolean hasAdvancement(ServerPlayerEntity player, String advancementId) {

        MinecraftServer server = player.getServer();
        if (server == null) return false;

        Advancement advancement =
                server.getAdvancementLoader().get(new Identifier(advancementId));

        if (advancement == null) return false;

        AdvancementProgress progress =
                player.getAdvancementTracker().getProgress(advancement);

        return progress.isDone();
    }
}
