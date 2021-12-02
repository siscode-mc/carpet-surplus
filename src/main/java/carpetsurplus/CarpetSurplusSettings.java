package carpetsurplus;

import carpet.settings.Rule;
import static carpet.settings.RuleCategory.FEATURE;

public class CarpetSurplusSettings {
    public static final String SURPLUS = "surplus";

    @Rule(
            desc = "Dispensers interact with cauldrons",
            extra = "Dispensers now can interact with cauldrons",
            category = { FEATURE,SURPLUS }
    )
    public static boolean dispensersInteractCauldron = false;

    @Rule(
            desc = "Spawning rates no longer depend on the highest y-position in the column",
            extra = "Makes spawning always operate at the same rates as 1.17-era heightmap at 64.",
            category = {FEATURE, SURPLUS}
    )
    public static boolean heightmapIndependentSpawning = false;

    @Rule(
            desc = "Makes it so mobs no longer despawn from chunks which are loaded by some other method than a player",
            category = {FEATURE, SURPLUS})
    public static boolean localDespawning = false;

}
