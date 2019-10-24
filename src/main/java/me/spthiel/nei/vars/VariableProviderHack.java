package me.spthiel.nei.vars;

import com.mumfrey.liteloader.core.LiteLoader;
import me.spthiel.nei.ModuleInfo;
import me.spthiel.nei.actions.ScriptActionHack;
import net.eq2online.macros.core.Macros;
import net.eq2online.macros.scripting.api.APIVersion;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.macros.scripting.variable.VariableCache;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import java.io.File;

@APIVersion(ModuleInfo.API_VERSION)
public class VariableProviderHack extends VariableCache {
    
    private World lastWorld = null;
    
    @Override
    public void updateVariables(boolean clock) {
        if (!clock) {
            return;
        }
        
        this.storeVariable("HACKED", ScriptActionHack.hacked);
        this.storeVariable("MODULENEI", true);
        this.storeVariable("MINECRAFTDIR", LiteLoader.getGameDirectory().getAbsolutePath() + File.separator);
        this.storeVariable("MACROSCONFIGDIR", (Macros.getInstance().getMacrosDirectory().getAbsolutePath()));
        this.storeVariable("FILESEPARATOR", File.separator);
    
        World theWorld = Minecraft.getMinecraft().world;
        if(!(lastWorld == null && theWorld == null) && ((lastWorld == null || theWorld == null) || !lastWorld.getWorldInfo().getWorldName().equalsIgnoreCase(theWorld.getWorldInfo().getWorldName()))) {
        
            ScriptActionHack.hack();
            lastWorld = theWorld;
        }
    }

    @Override
    public Object getVariable(String variableName) {
        return this.getCachedValue(variableName);
    }

    @Override
    public void onInit() {

        ScriptContext.MAIN.getCore().registerVariableProvider(this);
    }

}
