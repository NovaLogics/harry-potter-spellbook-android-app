package novalogics.android.harrysspellbook.ui.screen.spellcircle

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import novalogics.android.harrysspellbook.R
import novalogics.android.harrysspellbook.ui.common.component.StyledText
import novalogics.android.harrysspellbook.ui.theme.SpellBookTheme
import novalogics.android.harrysspellbook.util.Constants


@Composable
fun SpellCircleScreen(
    viewModel: SpellCircleViewModel = hiltViewModel(),
    onLoadingChange: (Boolean) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    onLoadingChange(uiState.isLoading)

    ScreenUiContent(
        uiState = uiState
    )
}

@Composable
fun ScreenUiContent(
    uiState : SpellCircleUiState
){
    val scrollState = rememberScrollState()
    val textFieldValue = remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(colorScheme.surface)
                .padding(bottom = 80.dp)
                .consumeWindowInsets(
                    WindowInsets.systemBars.only(WindowInsetsSides.Vertical)
                )
        ) {

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(dimensionResource(id = R.dimen.size_xsmall_16dp))
                    .border(
                        BorderStroke(
                            width = dimensionResource(id = R.dimen.border_stroke_medium_1dp),
                            color = colorScheme.background
                        ),
                        shape = MaterialTheme.shapes.medium
                    ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = dimensionResource(id = R.dimen.elevation_xlarge_10dp)
                ),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(colorScheme.background)
            ) {}

            StyledText(
                stringResId = R.string.app_name,
                letterSpacing = R.dimen.latter_space_small_2dp,
                style = typography.displayLarge,
                fontSize = R.dimen.text_size_xlarge_24sp,
                color = colorScheme.secondary,
                modifier = Modifier
                    .padding(
                        all = dimensionResource(id = R.dimen.padding_medium_16dp),
                    )
            )

            GifCard()

        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = textFieldValue.value,
                onValueChange = { textFieldValue.value = it },
                maxLines = 3,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus() // Clear focus when Done is pressed
                    }
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            Button(onClick = {
                // Handle submit action
            }) {
                Text("Submit")
            }

        }
    }

}

@Composable
fun GifCard() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_regular_8dp))
            .size(200.dp),
        colors = CardDefaults.cardColors(Color.Black),
        shape = MaterialTheme.shapes.small.copy(all = CornerSize(
            dimensionResource(id = R.dimen.corner_radius_medium_8dp))
        ),
    ) {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.harry_potter_home)
                .decoderFactory(GifDecoder.Factory())
                .build()
        )
        Image(
            painter = painterResource(id = R.drawable.img_banner_1),
            contentDescription = "Animated GIF",
            modifier = Modifier
                .fillMaxWidth()
              //  .alpha(0.3F)
            ,
            contentScale = ContentScale.Crop
        )
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
private fun SpellCircleScreenPreview() {

    val uiState = SpellCircleUiState(data = "Welcome to Home")

    SpellBookTheme {
        ScreenUiContent(
            uiState = uiState
        )
    }
}
