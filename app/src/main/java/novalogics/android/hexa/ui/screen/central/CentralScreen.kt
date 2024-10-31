package novalogics.android.hexa.ui.screen.central

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import novalogics.android.hexa.R
import novalogics.android.hexa.data.database.entity.CharmsEntity
import novalogics.android.hexa.ui.common.component.CustomHeaderComponent
import novalogics.android.hexa.ui.common.component.StyledText
import novalogics.android.hexa.ui.common.component.TypewriteText
import novalogics.android.hexa.ui.common.textSizeResource
import novalogics.android.hexa.ui.theme.SpellBookTheme
import novalogics.android.hexa.util.Constants


@Composable
fun SpellCircleScreen(
    viewModel: CentralViewModel = hiltViewModel(),
    onLoadingChange: (Boolean) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    onLoadingChange(uiState.isLoading)

    LaunchedEffect(Unit) {
        viewModel.handleIntent(CentralIntent.LoadData)
    }

    ScreenUiContent(
        uiState = uiState,
        onTextFieldValueChange = {
            viewModel.handleIntent(CentralIntent.UpdateTextField(it))
        },
        onListDataValueChange = {
            viewModel.updateListData()
        }
    )
}



@Composable
fun ScreenUiContent(
    uiState : CentralUiState,
    onTextFieldValueChange: (String) -> Unit,
    onListDataValueChange: () -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    top = 8.dp, bottom = 80.dp)
        ) {

            HeaderTitleText()
            MediaBanner(
                drawableResId = R.drawable.img_banner_1
            )

            TypewriteText(
                text = "Welcome to app, how is your day",
                style = MaterialTheme.typography.displayMedium
            )

            StyledText(
                stringValue = uiState.listData,
                letterSpacing = R.dimen.letter_space_small_2dp,
                style = typography.displaySmall,
                fontSize = R.dimen.text_size_large_18sp,
                color = colorScheme.secondary,
                modifier = Modifier
                    .padding(
                        all = dimensionResource(id = R.dimen.padding_medium_16dp),
                    )
            )

            CharmData()

        }
        CustomHeaderComponent()

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
                .background(colorScheme.background),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = uiState.textFieldValue,
                onValueChange = {value-> onTextFieldValueChange(value)},
                maxLines = 3,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
                shape = MaterialTheme.shapes.small,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Button(onClick = onListDataValueChange) {
                Text("Submit")
            }
        }
    }
}


@Composable
fun HeaderTitleText() {
    StyledText(
        stringResId = R.string.app_name_display,
        letterSpacing = R.dimen.letter_space_small_2dp,
        style = typography.displayLarge,
        fontSize = R.dimen.text_size_large_20sp,
        color = colorScheme.secondary,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(
                all = dimensionResource(id = R.dimen.padding_medium_16dp),
            )
    )
}

@Composable
fun CharmData(
 //   charm: CharmsEntity = CharmsEntity()
) {
    Column (
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ){

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append("Spell Name : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Accio")
                }
            },
            style = typography.displaySmall,
            fontSize = textSizeResource(id = R.dimen.text_size_medium_16sp),
            letterSpacing = textSizeResource(id = R.dimen.letter_space_small_1dp),
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_medium_16dp),
                    end = dimensionResource(id = R.dimen.padding_medium_16dp),
                    bottom = dimensionResource(id = R.dimen.padding_small_4dp)
                )
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Description : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append("The Summoning Charm (Accio) allows the caster to summon objects toward themselves, either in direct line of sight or out of view, by calling the object's name aloud after the incantation (unless cast nonverbally). Successful casting requires the caster to have a clear mental image of the object. The charm's opposite is the Banishing Charm.")
                }
            },
            style = typography.displaySmall,
            fontSize = textSizeResource(id = R.dimen.text_size_medium_16sp),
            letterSpacing = textSizeResource(id = R.dimen.letter_space_small_1dp),
            lineHeight = textSizeResource(id = R.dimen.text_size_xlarge_24sp),
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_medium_16dp),
                    end = dimensionResource(id = R.dimen.padding_medium_16dp),
                    bottom = dimensionResource(id = R.dimen.padding_small_4dp)
                )

        )


        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append("Type : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Charm")
                }
            },
            style = typography.displaySmall,
            fontSize = textSizeResource(id = R.dimen.text_size_medium_16sp),
            letterSpacing = textSizeResource(id = R.dimen.letter_space_small_1dp),
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_medium_16dp),
                    end = dimensionResource(id = R.dimen.padding_medium_16dp),
                    bottom = dimensionResource(id = R.dimen.padding_small_4dp)
                )
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append("Light Color : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("None")
                }
            },
            style = typography.displaySmall,
            fontSize = textSizeResource(id = R.dimen.text_size_medium_16sp),
            letterSpacing = textSizeResource(id = R.dimen.letter_space_small_1dp),
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_medium_16dp),
                    end = dimensionResource(id = R.dimen.padding_medium_16dp),
                    bottom = dimensionResource(id = R.dimen.padding_small_4dp)
                )
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append("Pronunciation : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Various, including: AK-ee-oh, AK-see-oh, AS-see-oh (US), and AT-chee-oh (Anglo-Catholic pronunciation)")
                }
            },
            style = typography.displaySmall,
            fontSize = textSizeResource(id = R.dimen.text_size_medium_16sp),
            letterSpacing = textSizeResource(id = R.dimen.letter_space_small_1dp),
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_medium_16dp),
                    end = dimensionResource(id = R.dimen.padding_medium_16dp),
                    bottom = dimensionResource(id = R.dimen.padding_small_4dp)
                )
        )


        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append("Effect : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Summons an object from a distance")
                }
            },
            style = typography.displaySmall,
            fontSize = textSizeResource(id = R.dimen.text_size_medium_16sp),
            letterSpacing = textSizeResource(id = R.dimen.letter_space_small_1dp),
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_medium_16dp),
                    end = dimensionResource(id = R.dimen.padding_medium_16dp),
                    bottom = dimensionResource(id = R.dimen.padding_small_4dp)
                )
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append("Origin : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("The Latin word accio means \"I call\" or \"I summon\".")
                }
            },
            style = typography.displaySmall,
            fontSize = textSizeResource(id = R.dimen.text_size_medium_16sp),
            letterSpacing = textSizeResource(id = R.dimen.letter_space_small_1dp),
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_medium_16dp),
                    end = dimensionResource(id = R.dimen.padding_medium_16dp),
                    bottom = dimensionResource(id = R.dimen.padding_small_4dp)
                )
        )


        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append("Notes : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("The Summoning Charm could not be used on buildings. In addition, it did not work on most living things, and those few on which it did were generally not considered worth Summoning, such as flobberworms.")
                }
            },
            style = typography.displaySmall,
            fontSize = textSizeResource(id = R.dimen.text_size_medium_16sp),
            letterSpacing = textSizeResource(id = R.dimen.letter_space_small_1dp),
            lineHeight = textSizeResource(id = R.dimen.text_size_xlarge_24sp),
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_medium_16dp),
                    end = dimensionResource(id = R.dimen.padding_medium_16dp),
                    bottom = dimensionResource(id = R.dimen.padding_small_4dp)
                )
        )


    }
}



@Composable
fun MediaBanner(
    @DrawableRes
    drawableResId: Int
) {
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
            painter = painterResource(id = drawableResId),
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

    val uiState = CentralUiState(data = "Welcome to Home")

    SpellBookTheme {

        ScreenUiContent(
            uiState = uiState,
            onTextFieldValueChange = {},
            onListDataValueChange = {}
        )
    }
}
