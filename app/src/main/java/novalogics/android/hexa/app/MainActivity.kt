package novalogics.android.hexa.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import novalogics.android.hexa.ui.screen.mainscreen.HexaMainScreen
import novalogics.android.hexa.ui.theme.SpellBookTheme
import novalogics.android.hexa.util.Constants.DELAY_1_SECOND

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            runBlocking { delay( DELAY_1_SECOND ) }
            false
        }
       // enableEdgeToEdge()
        setContent {
            SpellBookTheme {
                HexaMainScreen()
            }
        }
    }
}
