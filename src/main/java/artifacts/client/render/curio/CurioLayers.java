package artifacts.client.render.curio;

import artifacts.Artifacts;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class CurioLayers {

    public static final ModelLayerLocation MIMIC = createLocation("mimic");
    public static final ModelLayerLocation MIMIC_OVERLAY = createLocation("mimic", "overlay");

    public static final ModelLayerLocation DRINKING_HAT = createLocation("drinking_hat");
    public static final ModelLayerLocation SNORKEL = createLocation("snorkel");
    public static final ModelLayerLocation NIGHT_VISION_GOGGLES = createLocation("night_vision_goggles");
    public static final ModelLayerLocation SUPERSTITIOUS_HAT = createLocation("superstitious_hat");
    public static final ModelLayerLocation VILLAGER_HAT = createLocation("villager_hat");

    public static final ModelLayerLocation SCARF = createLocation("scarf");
    public static final ModelLayerLocation CROSS_NECKLACE = createLocation("cross_necklace");
    public static final ModelLayerLocation PANIC_NECKLACE = createLocation("panic_necklace");
    public static final ModelLayerLocation PENDANT = createLocation("pendant");
    public static final ModelLayerLocation CHARM_OF_SINKING = createLocation("charm_of_sinking");

    public static final ModelLayerLocation CLOUD_IN_A_BOTTLE = createLocation("cloud_in_a_bottle");
    public static final ModelLayerLocation OBSIDIAN_SKULL = createLocation("obsidian_skull");
    public static final ModelLayerLocation ANTIDOTE_VESSEL = createLocation("antidote_vessel");
    public static final ModelLayerLocation UNIVERSAL_ATTRACTOR = createLocation("universal_attractor");
    public static final ModelLayerLocation CRYSTAL_HEART = createLocation("crystal_heart");
    public static final ModelLayerLocation HELIUM_FLAMINGO = createLocation("helium_flamingo");

    public static final ModelLayerLocation CLAWS = createLocation("claws");
    public static final ModelLayerLocation SLIM_CLAWS = createLocation("slim_claws");
    public static final ModelLayerLocation GLOVE = createLocation("gloves");
    public static final ModelLayerLocation SLIM_GLOVE = createLocation("slim_gloves");
    public static final ModelLayerLocation GOLDEN_HOOK = createLocation("golden_hook");
    public static final ModelLayerLocation SLIM_GOLDEN_HOOK = createLocation("slim_golden_hook");

    public static final ModelLayerLocation AQUA_DASHERS = createLocation("aqua_dashers");
    public static final ModelLayerLocation BUNNY_HOPPERS = createLocation("bunny_hoppers");
    public static final ModelLayerLocation KITTY_SLIPPERS = createLocation("kitty_slippers");
    public static final ModelLayerLocation RUNNING_SHOES = createLocation("running_shoes");
    public static final ModelLayerLocation STEADFAST_SPIKES = createLocation("steadfast_spikes");
    public static final ModelLayerLocation FLIPPERS = createLocation("flippers");

    public static final ModelLayerLocation WHOOPEE_CUSHION = createLocation("whoopee_cushion");

    private static ModelLayerLocation createLocation(String model) {
        return createLocation(model, "main");
    }

    @SuppressWarnings("SameParameterValue")
    private static ModelLayerLocation createLocation(String model, String layer) {
        return new ModelLayerLocation(new ResourceLocation(Artifacts.MODID, model), layer);
    }

    public static ModelLayerLocation claws(boolean smallArms) {
        return smallArms ? SLIM_CLAWS : CLAWS;
    }

    public static ModelLayerLocation glove(boolean smallArms) {
        return smallArms ? SLIM_GLOVE : GLOVE;
    }

    public static ModelLayerLocation goldenHook(boolean smallArms) {
        return smallArms ? SLIM_GOLDEN_HOOK : GOLDEN_HOOK;
    }
}
