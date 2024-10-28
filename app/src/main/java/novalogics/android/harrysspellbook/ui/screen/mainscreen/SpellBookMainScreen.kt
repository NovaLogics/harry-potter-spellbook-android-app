package novalogics.android.harrysspellbook.ui.screen.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import novalogics.android.harrysspellbook.R
import novalogics.android.harrysspellbook.ui.common.component.LoadingScreen
import novalogics.android.harrysspellbook.ui.navigation.AppScreens
import novalogics.android.harrysspellbook.ui.screen.spellcircle.SpellCircleScreen
import novalogics.android.harrysspellbook.ui.screen.mainscreen.component.BottomNavigationBar
import novalogics.android.harrysspellbook.ui.screen.spellbook.SpellBookScreen


@Composable
fun SpellBookMainScreen(
    navController: NavHostController = rememberNavController()
){
    val context = LocalContext.current
    val tabItems = context.resources.getStringArray(R.array.tab_items).toList()
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = tabItems
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
    ) { innerPadding ->

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            NavHost(
                navController = navController,
                startDestination = AppScreens.SpellCircle.name,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable(route = AppScreens.SpellCircle.name) {
                    SpellCircleScreen(
                        onLoadingChange = { loading -> isLoading = loading }
                    )
                }

                composable(route = AppScreens.SpellBook.name) {
                    SpellBookScreen(
                        onLoadingChange = { loading -> isLoading = loading }
                    )
                }
            }

            if (isLoading) {
                LoadingScreen()
            }
        }
    }
}
