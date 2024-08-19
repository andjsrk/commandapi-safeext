package io.github.andjsrk.commandapi.safeext

import dev.jorel.commandapi.kotlindsl.*
import dev.jorel.commandapi.wrappers.*
import dev.jorel.commandapi.wrappers.Rotation
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.*
import org.bukkit.advancement.Advancement
import org.bukkit.block.Biome
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.*
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.loot.LootTable
import org.bukkit.potion.PotionEffectType
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Team
import org.junit.jupiter.api.Test
import java.util.EnumSet
import java.util.UUID
import java.util.function.Predicate

class Test {
    @Test
    fun test() {
        // intentionally wrapped in a function to prevent actually running the code
        // we just want the build to succeed, no additional runtime checks needed
        fun test() {
            commandAPICommand("") {
                val int = validateBlockReceiverType { v -> safeIntegerArgument("", block = v) }
                val intRange = validateBlockReceiverType { v -> safeIntegerRangeArgument("", block = v) }
                val float = validateBlockReceiverType { v -> safeFloatArgument("", block = v) }
                val floatRange = validateBlockReceiverType { v -> safeFloatRangeArgument("", block = v) }
                val double = validateBlockReceiverType { v -> safeDoubleArgument("", block = v) }
                val long = validateBlockReceiverType { v -> safeLongArgument("", block = v) }
                val boolean = validateBlockReceiverType { v -> safeBooleanArgument("", block = v) }
                val string = validateBlockReceiverType { v -> safeStringArgument("", block = v) }
                val text = validateBlockReceiverType { v -> safeTextArgument("", block = v) }
                val greedyString = validateBlockReceiverType { v -> safeGreedyStringArgument("", block = v) }
                val location = validateBlockReceiverType { v -> safeLocationArgument("", block = v) }
                val location2D = validateBlockReceiverType { v -> safeLocation2DArgument("", block = v) }
                val rotation = validateBlockReceiverType { v -> safeRotationArgument("", block = v) }
                val axis = validateBlockReceiverType { v -> safeAxisArgument("", block = v) }
                val chatColor = validateBlockReceiverType { v -> safeChatColorArgument("", block = v) }
                val chatComponent = validateBlockReceiverType { v -> safeChatComponentArgument("", block = v) }
                val chat = validateBlockReceiverType { v -> safeChatArgument("", block = v) }
                val adventureChatColor = validateBlockReceiverType { v -> safeAdventureChatColorArgument("", block = v) }
                val adventureChatComponent = validateBlockReceiverType { v -> safeAdventureChatComponentArgument("", block = v) }
                val adventureChat = validateBlockReceiverType { v -> safeAdventureChatArgument("", block = v) }
                val entitySelectorOneEntity = validateBlockReceiverType { v -> safeEntitySelectorArgumentOneEntity("", block = v) }
                val entitySelectorManyEntities = validateBlockReceiverType { v -> safeEntitySelectorArgumentManyEntities("", block = v) }
                val entitySelectorOnePlayer = validateBlockReceiverType { v -> safeEntitySelectorArgumentOnePlayer("", block = v) }
                val entitySelectorManyPlayers = validateBlockReceiverType { v -> safeEntitySelectorArgumentManyPlayers("", block = v) }
                val player = validateBlockReceiverType { v -> safePlayerArgument("", block = v) }
                val offlinePlayer = validateBlockReceiverType { v -> safeOfflinePlayerArgument("", block = v) }
                val entityType = validateBlockReceiverType { v -> safeEntityTypeArgument("", block = v) }
                val scoreHolderSingle = validateBlockReceiverType { v -> safeScoreHolderArgumentSingle("", block = v) }
                val scoreHolderMultiple = validateBlockReceiverType { v -> safeScoreHolderArgumentMultiple("", block = v) }
                val scoreboardSlot = validateBlockReceiverType { v -> safeScoreboardSlotArgument("", block = v) }
                val objective = validateBlockReceiverType { v -> safeObjectiveArgument("", block = v) }
                val objectiveCriteria = validateBlockReceiverType { v -> safeObjectiveCriteriaArgument("", block = v) }
                val team = validateBlockReceiverType { v -> safeTeamArgument("", block = v) }
                val angle = validateBlockReceiverType { v -> safeAngleArgument("", block = v) }
                val advancement = validateBlockReceiverType { v -> safeAdvancementArgument("", block = v) }
                val biome = validateBlockReceiverType { v -> safeBiomeArgument("", block = v) }
                val biomeNamespacedKey = validateBlockReceiverType { v -> safeBiomeNamespacedKeyArgument("", block = v) }
                val blockState = validateBlockReceiverType { v -> safeBlockStateArgument("", block = v) }
                val command = validateBlockReceiverType { v -> safeCommandArgument("", block = v) }
                val enchantment = validateBlockReceiverType { v -> safeEnchantmentArgument("", block = v) }
                val itemStack = validateBlockReceiverType { v -> safeItemStackArgument("", block = v) }
                val lootTable = validateBlockReceiverType { v -> safeLootTableArgument("", block = v) }
                val mathOperation = validateBlockReceiverType { v -> safeMathOperationArgument("", block = v) }
                val namespacedKey = validateBlockReceiverType { v -> safeNamespacedKeyArgument("", block = v) }
                val particle = validateBlockReceiverType { v -> safeParticleArgument("", block = v) }
                val potionEffect = validateBlockReceiverType { v -> safePotionEffectArgument("", block = v) }
                val potionEffectNamespacedKey = validateBlockReceiverType { v -> safePotionEffectNamespacedKeyArgument("", block = v) }
                val recipe = validateBlockReceiverType { v -> safeRecipeArgument("", block = v) }
                val sound = validateBlockReceiverType { v -> safeSoundArgument("", block = v) }
                val soundNamespacedKey = validateBlockReceiverType { v -> safeSoundNamespacedKeyArgument("", block = v) }
                val time = validateBlockReceiverType { v -> safeTimeArgument("", block = v) }
                val uuid = validateBlockReceiverType { v -> safeUuidArgument("", block = v) }
                val world = validateBlockReceiverType { v -> safeWorldArgument("", block = v) }
                val blockPredicate = validateBlockReceiverType { v -> safeBlockPredicateArgument("", block = v) }
                val itemStackPredicate = validateBlockReceiverType { v -> safeItemStackPredicateArgument("", block = v) }
                val nbtCompound = validateBlockReceiverType { v -> safeNbtCompoundArgument<SomeType>("", block = v) }
                val literal = validateBlockReceiverType { v -> safeLiteralArgument("", "", block = v) }
                val literalWithoutExplicit = validateBlockReceiverType { v -> safeLiteralArgument("", block = v) }
                val multiLiteral = validateBlockReceiverType { v -> safeMultiLiteralArgument("", block = v) }
                val function = validateBlockReceiverType { v -> safeFunctionArgument("", block = v) }

                val optionalInt = validateBlockReceiverType { v -> safeIntegerArgument("", block = v).optional() }

                anyExecutor { _, args ->
                    val int: Int = args[int]
                    val intRange: IntegerRange = args[intRange]
                    val float: Float = args[float]
                    val floatRange: FloatRange = args[floatRange]
                    val double: Double = args[double]
                    val long: Long = args[long]
                    val boolean: Boolean = args[boolean]
                    val string: String = args[string]
                    val text: String = args[text]
                    val greedyString: String = args[greedyString]
                    val location: Location = args[location]
                    val location2D: Location2D = args[location2D]
                    val rotation: Rotation = args[rotation]
                    val axis: EnumSet<Axis> = args[axis]
                    val chatColor: ChatColor = args[chatColor]
                    val chatComponent: Array<out BaseComponent> = args[chatComponent]
                    val chat: Array<out BaseComponent> = args[chat]
                    val adventureChatColor: NamedTextColor = args[adventureChatColor]
                    val adventureChatComponent: Component = args[adventureChatComponent]
                    val adventureChat: Component = args[adventureChat]
                    val entitySelectorOneEntity: Entity = args[entitySelectorOneEntity]
                    val entitySelectorManyEntities: Collection<Entity> = args[entitySelectorManyEntities]
                    val entitySelectorOnePlayer: Player = args[entitySelectorOnePlayer]
                    val entitySelectorManyPlayers: Collection<Player> = args[entitySelectorManyPlayers]
                    val player: Player = args[player]
                    val offlinePlayer: OfflinePlayer = args[offlinePlayer]
                    val entityType: EntityType = args[entityType]
                    val scoreHolderSingle: String = args[scoreHolderSingle]
                    val scoreHolderMultiple: Collection<String> = args[scoreHolderMultiple]
                    val scoreboardSlot: ScoreboardSlot = args[scoreboardSlot]
                    val objective: Objective = args[objective]
                    val objectiveCriteria: String = args[objectiveCriteria]
                    val team: Team = args[team]
                    val angle: Float = args[angle]
                    val advancement: Advancement = args[advancement]
                    val biome: Biome = args[biome]
                    val biomeNamespacedKey: NamespacedKey = args[biomeNamespacedKey]
                    val blockState: BlockData = args[blockState]
                    val command: CommandResult = args[command]
                    val enchantment: Enchantment = args[enchantment]
                    val itemStack: ItemStack = args[itemStack]
                    val lootTable: LootTable = args[lootTable]
                    val mathOperation: MathOperation = args[mathOperation]
                    val namespacedKey: NamespacedKey = args[namespacedKey]
                    val particle: ParticleData<*> = args[particle]
                    val potionEffect: PotionEffectType = args[potionEffect]
                    val potionEffectNamespacedKey: NamespacedKey = args[potionEffectNamespacedKey]
                    val recipe: Recipe = args[recipe]
                    val sound: Sound = args[sound]
                    val soundNamespacedKey: NamespacedKey = args[soundNamespacedKey]
                    val time: Time = args[time]
                    val uuid: UUID = args[uuid]
                    val world: World = args[world]
                    val blockPredicate: Predicate<Block> = args[blockPredicate]
                    val itemStackPredicate: Predicate<ItemStack> = args[itemStackPredicate]
                    val nbtCompound: SomeType = args[nbtCompound]
                    val literal: String = args[literal]
                    val literalWithoutExplicit: String = args[literalWithoutExplicit]
                    val multiLiteral: String = args[multiLiteral]
                    val function: NamespacedKey = args[function]

                    val optionalInt: Int? = args[optionalInt]
                    // TODO: prove `optionalInt` is nullable *at compile time*
                }
            }
        }
    }
}

private object SomeType

private inline fun <Arg, SafeArg: SafeArgument<Arg, *>> validateBlockReceiverType(block: (v: Arg.() -> Unit) -> SafeArg) =
    block {}
