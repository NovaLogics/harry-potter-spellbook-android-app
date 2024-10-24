package novalogics.android.harrysspellbook.data.repository

import novalogics.android.harrysspellbook.data.model.Spell
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStreamReader
import javax.inject.Inject

class HomeRepositoryOffline @Inject constructor(
    @ApplicationContext private val context: Context
) : HomeRepositoryImpl {

    override fun getTestData(): String {
        return "Welcome to the Home screen"
    }

    override fun getJsonData(): List<Spell> {
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
