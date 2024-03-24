package com.github.telvarost.whatareyouscoring.mixin;

import net.minecraft.inventory.InventoryBase;
import net.minecraft.tileentity.TileEntityBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.item.ItemInstance;

@Mixin(TileEntityFurnace.class)
public abstract class FurnaceEntityMixin extends TileEntityBase implements InventoryBase {

    @Shadow
    private ItemInstance[] inventory;

    @Shadow protected abstract boolean canAcceptRecipeOutput();

    @Inject(method = "craftRecipe", at = @At(value = "HEAD"), cancellable = true)
    public void whatAreYouScoring_craftRecipe(CallbackInfo ci) {
        if (this.canAcceptRecipeOutput()) {
            /** - Check for scoring */
        }
    }
}
