package artifacts.common.item.curio.hands;

import artifacts.common.events.LivingEntityDamagedCallback;
import artifacts.common.init.ModItems;
import artifacts.common.item.curio.CurioItem;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class VampiricGloveItem extends CurioItem {

	public VampiricGloveItem() {
        LivingEntityDamagedCallback.EVENT.register(VampiricGloveItem::onLivingDamage);
	}

	private static void onLivingDamage(LivingEntity entity, DamageSource source, float amount) {
		if (source.getEntity() instanceof LivingEntity attacker) {
			Entity src = source.getDirectEntity();
			float damageDealt = Math.min(amount, entity.getHealth());
			if (src == attacker && damageDealt > 4 && TrinketsHelper.isEquipped(ModItems.VAMPIRIC_GLOVE, attacker)) {
				attacker.heal(Math.min(2, amount / 4));
			}
		}
	}
}
