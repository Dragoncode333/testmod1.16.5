package net.hamnd.testmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.awt.*;

public class JumpyBlock extends Block {

    public JumpyBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        // Server: Main hand & Off hand
        // Client: Main hand & Off hand
        if(!world.isClientSide() && hand == Hand.MAIN_HAND) {
            playerEntity.sendMessage(new StringTextComponent("Right clicked this !"), playerEntity.getUUID());
        }
        return super.use(blockState, world, blockPos, playerEntity, hand, blockRayTraceResult);
    }

    @Override
    public void stepOn(World world, BlockPos blockPos, Entity entity) {
        if(entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new EffectInstance(Effects.JUMP, 200));
        }

        super.stepOn(world, blockPos, entity);
    }
}