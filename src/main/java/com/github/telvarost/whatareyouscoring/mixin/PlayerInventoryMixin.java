package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.armour.Armour;
import net.modificationstation.stationapi.api.entity.player.StationFlatteningPlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements InventoryBase, StationFlatteningPlayerInventory {

    @Shadow public ItemInstance[] armour;

    @Shadow public PlayerBase player;

    @Inject(
            method = "getArmourValue",
            at = @At("HEAD")
    )
    public void miscTweaks_getArmourValue(CallbackInfoReturnable<Integer> cir) {
        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            if (false == ModHelper.ModHelperFields.HAS_PLAYER_WORN_ARMOR) {
                for (int var4 = 0; var4 < this.armour.length; ++var4) {
                    if (this.armour[var4] != null && this.armour[var4].getType() instanceof Armour) {
                        ModHelper.ModHelperFields.HAS_PLAYER_WORN_ARMOR = true;
                        this.player.incrementStat(Ways404Achievements.NEVER_WEAR_ARMOR);
                    }
                }
            }
        }
    }
}