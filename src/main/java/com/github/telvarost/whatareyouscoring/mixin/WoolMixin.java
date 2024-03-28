package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import net.minecraft.block.BlockBase;
import net.minecraft.block.Wool;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Wool.class)
public class WoolMixin extends BlockBase {
    public WoolMixin() {
        super(35, 64, Material.WOOL);
    }

    @Override
    public void afterPlaced(Level arg, int i, int j, int k, Living arg2) {
        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            if (0xFFFF != ModHelper.ModHelperFields.WOOL_PLACED_BITFIELD) {
                if (null != arg2) {
                    if (arg2 instanceof PlayerBase) {
                        if (this.id == BlockBase.WOOL.id) {
                            int tileMeta = arg.getTileMeta(i, j, k);
                            int valueToCheck = (0x0001 << tileMeta);
                            if (valueToCheck != (valueToCheck & ModHelper.ModHelperFields.WOOL_PLACED_BITFIELD)) {
                                ModHelper.ModHelperFields.WOOL_TYPES_PLACED++;
                                ModHelper.ModHelperFields.WOOL_PLACED_BITFIELD |= valueToCheck;
                            }

                            if (8 == ModHelper.ModHelperFields.WOOL_TYPES_PLACED) {
                                PlayerBase player = PlayerHelper.getPlayerFromGame();
                                if (null != player) {
                                    player.incrementStat(Ways404Achievements.PLACE_8_TYPES_OF_WOOL);
                                }
                            }

                            if (16 == ModHelper.ModHelperFields.WOOL_TYPES_PLACED) {
                                PlayerBase player = PlayerHelper.getPlayerFromGame();
                                if (null != player) {
                                    player.incrementStat(Ways404Achievements.PLACE_ALL_TYPES_OF_WOOL);
                                }
                            }
                            return;
                        }
                    }
                }
            }
        }
    }
}
