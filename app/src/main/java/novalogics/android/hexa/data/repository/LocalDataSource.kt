package novalogics.android.hexa.data.repository

import novalogics.android.hexa.data.model.Spell
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStreamReader
import javax.inject.Inject

//Offline Repository
class LocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) : LocalDataSourceImpl {

    override fun getTestData(): String {
        return "Welcome to the Home screen"
    }

    override fun getListOfSpells(): List<Spell> {
        return try {
            val inputStream = context.assets.open("data.json")
            val reader = InputStreamReader(inputStream)
            val gson = Gson()
            val listType = object : TypeToken<List<Spell>>() {}.type
            gson.fromJson(reader, listType)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList<Spell>()
        }
    }

}
