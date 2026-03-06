package org.btwr.sturdy_trees.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.particle.ParticleTypes;

public class CindersParticle extends SpriteBillboardParticle {

    private final float initialScale;

    protected CindersParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);

        // Motion adjustments (BTW style)
        this.velocityX *= 1.5;
        this.velocityY = this.random.nextDouble() * 0.4 + 0.05;
        this.velocityZ *= 1.5;

        // White color
        this.red = 1.0f;
        this.green = 1.0f;
        this.blue = 1.0f;

        // Scale randomization
        this.scale *= this.random.nextFloat() * 2.0f + 0.2f;
        this.initialScale = this.scale;

        // Lifetime
        this.maxAge = (int)(16.0 / (this.random.nextDouble() * 0.8 + 0.2));

        // Gravity
        this.gravityStrength = 0.03f;

        // Enable collision
        this.collidesWithWorld = true;
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;

        if (this.age++ >= this.maxAge) {
            this.markDead();
            return;
        }

        float ageFraction = (float)this.age / (float)this.maxAge;

        // Occasionally spawn smoke like BTW
        if (this.random.nextFloat() > ageFraction) {
            this.world.addParticle(
                    ParticleTypes.SMOKE,
                    this.x,
                    this.y,
                    this.z,
                    this.velocityX,
                    this.velocityY,
                    this.velocityZ
            );
        }

        // Gravity
        this.velocityY -= this.gravityStrength;

        // Move with collision
        this.move(this.velocityX, this.velocityY, this.velocityZ);

        // Drag
        this.velocityX *= 0.999;
        this.velocityY *= 0.999;
        this.velocityZ *= 0.999;

        // Ground friction
        if (this.onGround) {
            this.velocityX *= 0.7;
            this.velocityZ *= 0.7;
        }

        // Quadratic shrink over lifetime
        this.scale = this.initialScale * (1.0f - ageFraction * ageFraction);
    }

    // Fullbright rendering (like old 240 light value)
    @Override
    protected int getBrightness(float tint) {
        return 0xF000F0;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    // Factory
    public static class Factory implements ParticleFactory<SimpleParticleType> {

        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type,
                                       ClientWorld world,
                                       double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {

            CindersParticle particle = new CindersParticle(world, x, y, z);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}
