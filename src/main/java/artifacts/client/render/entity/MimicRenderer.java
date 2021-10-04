package artifacts.client.render.entity;

import artifacts.Artifacts;
import artifacts.client.render.entity.model.MimicModel;
import artifacts.entity.MimicEntity;
import artifacts.init.ModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MimicRenderer extends MobRenderer<MimicEntity, MimicModel> {

    private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/mimic.png");

    public MimicRenderer(EntityRendererProvider.Context context) {
        super(context, new MimicModel(context.bakeLayer(ModelLayers.MIMIC)), 0.45F);
        addLayer(new MimicChestLayer(this));
    }

    @Override
    public void render(MimicEntity mimic, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        super.render(mimic, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(MimicEntity entity) {
        return TEXTURE;
    }
}
