package novalogics.android.hexa.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "charms")
data class CharmsEntity(
    @PrimaryKey
    @ColumnInfo(name = "spell_id")
    val spellId: String,

    @ColumnInfo(name = "spell_name")
    val spellName: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "light_color")
    val lightColor: String,

    @ColumnInfo(name = "pronunciation")
    val pronunciation: String,

    @ColumnInfo(name = "effect")
    val effect: String,

    @ColumnInfo(name = "origin")
    val origin: String? = null,

    @ColumnInfo(name = "notes")
    val notes: String? = null,

    @ColumnInfo(name = "section")
    val section: String,

    @ColumnInfo(name = "is_section")
    val isSection: Boolean = false
)
