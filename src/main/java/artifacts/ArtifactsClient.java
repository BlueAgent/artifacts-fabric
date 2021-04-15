package artifacts;

import artifacts.client.render.ArtifactFeatureRenderer;
import artifacts.client.render.MimicRenderer;
import artifacts.init.Entities;
import artifacts.init.Items;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ArtifactsClient implements ClientModInitializer {

	private static final ModelIdentifier UMBRELLA_HELD_MODEL = new ModelIdentifier(
			new Identifier(Artifacts.MODID, "umbrella_in_hand"), "inventory");

	@Override
	public void onInitializeClient() {
		// Mimic EntityRenderer
		EntityRendererRegistry.INSTANCE.register(Entities.MIMIC, (dispatcher, context) -> new MimicRenderer(dispatcher));

		// Held Umbrella model
		ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(UMBRELLA_HELD_MODEL));

		// ModelPredicateProvider for rendering of umbrella blocking
		FabricModelPredicateProviderRegistry.register(Items.UMBRELLA, new Identifier("blocking"), (stack, world, entity)
				-> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0);

		LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper) -> {
			if (entityRenderer instanceof PlayerEntityRenderer) {
				registrationHelper.register(new ArtifactFeatureRenderer((PlayerEntityRenderer) entityRenderer));
			}
		});
	}
}
