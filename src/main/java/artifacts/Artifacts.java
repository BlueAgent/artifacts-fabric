package artifacts;

import artifacts.client.render.MimicRenderer;
import artifacts.common.config.Config;
import artifacts.common.init.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod("artifacts")
@SuppressWarnings("unused")
public class Artifacts {

    public static final String MODID = "artifacts";

    public static final ItemGroup CREATIVE_TAB = new ItemGroup(MODID) {
        @Override
        @Environment(EnvType.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(Items.PLASTIC_DRINKING_HAT);
        }
    };

    public Artifacts() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void commonSetup(FMLCommonSetupEvent event) {
            Features.addFeatures();
        }

        @SubscribeEvent
        public static void setupClient(final FMLClientSetupEvent event) {
            RenderingRegistry.registerEntityRenderingHandler(Entities.MIMIC, MimicRenderer::new);
            ModelPredicateProviderRegistry.register(Items.UMBRELLA, new Identifier("blocking"), (stack, world, entity) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0);
        }

        @SubscribeEvent
        public static void enqueueIMC(final InterModEnqueueEvent event) {
            SlotTypePreset[] Types = {SlotTypePreset.HEAD, SlotTypePreset.NECKLACE, SlotTypePreset.BELT};
            for (SlotTypePreset type : Types) {
                InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> type.getMessageBuilder().build());
            }
            InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HANDS.getMessageBuilder().size(2).build());
            InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("feet").priority(220).icon(PlayerScreenHandler.EMPTY_BOOTS_SLOT_TEXTURE).build());
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            Items.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
            Entities.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
            SoundEvents.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerLootModifiers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            LootModifiers.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
            Features.registerFeatures(event.getRegistry());
        }
    }
}
