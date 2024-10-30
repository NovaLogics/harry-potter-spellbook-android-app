package novalogics.android.hexa.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "charms")
data class CharmsEntity(
    @PrimaryKey
    val spellId: String = String(),
    val spellName: String  = String(),
    val description: String = String(),
    val type: String = String(),
    val lightColor: String = String(),
    val pronunciation: String = String(),
    val effect: String = String(),
    val origin: String = String(),
    val notes: String = String(),
    val section: String = String(),
    val isSection: Boolean = false
)
