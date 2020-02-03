package net.eq2online.macros.core.mixin;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.client.gui.inventory.GuiEditSign;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiEditSign.class })
public interface IGuiEditSign
{
    @Accessor("tileSign")
    TileEntitySign getSign();
}