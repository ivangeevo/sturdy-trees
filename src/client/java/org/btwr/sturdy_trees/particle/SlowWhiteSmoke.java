package org.btwr.sturdy_trees.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

// TODO: Finish implementing this particle (fcwhitesmoke in btw)
//  Has some weird behavior where it flickers weirdly, either in the particle itself or how it's made to display
public class SlowWhiteSmoke extends SpriteBillboardParticle {

    protected float smokeParticleScale;
    private final SpriteProvider spriteProvider;

    protected SlowWhiteSmoke(
        ClientWorld world,
        double x, double y, double z,
        double velocityX, double velocityY, double velocityZ,
        float scaleMultiplier,
        SpriteProvider spriteProvider
    ) {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.spriteProvider = spriteProvider;

        // Base color (light gray / white)
        // Old code: 1.0F - random * 0.3F
        float component = 1.0F - (float)(this.random.nextDouble() * 0.3D);
        this.red = this.green = this.blue = component;

        // Initial motion (scaled down, then add incoming velocities)
        this.velocityX *= 0.1D;
        this.velocityY *= 0.1D;
        this.velocityZ *= 0.1D;

        this.velocityX += velocityX;
        this.velocityY += velocityY;
        this.velocityZ += velocityZ;

        // Scale & lifetime
        float f = 2.5F;
        this.scale *= 0.75F * scaleMultiplier;
        this.scale *= f;
        this.smokeParticleScale = this.scale;

        // Old formula: 8 / (rand * 0.8 + 0.3), then * f
        this.maxAge = (int)(8.0D / (this.random.nextDouble() * 0.8D + 0.3D));
        this.maxAge = (int)(this.maxAge * f);

        // Collisions like the original (set to false there)
        this.collidesWithWorld = false;

        // Slight upward bias like old motionY += 0.004
        // We'll reproduce that each tick instead of here.
        this.setSpriteForAge(this.spriteProvider);
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

        // Texture index based on age (7 - age * 8 / maxAge)
        // Assumes your sprite sheet has 8 frames (0..7)
        float lifeFrac = (float)this.age / (float)this.maxAge;
        int frame = 7 - (int)(lifeFrac * 8.0F);
        if (frame < 0) frame = 0;
        this.setSprite(this.spriteProvider.getSprite(frame, 8)); // 8 frames total

        // Upward drift (old: motionY += 0.004)
        this.velocityY += 0.004D;

        // Move
        this.move(this.velocityX, this.velocityY, this.velocityZ);

        // If we didn't move vertically, increase horizontal spread
        if (this.y == this.prevPosY) {
            this.velocityX *= 1.1D;
            this.velocityZ *= 1.1D;
        }

        // Damping (old: *= 0.96 on all components)
        this.velocityX *= 0.96D;
        this.velocityY *= 0.96D;
        this.velocityZ *= 0.96D;

        // Extra horizontal damping on ground (old: *= 0.7)
        if (this.onGround) {
            this.velocityX *= 0.7D;
            this.velocityZ *= 0.7D;
        }

        // Scale evolution: f = ((age + partialTicks) / maxAge) * 32 clamped [0, 1]
        // In render, that value multiplied smokeParticleScale.
        float scaleFactor = ((float)this.age / (float)this.maxAge) * 32.0F;
        if (scaleFactor < 0.0F) {
            scaleFactor = 0.0F;
        }
        if (scaleFactor > 1.0F) {
            scaleFactor = 1.0F;
        }
        this.scale = this.smokeParticleScale * scaleFactor;
    }

	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
    public int getBrightness(float tint) {
        // Optional: brighten smoke slightly; otherwise, just use default
        return super.getBrightness(tint);
    }

    @Override
    public SpriteBillboardParticle scale(float scale) {
        return (SpriteBillboardParticle)super.scale(scale);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(
            SimpleParticleType type,
            ClientWorld world,
            double x, double y, double z,
            double velocityX, double velocityY, double velocityZ
        ) {
            return new SlowWhiteSmoke(world, x, y, z, velocityX, velocityY, velocityZ, 1.0F, this.spriteProvider);
        }
    }
}