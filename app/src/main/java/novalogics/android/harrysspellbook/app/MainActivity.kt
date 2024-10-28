package novalogics.android.harrysspellbook.app

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import novalogics.android.harrysspellbook.ui.screen.mainscreen.SpellBookMainScreen
import novalogics.android.harrysspellbook.ui.theme.SpellBookTheme
import novalogics.android.harrysspellbook.util.Constants
import novalogics.android.harrysspellbook.util.Constants.DELAY_1_SECOND

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
                SpellBookMainScreen()
            }
        }
    }
}

@Preview(
    name = Constants.MODE_LIGHT,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = Constants.MODE_NIGHT,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun AppPreview() {
    SpellBookTheme {
        SpellBookMainScreen()
    }
}
