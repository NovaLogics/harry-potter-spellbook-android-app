package novalogics.android.hexa.data.repository.fake

import novalogics.android.hexa.data.database.entity.CharmsEntity

object TestDataRepository {

    fun getTestCharmsEntity(): CharmsEntity {
        return CharmsEntity(
            "a_aparecium",
            "Aparecium",
            "Reveals hidden or invisible messages, particularly those written in invisible ink.",
            "Revealing Charm",
            "None",
            "ah-puh-REE-see-um",
            "Makes invisible ink visible",
            "Latin 'appareo' meaning 'to appear'.",
            "Often used to read hidden or enchanted texts.",
            "A",
            false
        )
    }
}
