package novalogics.android.hexa.ui.screen.mainscreen

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import novalogics.android.hexa.R
import novalogics.android.hexa.data.database.entity.CharmsEntity
import novalogics.android.hexa.data.repository.LocalDataSource
import novalogics.android.hexa.data.repository.fake.TestDataRepository
import novalogics.android.hexa.ui.common.component.LoadingScreen
import novalogics.android.hexa.ui.common.component.SpellBottomSheetContent
import novalogics.android.hexa.ui.navigation.AppScreens
import novalogics.android.hexa.ui.screen.central.SpellCircleScreen
import novalogics.android.hexa.ui.screen.mainscreen.component.BottomNavigationBar
import novalogics.android.hexa.ui.screen.charms.CharmsScreen
import novalogics.android.hexa.ui.theme.SpellBookTheme
import novalogics.android.hexa.util.Constants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HexaMainScreen(
    navController: NavHostController = rememberNavController()
){
    val context = LocalContext.current
    val tabItems = context.resources.getStringArray(R.array.tab_items).toList()
    var isLoading by remember { mutableStateOf(false) }
    val imeHeight = WindowInsets.ime.getBottom(LocalDensity.current)
    val keyboardVisible = imeHeight > 0

    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var skipPartiallyExpanded by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = skipPartiallyExpanded
        )
    var charmObj:CharmsEntity = TestDataRepository.getTestCharmsEntity()


    Scaffold(
        bottomBar = {
            if (!keyboardVisible) { // Show bottom bar only if keyboard is not visible
                BottomNavigationBar(
                    navController = navController,
                    items = tabItems
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .imePadding()
    ) { innerPadding ->

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            NavHost(
                navController = navController,
                startDestination = AppScreens.Central.name,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable(route = AppScreens.Central.name) {
                    SpellCircleScreen(
                        onLoadingChange = { loading -> isLoading = loading }
                    )
                }

                composable(route = AppScreens.Charms.name) {
                    CharmsScreen(
                        onLoadingChange = { loading -> isLoading = loading },
                        onBottomSheet = {entity ->
                            charmObj = entity
                            openBottomSheet = true
                        }
                    )
                }
            }

            if (isLoading) {
                LoadingScreen()
            }

            if (openBottomSheet) {

                ModalBottomSheet(
                    onDismissRequest = { openBottomSheet = false },
                    sheetState = bottomSheetState,

                    ) {
                    SpellBottomSheetContent(charmObj)
                }
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
        HexaMainScreen()
    }
}
