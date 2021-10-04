package artifacts;

import artifacts.client.render.entity.MimicRenderer;
import artifacts.client.render.trinket.CurioRenderers;
import artifacts.init.Entities;
import artifacts.init.Items;
import artifacts.init.LayerDefinitions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;

@Environment(EnvType.CLIENT)
public class ArtifactsClient implements ClientModInitializer {

	private static final ModelResourceLocation UMBRELLA_HELD_MODEL = new ModelResourceLocation(Artifacts.id("umbrella_in_hand"), "inventory");

	@Override
	public void onInitializeClient() {
		// Mimic EntityRenderer
		EntityRendererRegistry.register(Entities.MIMIC, MimicRenderer::new);

		// Entity models
		LayerDefinitions.registerAll();
		ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new CurioRenderers());

		// Held Umbrella model
		ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(UMBRELLA_HELD_MODEL));

		// ModelPredicateProvider for rendering of umbrella blocking
		FabricModelPredicateProviderRegistry.register(Items.UMBRELLA, new ResourceLocation("blocking"), (stack, level, entity, i)
				-> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
	}
}
