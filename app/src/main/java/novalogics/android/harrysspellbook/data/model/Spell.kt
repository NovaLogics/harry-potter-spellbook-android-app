package novalogics.android.harrysspellbook.data.model

import com.google.gson.annotations.SerializedName

data class Spell(
    @SerializedName("spell_id")
    val spellId: String,
    @SerializedName("spell_name")
    val spellName: String,
    val description: String,
    val type: String,
    @SerializedName("light_color")
    val lightColor: String,
    val pronunciation: String,
    val effect: String,
    val section: String,
    @SerializedName("is_section")
    val isSection: Boolean
)
