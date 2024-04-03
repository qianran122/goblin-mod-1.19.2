package top.qianran.entity.custom;

import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class ShitAEntity extends TameableEntity implements IAnimatable {

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final TrackedData<Boolean> SITTING = DataTracker.registerData(ShitAEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public ShitAEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    //设置属性
    public static DefaultAttributeContainer.Builder setAttributes(){
        return AnimalEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2f);
    }

    protected void initGoals(){
        this.goalSelector.add(0, new SwimGoal(this));//游泳
        this.goalSelector.add(3, new EscapeDangerGoal(this, 1.25D));//逃离危险
        //this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));//繁殖
        this.goalSelector.add(4,new WanderAroundFarGoal(this,1.0D));//游荡
        this.goalSelector.add(5, new LookAtEntityGoal(this, AnimalEntity.class, 6.0F));//看实体
        this.goalSelector.add(6, new LookAroundGoal(this));//看周围
    }
    //动画

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event){
        if(event.isMoving()){
            event.getController().setAnimation(
                    new AnimationBuilder().addAnimation("animation.shita.walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(
                new AnimationBuilder().addAnimation("animation.shita.stop", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(
                new AnimationController<ShitAEntity>(this, "controller",0,this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_FOX_AMBIENT;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_FOX_HURT;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_FOX_DEATH;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();
        Item itemForTaming = Items.CARROT;
        if(item == itemForTaming && !isTamed()){
            if(this.world.isClient){
                return ActionResult.CONSUME;
            } else {
                if(!player.getAbilities().creativeMode){
                    stack.decrement(1);
                }
                if(!this.world.isClient){
                    setOwner(player);
                    this.navigation.recalculatePath();
                    this.setTarget(null);
                    this.world.sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES);
                }
                return ActionResult.SUCCESS;
            }
        }
        return super.interactMob(player, hand);
    }
}
