package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.ModHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(
            method = "createOrLoadWorld",
            at = @At("HEAD")
    )
    public void createOrLoadWorld(String string, String string2, long l, CallbackInfo ci) {
        ModHelper.resetFieldsOnDeath(null, true);
    }
}
