package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.entity.player.StationFlatteningPlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements Inventory, StationFlatteningPlayerInventory {

    @Shadow public ItemStack[] armor;

    @Shadow public PlayerEntity player;

    @Inject(
            method = "getTotalArmorDurability",
            at = @At("HEAD")
    )
    public void miscTweaks_getArmourValue(CallbackInfoReturnable<Integer> cir) {
        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            if (ModHelper.ModHelperFields.HAS_PLAYER_WORN_ARMOR != (ModHelper.ModHelperFields.HAS_PLAYER_WORN_ARMOR & ModHelper.ModHelperFields.OTHER_BITFIELD)) {
                for (int var4 = 0; var4 < this.armor.length; ++var4) {
                    if (this.armor[var4] != null && this.armor[var4].getItem() instanceof ArmorItem) {
                        ModHelper.ModHelperFields.OTHER_BITFIELD |= ModHelper.ModHelperFields.HAS_PLAYER_WORN_ARMOR;
                        this.player.incrementStat(Ways404Achievements.NEVER_WEAR_ARMOR);
                    }
                }
            }
        }
    }
}