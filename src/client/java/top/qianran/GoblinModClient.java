package top.qianran;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import top.qianran.entity.ModEntity;
import top.qianran.entity.cube.CubeEntityModel;
import top.qianran.entity.cube.CubeEntityRenderer;
import top.qianran.entity.shit.AAALERenderer;
import top.qianran.entity.shit.ShitEntityRenderer;

@Environment(EnvType.CLIENT)
public class GoblinModClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("goblin-mod", "cube"), "main");

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ScreenRegistry.register(GoblinMod.BOX_SCREEN_HANDLER, BoxScreen::new);

		/*
		 * 方块实体渲染器的注册，提供模型、阴影大小和纹理的渲染器。
		 *
		 * 实体渲染器也可以在实体基于上下文进行渲染前(EndermanEntityRenderer#render). 操作模型。
		 */
		// In 1.17, use EntityRendererRegistry.register (seen below) instead of EntityRendererRegistry.INSTANCE.register (seen above)
		EntityRendererRegistry.register(GoblinMod.CUBE, (context) -> {
			return new CubeEntityRenderer(context);
		});

		EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, CubeEntityModel::getTexturedModelData);

		// 注册shit
		EntityRendererRegistry.register(ModEntity.SHIT_ENTITY, ShitEntityRenderer::new);
		EntityRendererRegistry.register(ModEntity.AAA_ENTITY, AAALERenderer::new);
	}
}