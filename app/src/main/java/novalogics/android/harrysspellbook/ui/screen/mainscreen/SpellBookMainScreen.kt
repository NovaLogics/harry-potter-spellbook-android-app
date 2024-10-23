package novalogics.android.harrysspellbook.ui.screen.mainscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import novalogics.android.harrysspellbook.R
import novalogics.android.harrysspellbook.ui.navigation.AppScreens
import novalogics.android.harrysspellbook.ui.screen.home.HomeScreen
import novalogics.android.harrysspellbook.ui.screen.mainscreen.component.BottomNavigationBar


@Composable
fun SpellBookMainScreen(
    navController: NavHostController = rememberNavController()
){
    val context = LocalContext.current
    val tabItems = context.resources.getStringArray(R.array.tab_items).toList()

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

        NavHost(
            navController = navController,
            startDestination = AppScreens.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = AppScreens.Home.name) {
                HomeScreen(
                    contentPadding = innerPadding
                )
            }

            composable(route = AppScreens.SpellBook.name) {
                SpellBookScreen()
            }

        }
    }
}


@Composable
fun SpellBookScreen() {
    Text(text = " SpellBook Screen", modifier = Modifier.fillMaxSize())
}
