package com.github.telvarost.whatareyouscoring.events;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import net.modificationstation.stationapi.api.event.container.slot.ItemUsedInCraftingEvent;

public class ItemUsedInCraftingListener {

    /**
     * @param event Item used in crafting event which fires whenever an item is consumed in crafting or an item is crafted
     */
    @EventListener
    public void combineDurability(ItemUsedInCraftingEvent event) {
        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            if (  (0 == event.itemOrdinal)
               && (null != event.itemCrafted)
            ) {
                if (0x00C0 != ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD) {
                    if (event.itemCrafted.itemId == Item.ARROW.id) {
                        int arrowIncrement = 0x007F & ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD;
                        if (64 > arrowIncrement) {
                            arrowIncrement = arrowIncrement + 4;
                            ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD &= 0x0080;
                            ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD |= arrowIncrement;
                        }

                        if (0x00C0 == ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD) {
                            PlayerEntity player = PlayerHelper.getPlayerFromGame();
                            player.incrementStat(Ways404Achievements.CRAFT_BOW_AND_ARROWS);
                        }
                        return;
                    } else if (event.itemCrafted.itemId == Item.BOW.id) {
                        ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD |= 0x0080;

                        if (0x00C0 == ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD) {
                            PlayerEntity player = PlayerHelper.getPlayerFromGame();
                            player.incrementStat(Ways404Achievements.CRAFT_BOW_AND_ARROWS);
                        }
                        return;
                    }
                }

                if (0x003F != ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD) {
                    if (event.itemCrafted.itemId == Block.LAPIS_BLOCK.asItem().id) {
                        ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD |= 0x0001;
                        PlayerEntity player = PlayerHelper.getPlayerFromGame();
                        player.incrementStat(Ways404Achievements.CRAFT_LAPIS_LAZULI_BLOCK);
                        return;
                    } else if (event.itemCrafted.itemId == Block.IRON_BLOCK.asItem().id) {
                        ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD |= 0x0002;
                        PlayerEntity player = PlayerHelper.getPlayerFromGame();
                        player.incrementStat(Ways404Achievements.CRAFT_IRON_BLOCK);
                        return;
                    } else if (event.itemCrafted.itemId == Block.GOLD_BLOCK.asItem().id) {
                        ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD |= 0x0004;
                        PlayerEntity player = PlayerHelper.getPlayerFromGame();
                        player.incrementStat(Ways404Achievements.CRAFT_GOLD_BLOCK);
                        return;
                    } else if (event.itemCrafted.itemId == Block.DIAMOND_BLOCK.asItem().id) {
                        ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD |= 0x0008;
                        PlayerEntity player = PlayerHelper.getPlayerFromGame();
                        player.incrementStat(Ways404Achievements.CRAFT_DIAMOND_BLOCK);
                        return;
                    } else if (event.itemCrafted.itemId == Block.JACK_O_LANTERN.asItem().id) {
                        ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD |= 0x0010;
                        PlayerEntity player = PlayerHelper.getPlayerFromGame();
                        player.incrementStat(Ways404Achievements.CRAFT_JACK_O_LANTERN);
                        return;
                    } else if (event.itemCrafted.itemId == Item.BREAD.id) {
                        ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD |= 0x0020;
                        PlayerEntity player = PlayerHelper.getPlayerFromGame();
                        player.incrementStat(Ways404Achievements.CRAFT_BREAD);
                        return;
                    }
                }

                if (0xFFFF != ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD) {
                    if (event.itemCrafted.itemId == Item.LEATHER_HELMET.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0008;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.LEATHER_CHESTPLATE.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0004;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.LEATHER_LEGGINGS.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0002;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.LEATHER_BOOTS.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0001;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.IRON_HELMET.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0080;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.IRON_CHESTPLATE.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0040;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.IRON_LEGGINGS.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0020;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.IRON_BOOTS.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0010;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.GOLDEN_HELMET.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0800;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.GOLDEN_CHESTPLATE.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0400;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.GOLDEN_LEGGINGS.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0200;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.GOLDEN_BOOTS.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0100;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.DIAMOND_HELMET.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x8000;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.DIAMOND_CHESTPLATE.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x4000;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.DIAMOND_LEGGINGS.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x2000;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == Item.DIAMOND_BOOTS.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x1000;
                        checkForArmorSetComplete();
                        return;
                    }
                }
            }
        }
    }

    private void checkForArmorSetComplete() {
        PlayerEntity player = PlayerHelper.getPlayerFromGame();

        if (0x000F == (0x000F & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            player.incrementStat(Ways404Achievements.LEATHER_ARMOR);
        }

        if (0x00F0 == (0x00F0 & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            player.incrementStat(Ways404Achievements.IRON_ARMOR);
        }

        if (0x0F00 == (0x0F00 & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            player.incrementStat(Ways404Achievements.GOLD_ARMOR);
        }

        if (0xF000 == (0xF000 & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            player.incrementStat(Ways404Achievements.DIAMOND_ARMOR);
        }
    }
}