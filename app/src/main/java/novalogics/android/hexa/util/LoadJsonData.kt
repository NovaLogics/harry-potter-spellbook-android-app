package novalogics.android.hexa.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import novalogics.android.hexa.data.model.Spell
import java.io.InputStreamReader

fun loadJsonData(context: Context): List<Spell> {
    val inputStream = context.assets.open("data.json")
    val reader = InputStreamReader(inputStream)
    val gson = Gson()
    val listType = object : TypeToken<List<Spell>>() {}.type
    return gson.fromJson(reader, listType)
}
