package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import net.minecraft.block.Block;
import net.minecraft.block.WoolBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WoolBlock.class)
public class WoolBlockMixin extends Block {
    public WoolBlockMixin() {
        super(35, 64, Material.WOOL);
    }

    @Override
    public void onPlaced(World world, int x, int y, int z, LivingEntity arg2) {
        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            if (0xFFFF != ModHelper.ModHelperFields.WOOL_PLACED_BITFIELD) {
                if (null != arg2) {
                    if (arg2 instanceof PlayerEntity) {
                        if (this.id == Block.WOOL.id) {
                            int tileMeta = world.getBlockMeta(x, y, z);
                            int valueToCheck = (0x0001 << tileMeta);
                            if (valueToCheck != (valueToCheck & ModHelper.ModHelperFields.WOOL_PLACED_BITFIELD)) {
                                ModHelper.ModHelperFields.WOOL_TYPES_PLACED++;
                                ModHelper.ModHelperFields.WOOL_PLACED_BITFIELD |= valueToCheck;
                                ModHelper.ModHelperFields.Current404Score = ModHelper.calculate404ChallengeScore(world);
                            }

                            if (8 == ModHelper.ModHelperFields.WOOL_TYPES_PLACED) {
                                PlayerEntity player = PlayerHelper.getPlayerFromGame();
                                if (null != player) {
                                    player.incrementStat(Ways404Achievements.PLACE_8_TYPES_OF_WOOL);
                                }
                            }

                            if (16 == ModHelper.ModHelperFields.WOOL_TYPES_PLACED) {
                                PlayerEntity player = PlayerHelper.getPlayerFromGame();
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
