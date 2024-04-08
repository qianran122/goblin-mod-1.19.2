package top.qianran;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import top.qianran.commands.NbtCommand;
import top.qianran.entity.ModEntity;
import top.qianran.entity.cube.CubeEntityModel;
import top.qianran.entity.cube.CubeEntityRenderer;
import top.qianran.entity.goblin.GoblinEntityRenderer;
import top.qianran.entity.shit.ShitEntityRenderer;
import top.qianran.entity.shita.ShitAEntityRenderer;
import top.qianran.screen.BoxScreen;
import top.qianran.screen.*;
import top.qianran.util.ModBlocks;

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

		// 注册实体
		EntityRendererRegistry.register(ModEntity.SHIT_ENTITY, ShitEntityRenderer::new);
		EntityRendererRegistry.register(ModEntity.SHITA_ENTITY, ShitAEntityRenderer::new);
		EntityRendererRegistry.register(ModEntity.GOBLIN_ENTITY, GoblinEntityRenderer::new);

		//注册Screen
		ScreenRegistry.register(ModBlocks.UI_BLOCK_SCREEN_HANDLER, UIBlockScreen::new);
		ScreenRegistry.register(ModBlocks.FERMENTATION_VESSELS_BLOCK_SCREEN_HANDLER, fermentationVesselsBlockScreen::new);

		//command
		CommandRegistrationCallback.EVENT.register(
				((dispatcher, registryAccess, environment) -> NbtCommand.register(dispatcher))
		);

		//不完整方块
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MYSTERIOUS_LEAVES, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MYSTERIOUS_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MYSTERIOUS_FLOWER, RenderLayer.getCutout());
	}
}