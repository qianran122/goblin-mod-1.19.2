package top.qianran.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
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

public class GoblinEntity extends AnimalEntity implements IAnimatable, Monster {

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(GoblinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final Identifier ID = new Identifier("goblin-mod", "goblin");
    public GoblinEntity(EntityType<? extends AnimalEntity> entityType, World world) {
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
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1f);
    }

    protected void initGoals(){
        this.goalSelector.add(0, new SwimGoal(this));//游泳
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1f, false));//近战攻击
        this.goalSelector.add(3, new EscapeDangerGoal(this, 1.25D));//逃离危险
        //this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));//繁殖
        this.goalSelector.add(4,new WanderAroundFarGoal(this,1.0D));//游荡
        this.goalSelector.add(5, new LookAtEntityGoal(this, AnimalEntity.class, 6.0F));//看实体
        this.goalSelector.add(6, new LookAroundGoal(this));//看周围

        this.targetSelector.add(0, new ActiveTargetGoal<>(this, PlayerEntity.class, true));//玩家
    }
    //动画

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event){
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goblin.walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if(this.isAttacking()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goblin.attack", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goblin.stop", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(
                new AnimationController<GoblinEntity>(this, "controller",0,this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public boolean tryAttack(Entity target) {
        if(target instanceof PlayerEntity){
            boolean b = super.tryAttack(target);
            this.setAttack(b);
            return b;
        }
        this.setAttack(false);
        return false;
    }

    public void setAttack(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
        super.setAttacking(attacking);
    }
    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);
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
}
