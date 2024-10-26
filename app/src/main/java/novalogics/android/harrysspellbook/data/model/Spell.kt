package novalogics.android.harrysspellbook.data.model

import com.google.gson.annotations.SerializedName

data class Spell(
    @SerializedName("spell_id")
    val spellId: String = String(),
    @SerializedName("spell_name")
    val spellName: String  = String(),
    val description: String = String(),
    val type: String = String(),
    @SerializedName("light_color")
    val lightColor: String = String(),
    val pronunciation: String = String(),
    val effect: String = String(),
    val origin: String = String(),
    val notes: String = String(),
    val section: String = String(),
    @SerializedName("is_section")
    val isSection: Boolean = false
)
