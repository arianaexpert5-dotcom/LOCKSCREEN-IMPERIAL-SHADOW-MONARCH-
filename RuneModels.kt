package com.ege.lockscreen.model

import androidx.compose.ui.graphics.Color
import com.ege.lockscreen.theme.EgeCyan
import com.ege.lockscreen.theme.EgePurple
import com.ege.lockscreen.theme.EgeCrimson
import com.ege.lockscreen.theme.EgeGold
import com.ege.lockscreen.theme.EgeGlacier
import kotlin.random.Random

data class RuneItem(
    val digit: Int,
    val glyph: String,
    val title: String,
    val rank: String,
    val element: String,
    val power: Int,
    val description: String
)

data class ThemeConfig(
    val id: String,
    val name: String,
    val primaryColor: Color,
    val secondaryColor: Color,
    val auraColor: Color,
    val characterName: String,
    val characterSubtitle: String
)

val DEFAULT_RUNES = listOf(
    RuneItem(1, "ᛁ", "IL • MONARCH", "S-Rank", "Shadow Flame", 999, "The primordial sovereign spark that commands the legion."),
    RuneItem(2, "ᛝ", "YI • DUALITY", "A-Rank", "Ethereal Blade", 850, "Twin daggers resonating with abyssal frequency."),
    RuneItem(3, "ᛟ", "SAM • DOMAIN", "S-Rank", "Territory of Monarch", 940, "Expands shadow territory across all dimensional boundaries."),
    RuneItem(4, "ᚦ", "SA • GATE", "B-Rank", "Dimensional Rift", 720, "Opens the threshold between dungeon realms and reality."),
    RuneItem(5, "ᚨ", "OH • AVARICE", "SS-Rank", "Orb Resonance", 1250, "Amplifies magical output by 200% via the Crimson Sphere."),
    RuneItem(6, "ᚱ", "YUK • REIGN", "A-Rank", "Command Wave", 890, "Transmits unshakeable telepathic edicts to fallen thralls."),
    RuneItem(7, "ᛊ", "CHIL • EXTRACTION", "SSS-Rank", "Shadow Arise", 1500, "Extracts ethereal soul essence into loyal shadow warriors."),
    RuneItem(8, "ᛏ", "PAL • SOVEREIGN", "S-Rank", "Ruler Authority", 980, "Exerts gravitational telekinesis over physical matter."),
    RuneItem(9, "ᚹ", "GU • ASCENSION", "SSS-Rank", "Deathless Crown", 1400, "Transcends mortality through abyssal darkness."),
    RuneItem(0, "ᛈ", "MU • VOID", "EX-Rank", "Zero Horizon", 1600, "The silent abyss from which all eternal shadows awaken.")
)

val THEME_PRESETS = listOf(
    ThemeConfig(
        id = "shadow_monarch",
        name = "Shadow Monarch",
        primaryColor = EgeCyan,
        secondaryColor = EgePurple,
        auraColor = EgeCyan,
        characterName = "Sung Jin-Woo",
        characterSubtitle = "Monarch of Shadows"
    ),
    ThemeConfig(
        id = "monarch_destruction",
        name = "Monarch of Destruction",
        primaryColor = EgeCrimson,
        secondaryColor = Color(0xFFFF5722),
        auraColor = EgeCrimson,
        characterName = "Antares",
        characterSubtitle = "King of Berserk Dragons"
    ),
    ThemeConfig(
        id = "frost_monarch",
        name = "Frost Monarch",
        primaryColor = EgeGlacier,
        secondaryColor = Color(0xFFE0F7FA),
        auraColor = EgeGlacier,
        characterName = "Sillad",
        characterSubtitle = "Monarch of the White Frost"
    ),
    ThemeConfig(
        id = "iron_blood",
        name = "Iron & Blood",
        primaryColor = EgeGold,
        secondaryColor = Color(0xFFFF8F00),
        auraColor = EgeGold,
        characterName = "Iron Vanguard",
        characterSubtitle = "Legion Commander"
    )
)

data class InfiniteCodexRune(
    val id: String,
    val glyph: String,
    val name: String,
    val rank: String,
    val element: String,
    val powerLevel: Long,
    val inscription: String
)

object InfiniteCodexEngine {
    private val GLYPHS = listOf("ᚠ", "ᚢ", "ᚦ", "ᚨ", "ᚱ", "ᚲ", "ᚷ", "ᚹ", "ᚺ", "ᚾ", "ᛁ", "ᛃ", "ᛈ", "ᛇ", "ᛉ", "ᛊ", "ᛏ", "ᛒ", "ᛖ", "ᛗ", "ᛚ", "ᛜ", "ᛞ", "ᛟ")
    private val PREFIXES = listOf("Astra", "Vok", "Khar", "Mor", "Zeph", "Ign", "Thal", "Umbr", "Nyx", "Chron", "Sol", "Vael")
    private val SUFFIXES = listOf("dor", "rak", "kahn", "iel", "vash", "zarr", "moth", "ren", "thal", "kor", "gath", "rix")
    private val RANKS = listOf("B-Rank", "A-Rank", "S-Rank", "SS-Rank", "SSS-Rank", "EX-National Rank", "Transcendent Sovereign")
    private val ELEMENTS = listOf("Abyssal Dark", "Ethereal Cyan", "Solar Flare", "Nether Frost", "Blood Tempest", "Void Singularity")

    fun generateRune(seed: Int = Random.nextInt()): InfiniteCodexRune {
        val rand = Random(seed)
        val glyph = GLYPHS[rand.nextInt(GLYPHS.size)]
        val prefix = PREFIXES[rand.nextInt(PREFIXES.size)]
        val suffix = SUFFIXES[rand.nextInt(SUFFIXES.size)]
        val name = "$prefix'$suffix"
        val rank = RANKS[rand.nextInt(RANKS.size)]
        val element = ELEMENTS[rand.nextInt(ELEMENTS.size)]
        val power = rand.nextLong(10000, 9999999)
        val inscription = "Rune $name resonates with $element mana, granting rank $rank resonance when inscribed into the monarch soul archive."

        return InfiniteCodexRune(
            id = "RUNE-$seed",
            glyph = glyph,
            name = name.uppercase(),
            rank = rank,
            element = element,
            powerLevel = power,
            inscription = inscription
        )
    }
}
