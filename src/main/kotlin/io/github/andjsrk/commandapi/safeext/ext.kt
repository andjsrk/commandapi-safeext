package io.github.andjsrk.commandapi.safeext

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.*
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.kotlindsl.*
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.function.Predicate

inline operator fun <reified R> CommandArguments.get(arg: SafeArgument<out Argument<*>, R>) =
    get(arg.index) as R

private fun <R, Arg: Argument<*>> newRequiredSafeArgument(
    args: MutableList<Argument<*>>,
    arg: Arg,
    _returnTypeInferResult: TypeContainer<Pair<Arg, R>>? = null,
    // here, Pair is used to associate the argument with `Arg` so no explicit generic is needed
) =
    run {
        args.add(arg)
        RequiredSafeArgument<Arg, R>(arg, args.lastIndex)
    }

// Note: `inferReturn` should be preferred over `inferSimpleReturn` whenever possible
private inline fun <Arg: SafeOverrideableArgument<*, R>, R> inferReturn() = TypeContainer<Pair<Arg, R>>()
private inline fun <Arg: Argument<R>, R> inferSimpleReturn() = TypeContainer<Pair<Arg, R>>()

/**
 * @see CommandAPICommand.integerArgument
 */
fun CommandAPICommand.safeIntegerArgument(name: String, min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE, block: IntegerArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, IntegerArgument(name, min, max).apply(block), inferReturn())
/**
 * @see CommandAPICommand.integerRangeArgument
 */
fun CommandAPICommand.safeIntegerRangeArgument(name: String, block: IntegerRangeArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, IntegerRangeArgument(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.floatArgument
 */
fun CommandAPICommand.safeFloatArgument(name: String, min: Float = -Float.MAX_VALUE, max: Float = Float.MAX_VALUE, block: FloatArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, FloatArgument(name, min, max).apply(block), inferReturn())
/**
 * @see CommandAPICommand.floatRangeArgument
 */
fun CommandAPICommand.safeFloatRangeArgument(name: String, block: FloatRangeArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, FloatRangeArgument(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.doubleArgument
 */
fun CommandAPICommand.safeDoubleArgument(name: String, min: Double = -Double.MAX_VALUE, max: Double = Double.MAX_VALUE, block: DoubleArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, DoubleArgument(name, min, max).apply(block), inferReturn())

/**
 * @see CommandAPICommand.longArgument
 */
fun CommandAPICommand.safeLongArgument(name: String, min: Long = Long.MIN_VALUE, max: Long = Long.MAX_VALUE, block: LongArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, LongArgument(name, min, max).apply(block), inferReturn())

/**
 * @see CommandAPICommand.booleanArgument
 */
fun CommandAPICommand.safeBooleanArgument(name: String, block: BooleanArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, BooleanArgument(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.stringArgument
 */
fun CommandAPICommand.safeStringArgument(name: String, block: StringArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, StringArgument(name).apply(block), inferSimpleReturn())
/**
 * @see CommandAPICommand.textArgument
 */
fun CommandAPICommand.safeTextArgument(name: String, block: TextArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, TextArgument(name).apply(block), inferSimpleReturn())
/**
 * @see CommandAPICommand.greedyStringArgument
 */
fun CommandAPICommand.safeGreedyStringArgument(name: String, block: GreedyStringArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, GreedyStringArgument(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.locationArgument
 */
fun CommandAPICommand.safeLocationArgument(name: String, locationType: LocationType = LocationType.PRECISE_POSITION, centerPosition: Boolean = true, block: LocationArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, LocationArgument(name, locationType, centerPosition).apply(block), inferReturn())
/**
 * @see CommandAPICommand.location2DArgument
 */
fun CommandAPICommand.safeLocation2DArgument(name: String, locationType: LocationType = LocationType.PRECISE_POSITION, centerPosition: Boolean = true, block: Location2DArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, Location2DArgument(name, locationType, centerPosition).apply(block), inferReturn())
/**
 * @see CommandAPICommand.rotationArgument
 */
fun CommandAPICommand.safeRotationArgument(name: String, block: RotationArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, RotationArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.axisArgument
 */
fun CommandAPICommand.safeAxisArgument(name: String, block: AxisArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, AxisArgument(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.chatColorArgument
 */
fun CommandAPICommand.safeChatColorArgument(name: String, block: ChatColorArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, ChatColorArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.chatComponentArgument
 */
fun CommandAPICommand.safeChatComponentArgument(name: String, block: ChatComponentArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, ChatComponentArgument(name).apply(block), inferSimpleReturn())
/**
 * @see CommandAPICommand.chatArgument
 */
fun CommandAPICommand.safeChatArgument(name: String, block: ChatArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, ChatArgument(name).apply(block), inferSimpleReturn())
/**
 * @see CommandAPICommand.adventureChatColorArgument
 */
fun CommandAPICommand.safeAdventureChatColorArgument(name: String, block: AdventureChatColorArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, AdventureChatColorArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.adventureChatComponentArgument
 */
fun CommandAPICommand.safeAdventureChatComponentArgument(name: String, block: AdventureChatComponentArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, AdventureChatComponentArgument(name).apply(block), inferSimpleReturn())
/**
 * @see CommandAPICommand.adventureChatArgument
 */
fun CommandAPICommand.safeAdventureChatArgument(name: String, block: AdventureChatArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, AdventureChatArgument(name).apply(block), inferSimpleReturn())

/**
 * @see CommandAPICommand.entitySelectorArgumentOneEntity
 */
fun CommandAPICommand.safeEntitySelectorArgumentOneEntity(name: String, block: EntitySelectorArgument.OneEntity.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, EntitySelectorArgument.OneEntity(name).apply(block), inferSimpleReturn())
/**
 * @see CommandAPICommand.entitySelectorArgumentManyEntities
 */
fun CommandAPICommand.safeEntitySelectorArgumentManyEntities(name: String, allowEmpty: Boolean = true, block: EntitySelectorArgument.ManyEntities.() -> Unit = {}) =
    newRequiredSafeArgument<Collection<Entity>, _>(arguments, EntitySelectorArgument.ManyEntities(name, allowEmpty).apply(block))
/**
 * @see CommandAPICommand.entitySelectorArgumentOnePlayer
 */
fun CommandAPICommand.safeEntitySelectorArgumentOnePlayer(name: String, block: EntitySelectorArgument.OnePlayer.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, EntitySelectorArgument.OnePlayer(name).apply(block), inferSimpleReturn())
/**
 * @see CommandAPICommand.entitySelectorArgumentManyPlayers
 */
fun CommandAPICommand.safeEntitySelectorArgumentManyPlayers(name: String, allowEmpty: Boolean = true, block: EntitySelectorArgument.ManyPlayers.() -> Unit = {}) =
    newRequiredSafeArgument<Collection<Player>, _>(arguments, EntitySelectorArgument.ManyPlayers(name, allowEmpty).apply(block))
/**
 * @see CommandAPICommand.playerArgument
 */
fun CommandAPICommand.safePlayerArgument(name: String, block: PlayerArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, PlayerArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.offlinePlayerArgument
 */
fun CommandAPICommand.safeOfflinePlayerArgument(name: String, block: OfflinePlayerArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, OfflinePlayerArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.entityTypeArgument
 */
fun CommandAPICommand.safeEntityTypeArgument(name: String, block: EntityTypeArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, EntityTypeArgument(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.scoreHolderArgumentSingle
 */
fun CommandAPICommand.safeScoreHolderArgumentSingle(name: String, block: ScoreHolderArgument.Single.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, ScoreHolderArgument.Single(name).apply(block), inferSimpleReturn())
/**
 * @see CommandAPICommand.scoreHolderArgumentMultiple
 */
fun CommandAPICommand.safeScoreHolderArgumentMultiple(name: String, block: ScoreHolderArgument.Multiple.() -> Unit = {}) =
    newRequiredSafeArgument<Collection<String>, _>(arguments, ScoreHolderArgument.Multiple(name).apply(block))
/**
 * @see CommandAPICommand.scoreboardSlotArgument
 */
fun CommandAPICommand.safeScoreboardSlotArgument(name: String, block: ScoreboardSlotArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, ScoreboardSlotArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.objectiveArgument
 */
fun CommandAPICommand.safeObjectiveArgument(name: String, block: ObjectiveArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, ObjectiveArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.objectiveCriteriaArgument
 */
fun CommandAPICommand.safeObjectiveCriteriaArgument(name: String, block: ObjectiveCriteriaArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, ObjectiveCriteriaArgument(name).apply(block), inferSimpleReturn())
/**
 * @see CommandAPICommand.teamArgument
 */
fun CommandAPICommand.safeTeamArgument(name: String, block: TeamArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, TeamArgument(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.angleArgument
 */
fun CommandAPICommand.safeAngleArgument(name: String, block: AngleArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, AngleArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.advancementArgument
 */
fun CommandAPICommand.safeAdvancementArgument(name: String, block: AdvancementArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, AdvancementArgument(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.biomeArgument
 * @see CommandAPICommand.safeBiomeNamespacedKeyArgument
 */
fun CommandAPICommand.safeBiomeArgument(name: String, block: BiomeArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, BiomeArgument(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.biomeArgument
 * @see CommandAPICommand.safeBiomeArgument
 */
fun CommandAPICommand.safeBiomeNamespacedKeyArgument(name: String, block: BiomeArgument.NamespacedKey.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, BiomeArgument.NamespacedKey(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.blockStateArgument
 */
fun CommandAPICommand.safeBlockStateArgument(name: String, block: BlockStateArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, BlockStateArgument(name).apply(block), inferSimpleReturn())
/**
 * @see CommandAPICommand.commandArgument
 */
fun CommandAPICommand.safeCommandArgument(name: String, block: CommandArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, CommandArgument(name).apply(block), inferSimpleReturn())
/**
 * @see CommandAPICommand.enchantmentArgument
 */
fun CommandAPICommand.safeEnchantmentArgument(name: String, block: EnchantmentArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, EnchantmentArgument(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.itemStackArgument
 */
fun CommandAPICommand.safeItemStackArgument(name: String, block: ItemStackArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, ItemStackArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.lootTableArgument
 */
fun CommandAPICommand.safeLootTableArgument(name: String, block: LootTableArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, LootTableArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.mathOperationArgument
 */
fun CommandAPICommand.safeMathOperationArgument(name: String, block: MathOperationArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, MathOperationArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.namespacedKeyArgument
 */
fun CommandAPICommand.safeNamespacedKeyArgument(name: String, block: NamespacedKeyArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, NamespacedKeyArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.particleArgument
 */
fun CommandAPICommand.safeParticleArgument(name: String, block: ParticleArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, ParticleArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.potionEffectArgument
 * @see CommandAPICommand.safePotionEffectNamespacedKeyArgument
 */
fun CommandAPICommand.safePotionEffectArgument(name: String, block: PotionEffectArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, PotionEffectArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.potionEffectArgument
 * @see CommandAPICommand.safePotionEffectArgument
 */
fun CommandAPICommand.safePotionEffectNamespacedKeyArgument(name: String, block: PotionEffectArgument.NamespacedKey.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, PotionEffectArgument.NamespacedKey(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.recipeArgument
 */
fun CommandAPICommand.safeRecipeArgument(name: String, block: RecipeArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, RecipeArgument(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.soundArgument
 * @see CommandAPICommand.safeSoundNamespacedKeyArgument
 */
fun CommandAPICommand.safeSoundArgument(name: String, block: SoundArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, SoundArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.soundArgument
 * @see CommandAPICommand.safeSoundArgument
 */
fun CommandAPICommand.safeSoundNamespacedKeyArgument(name: String, block: SoundArgument.NamespacedKey.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, SoundArgument.NamespacedKey(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.timeArgument
 */
fun CommandAPICommand.safeTimeArgument(name: String, block: TimeArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, TimeArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.uuidArgument
 */
fun CommandAPICommand.safeUuidArgument(name: String, block: UUIDArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, UUIDArgument(name).apply(block), inferReturn())
/**
 * @see CommandAPICommand.worldArgument
 */
fun CommandAPICommand.safeWorldArgument(name: String, block: WorldArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, WorldArgument(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.blockPredicateArgument
 */
fun CommandAPICommand.safeBlockPredicateArgument(name: String, block: BlockPredicateArgument.() -> Unit = {}) =
    newRequiredSafeArgument<Predicate<Block>, _>(arguments, BlockPredicateArgument(name).apply(block))
/**
 * @see CommandAPICommand.itemStackPredicateArgument
 */
fun CommandAPICommand.safeItemStackPredicateArgument(name: String, block: ItemStackPredicateArgument.() -> Unit = {}) =
    newRequiredSafeArgument<Predicate<ItemStack>, _>(arguments, ItemStackPredicateArgument(name).apply(block))

/**
 * @see CommandAPICommand.nbtCompoundArgument
 */
fun <NBTContainer> CommandAPICommand.safeNbtCompoundArgument(name: String, block: NBTCompoundArgument<NBTContainer>.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, NBTCompoundArgument<NBTContainer>(name).apply(block), inferReturn())

/**
 * @see CommandAPICommand.literalArgument
 */
fun CommandAPICommand.safeLiteralArgument(literal: String, block: LiteralArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, LiteralArgument.of(literal, literal).apply(block), inferSimpleReturn())
/**
 * @see CommandAPICommand.literalArgument
 */
fun CommandAPICommand.safeLiteralArgument(name: String, literal: String, block: LiteralArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, LiteralArgument.of(name, literal).apply(block), inferSimpleReturn())

/**
 * @see CommandAPICommand.multiLiteralArgument
 */
fun CommandAPICommand.safeMultiLiteralArgument(name: String, vararg literals: String, block: MultiLiteralArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, MultiLiteralArgument(name, *literals).apply(block), inferSimpleReturn())

/**
 * @see CommandAPICommand.functionArgument
 */
fun CommandAPICommand.safeFunctionArgument(name: String, block: FunctionArgument.() -> Unit = {}) =
    newRequiredSafeArgument(arguments, FunctionArgument(name).apply(block), inferReturn())
