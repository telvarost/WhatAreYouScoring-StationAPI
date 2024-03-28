package com.github.telvarost.whatareyouscoring.events;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import net.modificationstation.stationapi.api.event.container.slot.ItemUsedInCraftingEvent;
import org.spongepowered.asm.mixin.Unique;

import static java.lang.Math.floor;

public class ItemUsedInCraftingListener {

    /**
     * @param event Item used in crafting event which fires whenever an item is consumed in crafting or an item is crafted
     */
    @EventListener
    public void combineDurability(ItemUsedInCraftingEvent event) {
        if (Config.config.CHALLENGE_404_SCORING_ENABLED) {
            if (  (0 == event.itemOrdinal)
               && (null != event.itemCrafted)
            ) {
                if (0x00C0 != ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD) {
                    if (event.itemCrafted.itemId == ItemBase.arrow.id) {
                        int arrowIncrement = 0x007F & ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD;
                        if (64 > arrowIncrement) {
                            arrowIncrement = arrowIncrement + 4;
                            ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD &= 0x0080;
                            ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD |= arrowIncrement;
                        }

                        if (0x00C0 == ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD) {
                            PlayerBase player = PlayerHelper.getPlayerFromGame();
                            player.incrementStat(Ways404Achievements.CRAFT_BOW_AND_ARROWS);
                        }
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.bow.id) {
                        ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD |= 0x0080;

                        if (0x00C0 == ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD) {
                            PlayerBase player = PlayerHelper.getPlayerFromGame();
                            player.incrementStat(Ways404Achievements.CRAFT_BOW_AND_ARROWS);
                        }
                        return;
                    }
                }

                if (0x003F != ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD) {
                    if (event.itemCrafted.itemId == BlockBase.LAPIS_LAZULI_BLOCK.asItem().id) {
                        ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD |= 0x0001;
                        PlayerBase player = PlayerHelper.getPlayerFromGame();
                        player.incrementStat(Ways404Achievements.CRAFT_LAPIS_LAZULI_BLOCK);
                        return;
                    } else if (event.itemCrafted.itemId == BlockBase.IRON_BLOCK.asItem().id) {
                        ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD |= 0x0002;
                        PlayerBase player = PlayerHelper.getPlayerFromGame();
                        player.incrementStat(Ways404Achievements.CRAFT_IRON_BLOCK);
                        return;
                    } else if (event.itemCrafted.itemId == BlockBase.GOLD_BLOCK.asItem().id) {
                        ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD |= 0x0004;
                        PlayerBase player = PlayerHelper.getPlayerFromGame();
                        player.incrementStat(Ways404Achievements.CRAFT_GOLD_BLOCK);
                        return;
                    } else if (event.itemCrafted.itemId == BlockBase.DIAMOND_BLOCK.asItem().id) {
                        ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD |= 0x0008;
                        PlayerBase player = PlayerHelper.getPlayerFromGame();
                        player.incrementStat(Ways404Achievements.CRAFT_DIAMOND_BLOCK);
                        return;
                    } else if (event.itemCrafted.itemId == BlockBase.JACK_O_LANTERN.asItem().id) {
                        ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD |= 0x0010;
                        PlayerBase player = PlayerHelper.getPlayerFromGame();
                        player.incrementStat(Ways404Achievements.CRAFT_JACK_O_LANTERN);
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.bread.id) {
                        ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD |= 0x0020;
                        PlayerBase player = PlayerHelper.getPlayerFromGame();
                        player.incrementStat(Ways404Achievements.CRAFT_BREAD);
                        return;
                    }
                }

                if (0xFFFF != ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD) {
                    if (event.itemCrafted.itemId == ItemBase.leatherHelmet.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0008;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.leatherChestplate.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0004;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.leatherLeggings.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0002;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.leatherBoots.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0001;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.ironHelmet.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0080;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.ironChestplate.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0040;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.ironLeggings.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0020;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.ironBoots.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0010;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.goldHelmet.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0800;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.goldChestplate.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0400;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.goldLeggings.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0200;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.goldBoots.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x0100;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.diamondHelmet.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x8000;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.diamondChestplate.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x4000;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.diamondLeggings.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x2000;
                        checkForArmorSetComplete();
                        return;
                    } else if (event.itemCrafted.itemId == ItemBase.diamondBoots.id) {
                        ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD |= 0x1000;
                        checkForArmorSetComplete();
                        return;
                    }
                }
            }
        }
    }

    private void checkForArmorSetComplete() {
        PlayerBase player = PlayerHelper.getPlayerFromGame();

        if (0x000F == (0x000F & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            player.incrementStat(Ways404Achievements.LEATHER_ARMOR);
        } else if (0x00F0 == (0x00F0 & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            player.incrementStat(Ways404Achievements.IRON_ARMOR);
        } else if (0x0F00 == (0x0F00 & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            player.incrementStat(Ways404Achievements.GOLD_ARMOR);
        } else if (0xF000 == (0xF000 & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            player.incrementStat(Ways404Achievements.DIAMOND_ARMOR);
        }
    }
}