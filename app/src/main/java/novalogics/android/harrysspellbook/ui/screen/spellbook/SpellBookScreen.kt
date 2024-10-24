package novalogics.android.harrysspellbook.ui.screen.spellbook

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import novalogics.android.harrysspellbook.R
import novalogics.android.harrysspellbook.ui.common.StyledText
import novalogics.android.harrysspellbook.ui.theme.SpellBookTheme
import novalogics.android.harrysspellbook.util.Constants


@Composable
fun SpellBookScreen(
    viewModel: SpellBookViewModel = hiltViewModel(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val uiState by viewModel.uiState.collectAsState()

    ScreenFlow(
        uiState = uiState
    )
}

@Composable
fun ScreenFlow(
    uiState : SpellBookUiState
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.surface)
    ) {

        Image(
            painter = painterResource(id = R.drawable.bg_home_effect),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5F)
        )

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
        ) {


        }

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
fun SectionElement(

) {
    Column (modifier = Modifier.padding(8.dp)){
        SectionHeader()
        SectionBodyElement()
    }

}

@Composable
fun SectionHeader(

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Image(
            painter = painterResource(id = R.drawable.element_bookmark_purple),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(0.dp)
        )
        StyledText(
            stringValue = stringResource(id = R.string.section_title, "A"),
            letterSpacing = R.dimen.latter_space_small_2dp,
            style = typography.displayMedium,
            color = colorScheme.secondary,
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.padding_medium_20dp),
                    start = dimensionResource(id = R.dimen.padding_large_24dp),
                )
        )
    }

}

@Composable
fun SectionBodyElement(

) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(width = 1.dp, color = colorScheme.background)
            ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(colorScheme.surface)
    ) {

        Column {
            StyledText(
                stringValue = "Aberto",
                fontSize = R.dimen.text_size_small_14sp,
                letterSpacing = R.dimen.latter_space_small_1dp,
                style = typography.labelSmall,
                isUppercase = true,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.padding_regular_8dp),
                    start = dimensionResource(id = R.dimen.padding_regular_8dp)
                ),
            )


            StyledText(
                stringValue = "A spell used to open doors;",
                fontSize = R.dimen.text_size_small_12sp,
                letterSpacing = R.dimen.latter_space_small_1dp,
                style = typography.displayMedium,
                isUppercase = true,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.padding_regular_8dp),
                    start = dimensionResource(id = R.dimen.padding_regular_8dp)
                ),
            )

            StyledText(
                stringValue = "Type: Charm",
                fontSize = R.dimen.text_size_small_12sp,
                letterSpacing = R.dimen.latter_space_small_1dp,
                style = typography.displayMedium,
                isUppercase = true,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.padding_regular_8dp),
                    start = dimensionResource(id = R.dimen.padding_regular_8dp),
                    bottom = dimensionResource(id = R.dimen.padding_regular_8dp)
                ),
            )
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
fun HomeScreenPreview() {

    val uiState = SpellBookUiState(
        true,
        "Welcome to Home",
        null)

    SpellBookTheme {
        SectionElement()
//        ScreenFlow(
//            uiState = uiState
//        )
    }
}
