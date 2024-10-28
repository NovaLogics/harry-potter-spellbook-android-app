package novalogics.android.harrysspellbook.ui.screen.spellcircle

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.surface)
    ) {

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(24.dp)
                .border(
                    BorderStroke(width = 1.dp, color = colorScheme.background),
                    shape = MaterialTheme.shapes.medium
                ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            shape = MaterialTheme.shapes.medium
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

        Image(
            painter = painterResource(id = R.drawable.img_crossed_wands),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(4.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 96.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        ) {

            Text(
                text = uiState.data,
                color = colorScheme.onBackground
            )

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
            painter = painter,
            contentDescription = "Animated GIF",
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.3F),
            contentScale = ContentScale.FillWidth
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

    val uiState = SpellCircleUiState(data = "Welcome to Home",)

    SpellBookTheme {
        ScreenUiContent(
            uiState = uiState
        )
    }
}
