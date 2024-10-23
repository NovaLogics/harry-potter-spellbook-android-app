package novalogics.android.harrysspellbook.ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

    val uiState by viewModel.uiState.collectAsState()

    Text(
        text = uiState.data,
    )

}
