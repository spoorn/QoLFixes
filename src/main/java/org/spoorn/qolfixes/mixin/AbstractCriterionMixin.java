package org.spoorn.qolfixes.mixin;

import com.google.common.collect.Sets;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.advancement.criterion.Criterion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spoorn.qolfixes.config.ModConfig;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(AbstractCriterion.class)
public class AbstractCriterionMixin<T extends AbstractCriterionConditions> {

    @Shadow @Final private Map<PlayerAdvancementTracker, Set<Criterion.ConditionsContainer<T>>> progressions;

    /**
     * Replaces the Set of Criterions with a ConcurrentHashSet to be thread safe.
     * 
     * @author spoorn
     * @reason https://github.com/Draylar/inmis/issues/117
     */
    @Overwrite
    public final void beginTrackingCondition(PlayerAdvancementTracker manager2, Criterion.ConditionsContainer<T> conditions) {
        if (ModConfig.get().useThreadSafeAbstractCriterionProgressions) {
            this.progressions.computeIfAbsent(manager2, manager -> ConcurrentHashMap.newKeySet()).add(conditions);
        } else {
            // Vanilla
            this.progressions.computeIfAbsent(manager2, manager -> Sets.newHashSet()).add(conditions);
        }
    }
}
