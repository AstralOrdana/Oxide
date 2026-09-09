package com.ordana.oxide.reg;

import com.ordana.oxide.Oxide;
import com.ordana.oxide.entities.FallingCementEntity;
import com.ordana.oxide.entities.RustyNailEntity;
import com.ordana.oxide.entities.SprayParticleEntity;
import com.ordana.oxide.items.SFStackView;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class ModEntities {

    public static void init() {
    }

    public static final Supplier<EntityDataSerializer<SFStackView>> FLUID_DATA = RegHelper.registerEntityDataSerializer(
            Oxide.res("fluid_data"), () -> EntityDataSerializer.forValueType(SFStackView.STREAM_CODEC));

    public static final Supplier<EntityType<RustyNailEntity>> RUSTY_NAIL = registerEntityType(
            Oxide.res("rusty_nail"),
            RustyNailEntity::new, MobCategory.MISC, 0.3F, 0.3F, 10, 20);

    public static final Supplier<EntityType<SprayParticleEntity>> SPRAY_ENTITY = registerEntityType(
            Oxide.res("spray_entity"),
            SprayParticleEntity::new, MobCategory.MISC, 0.3F, 0.3F, 10, 20);

    public static final Supplier<EntityType<FallingCementEntity>> FALLING_CEMENT_ENTITY = registerEntityType(
            Oxide.res("falling_cement_entity"),
            FallingCementEntity::new, MobCategory.MISC, 0.98F, 0.98F, 10, 20);

	public static <T extends Entity> RegSupplier<EntityType<T>> registerEntityType(Identifier name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height, int clientTrackingRange, int updateInterval) {
		return RegHelper.registerEntityType(name, EntityType.Builder.of(factory, category).clientTrackingRange(clientTrackingRange).updateInterval(updateInterval).sized(width, height));
	}


}
