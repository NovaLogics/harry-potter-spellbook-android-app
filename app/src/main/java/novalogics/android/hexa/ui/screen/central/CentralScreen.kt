package novalogics.android.hexa.ui.screen.central

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import novalogics.android.hexa.R
import novalogics.android.hexa.ui.common.component.CustomHeaderComponent
import novalogics.android.hexa.ui.common.component.StyledText
import novalogics.android.hexa.ui.common.component.TypewriteText
import novalogics.android.hexa.ui.theme.SpellBookTheme
import novalogics.android.hexa.ui.util.aiEngine.HexaActions
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
        onUserInputValueChange = {
            viewModel.handleIntent(CentralIntent.UserInputActions)
        }
    )
}


@Composable
fun ScreenUiContent(
    uiState : CentralUiState,
    onTextFieldValueChange: (String) -> Unit,
    onUserInputValueChange: () -> Unit
) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

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
                    top = dimensionResource(id = R.dimen.padding_regular_8dp),
                    bottom = dimensionResource(id = R.dimen.padding_xlarge_32dp)
                )
        ) {

            HeaderTitleText()

            MediaBanner(
                drawableResId = R.drawable.img_harry_friends
            )

            StyledText(
                stringValue = uiState.userMessage,
                letterSpacing = R.dimen.letter_space_small_2dp,
                style = typography.displayLarge.copy(
                    shadow = Shadow(
                        color = colorScheme.onSurface,
                        offset = Offset(1.0f, 1.0f),
                        blurRadius = 2f
                    )
                ),
                fontWeight = FontWeight.Bold,
                fontSize = R.dimen.text_size_large_20sp,
                color = colorScheme.secondary,
                modifier = Modifier
                    .padding(
                        all = dimensionResource(id = R.dimen.padding_medium_16dp),
                    )
            )

            TypewriteText(
                text = uiState.dataAiValue,
                style = typography.displayMedium,
                modifier = Modifier.padding(all = dimensionResource(id = R.dimen.padding_medium_16dp))
            )

            if (uiState.deviceHexaActions == HexaActions.FLASHLIGHT_ON ||
                uiState.deviceHexaActions == HexaActions.FLASHLIGHT_OFF
            ) {
                FlashlightControl(
                    context = LocalContext.current,
                    actionGo = uiState.deviceHexaActions
                )
            }


        }
        CustomHeaderComponent()

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(all = dimensionResource(id = R.dimen.padding_medium_16dp))
                .background(colorScheme.background),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = uiState.textFieldValue,
                onValueChange = {value-> onTextFieldValueChange(value)},
                maxLines = 3,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onUserInputValueChange.invoke()
                        keyboardController?.hide()
                    }
                ),
                shape = MaterialTheme.shapes.small.copy(
                    all = CornerSize(dimensionResource(id = R.dimen.size_xsmall_16dp))
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = dimensionResource(id = R.dimen.padding_regular_8dp))
            )
            Button(onClick = {
                onUserInputValueChange.invoke()
                keyboardController?.hide()
            }) {
                Text(text = stringResource(id = R.string.send))
            }
        }
    }
}


@Composable
fun HeaderTitleText() {
    StyledText(
        stringResId = R.string.app_name_display,
        letterSpacing = R.dimen.letter_space_small_2dp,
        style = typography.displayLarge.copy(
            shadow = Shadow(
                color = colorScheme.onSurface,
                offset = Offset(1.0f, 1.0f),
                blurRadius = 2f
            )
        ),
        fontSize = R.dimen.text_size_large_20sp,
        color = colorScheme.secondary,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(all = dimensionResource(id = R.dimen.padding_medium_16dp))

    )
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
            .size(182.dp),
        colors = CardDefaults.cardColors(Color.Black),
        shape = MaterialTheme.shapes.small.copy(all = CornerSize(
            dimensionResource(id = R.dimen.corner_radius_medium_8dp))
        ),
    ) {
        val painterGif = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.harry_potter_home)
                .decoderFactory(GifDecoder.Factory())
                .build()
        )
        val painterImage = painterResource(id = drawableResId)
        Image(
            painter = painterImage,
            contentDescription = "Animated GIF",
            modifier = Modifier
                .fillMaxWidth()
              //  .alpha(0.3F)
            ,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun FlashlightControl(
    context: Context,
    actionGo: HexaActions
) {
    var isFlashOn by remember { mutableStateOf(false) }

    val cameraManager = remember { context.getSystemService(Context.CAMERA_SERVICE) as CameraManager }
    val cameraId = remember { cameraManager.cameraIdList.first() }


    Button(
        onClick = {
            isFlashOn = !isFlashOn
            try {
                cameraManager.setTorchMode(cameraId, isFlashOn)
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        },
        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_xlarge_32dp))
    ) {
        Text(if (isFlashOn) "Turn Off Flashlight" else "Turn On Flashlight")
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

    val uiState = CentralUiState(
        userMessage = "> Lumos",
        dataAiValue = "Welcome to Home"
    )

    SpellBookTheme {

        ScreenUiContent(
            uiState = uiState,
            onTextFieldValueChange = {},
            onUserInputValueChange = {}
        )
    }
}
