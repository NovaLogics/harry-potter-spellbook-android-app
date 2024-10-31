package novalogics.android.hexa.ui.common.component


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import novalogics.android.hexa.R
import novalogics.android.hexa.data.database.entity.CharmsEntity
import novalogics.android.hexa.data.repository.fake.TestDataRepository
import novalogics.android.hexa.ui.common.textSizeResource
import novalogics.android.hexa.ui.theme.SpellBookTheme


@Composable
fun SpellBottomSheetContent(
    charm: CharmsEntity
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                top = dimensionResource(id = R.dimen.padding_regular_8dp),
                bottom = dimensionResource(id = R.dimen.padding_large_24dp),
                start = dimensionResource(id = R.dimen.padding_medium_16dp),
                end = dimensionResource(id = R.dimen.padding_medium_16dp)
            )
    ) {

        DisplayTextItem("Spell Name : ", charm.spellName)

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.size_small_24dp)))

        DisplayLongTextItem("Description : ", charm.description)

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.size_small_24dp)))

    }
}

@Composable
fun DisplayTextItem(
    titleText: String,
    stringValue: String
){

    StyledText(
        stringValue = titleText,
        letterSpacing = R.dimen.letter_space_small_2dp,
        style = typography.displayMedium,
        fontSize = R.dimen.text_size_medium_16sp,
        color = colorScheme.onSecondaryContainer,
        modifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.padding_regular_8dp))
    )

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation_medium_4dp)
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(colorScheme.surface),
    ) {
        StyledText(
            stringValue = stringValue,
            letterSpacing = R.dimen.letter_space_small_2dp,
            style = typography.displayMedium,
            fontSize = R.dimen.text_size_medium_16sp,
            color = colorScheme.onPrimaryContainer,
            modifier = Modifier
                .padding(
                    all = dimensionResource(id = R.dimen.padding_regular_8dp)
                )
        )
    }
}


@Composable
fun DisplayLongTextItem(
    titleText: String,
    stringValue: String
){

    StyledText(
        stringValue = titleText,
        letterSpacing = R.dimen.letter_space_small_2dp,
        style = typography.displayMedium,
        fontSize = R.dimen.text_size_medium_16sp,
        color = colorScheme.onSecondaryContainer,
        modifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.padding_regular_8dp))
    )

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation_medium_4dp)
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(colorScheme.surface),
    ) {
        Text(
            text = stringValue,
            style = typography.bodyMedium,
            fontSize = textSizeResource(id = R.dimen.text_size_medium_16sp),
            letterSpacing = textSizeResource(id = R.dimen.letter_space_small_1dp),
            lineHeight = textSizeResource(id = R.dimen.text_size_xlarge_24sp),
            textAlign = TextAlign.Justify,
            color = colorScheme.onPrimaryContainer,
            modifier = Modifier
                .padding(
                    all = dimensionResource(id = R.dimen.padding_regular_8dp)
                )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun BottomSheetPreview() {
    val charm = TestDataRepository.getTestCharmsEntity()

    SpellBookTheme {
        Surface {
            SpellBottomSheetContent(charm)
        }
    }
}
