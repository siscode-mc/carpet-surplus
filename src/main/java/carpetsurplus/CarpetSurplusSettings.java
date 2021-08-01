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

}
