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
                return "Hello! How can I assist you today?"
            }

            userInput.contains("im good", ignoreCase = true) ||
                    userInput.contains("nice", ignoreCase = true) ||
                    userInput.contains("good", ignoreCase = true) -> {
                return "It\'s good to hear that! "
            }

            userInput.contains("help", ignoreCase = true) -> {
                return "I can help you with simple tasks. Try asking me something!"
            }

            userInput.contains("time", ignoreCase = true) -> {
                return getCurrentTime()
            }

            userInput.contains("lumos", ignoreCase = true) -> {
                actionGo = HexaActions.FLASHLIGHT_ON
                return "✨ The light of Lumos illuminates your path! The flashlight is now on. Type 'Nox' to extinguish the light."
            }

            userInput.contains("nox", ignoreCase = true) -> {
                actionGo = HexaActions.FLASHLIGHT_OFF
                return "🌑 The darkness of Nox has returned. The flashlight is now off."
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
