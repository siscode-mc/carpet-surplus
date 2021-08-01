package carpetsurplus;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;

public class CarpetSurplusServer implements CarpetExtension, ModInitializer {
    @Override
    public void onInitialize() {
        CarpetSurplusServer.loadExtension();
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetSurplusSettings.class);
    }

    @Override
    public String version() {
        return "carpet-surplus";
    }

    public static void loadExtension(){
        CarpetServer.manageExtension(new CarpetSurplusServer());
    }
}
