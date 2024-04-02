package top.qianran;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class GoblinModClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("goblin-mod", "cube"), "main");

	//在客户端初始化类中使用 EntityRendererRegistry 来注册这个渲染器：

		@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ScreenRegistry.register(GoblinMod.BOX_SCREEN_HANDLER, BoxScreen::new);
		/*
		 * 方块实体渲染器的注册，提供模型、阴影大小和纹理的渲染器。
		 *
		 * 实体渲染器也可以在实体基于上下文进行渲染前(EndermanEntityRenderer#render). 操作模型。
		 */
		 EntityRendererRegistry.register(GoblinMod.CUBE, (context) -> {
			 return new CubeEntityRenderer(context);
		 });
	}
}