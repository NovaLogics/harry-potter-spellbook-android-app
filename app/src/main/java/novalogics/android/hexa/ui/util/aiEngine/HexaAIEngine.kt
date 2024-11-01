package novalogics.android.hexa.ui.util.aiEngine

import android.content.Context
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class HexaAIEngine @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private var actionGo : HexaActions = HexaActions.NONE

    fun getAction() = actionGo

    fun getResponse(userInput: String): String {
        actionGo = HexaActions.NONE
        when {
            userInput.contains("hi", ignoreCase = true) ||
                    userInput.contains("hey", ignoreCase = true) ||
                    userInput.contains("hello", ignoreCase = true) -> {
                return "Hey! Let's make some magic together. \nHow can Hexa assist?"
            }

            userInput.contains("im good", ignoreCase = true) ||
                    userInput.contains("nice", ignoreCase = true) ||
                    userInput.contains("good", ignoreCase = true) -> {
                return "Glad to hear you're feeling great! What would you like to do next?"
            }

            userInput.contains("like ", ignoreCase = true) &&
                    (userInput.contains("app", ignoreCase = true) ||
                    userInput.contains("hexa", ignoreCase = true)) -> {
                return "So happy to hear that! \uD83D\uDC96 \nThere's plenty more magic where that came from!\uD83E\uDD89✨"
            }

            userInput.contains("help", ignoreCase = true) -> {
                return "I can help you with simple tasks. Try asking me something!"
            }

            userInput.contains("time", ignoreCase = true) -> {
                return getCurrentTime()
            }

            userInput.contains("guide", ignoreCase = true) -> {
                return "Sure! Hexa is here to guide you! \n🔮 Here, you can explore \nHogwarts Magic. \nTry typing or saying 'Lumos' to practice it will cast light ✨\n(and turn on your device's flashlight). \nWhen you’re ready to end the spell, just say 'Nox' to turn it off."
            }

            userInput.contains("lumos", ignoreCase = true) -> {
                actionGo = HexaActions.FLASHLIGHT_ON
                return "✨ The light of Lumos illuminates your path! \nThe flashlight is now on. \nType 'Nox' to extinguish the light."
            }

            userInput.contains("nox", ignoreCase = true) -> {
                actionGo = HexaActions.FLASHLIGHT_OFF
                return "🌑 The darkness of Nox has returned. \nThe flashlight is now off."
            }

            userInput.contains("wow", ignoreCase = true) -> {
                return "✨ Wow indeed! \nThe magic's just getting started!"
            }

            userInput.contains("bye", ignoreCase = true) -> {
                return "👋 Until next time! May your day be filled with magic and wonder!"
            }

            else -> return "I'm here to chat. Feel free to ask me anything!"
        }
    }

    private fun getCurrentTime(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // For Android O and above
            val currentTime = java.time.LocalTime.now()
            "The current time is ${currentTime.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a"))}"
        } else {
            // For older Android versions
            val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val currentTime = Date()
            "The current time is ${dateFormat.format(currentTime)}"
        }
    }
}
