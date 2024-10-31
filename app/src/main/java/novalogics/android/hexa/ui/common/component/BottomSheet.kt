package novalogics.android.hexa.ui.common.component


import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import novalogics.android.hexa.R
import novalogics.android.hexa.data.database.entity.CharmsEntity
import novalogics.android.hexa.data.repository.fake.TestDataRepository
import novalogics.android.hexa.ui.common.textSizeResource
import novalogics.android.hexa.ui.theme.SpellBookTheme
import novalogics.android.hexa.util.Constants


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

        DisplayTextItem(
            "Spell Name : ",
            charm.spellName
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.size_small_24dp)))

        DisplayTextItem(
            "Pronunciation: ",
            charm.pronunciation
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.size_small_24dp)))

        DisplayTextItem(
            "Effect : ",
            charm.effect
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.size_small_24dp)))

        DisplayTextItem(
            "Spell Type : ",
            charm.type
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.size_small_24dp)))

        DisplayTextItem(
            "Light Color : ",
            charm.lightColor
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.size_small_24dp)))

        DisplayLongTextItem(
            "Description : ",
            charm.description
        )


        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.size_small_24dp)))

        DisplayTextItem(
            "Origin : ",
            charm.origin ?: "N/A"
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.size_small_24dp)))

        DisplayTextItem(
            "Notes : ",
            charm.notes ?: "N/A"
        )

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
        style = typography.displayLarge,
        fontSize = R.dimen.text_size_small_14sp,
        color = colorScheme.onSecondaryContainer,
        modifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.padding_regular_8dp))
    )

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation_small_1dp)
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(colorScheme.surface),
    ) {
        StyledText(
            stringValue = stringValue,
            letterSpacing = R.dimen.letter_space_small_2dp,
            style = typography.displayMedium,
            fontWeight = FontWeight.Normal,
            fontSize = R.dimen.text_size_large_18sp,
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
        style = typography.displayLarge,
        fontSize = R.dimen.text_size_small_14sp,
        color = colorScheme.onSecondaryContainer,
        modifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.padding_regular_8dp))
    )

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation_small_1dp)
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(colorScheme.surface),
    ) {
        Text(
            text = stringValue,
            style = typography.displayMedium,
            fontWeight = FontWeight.Normal,
            fontSize = textSizeResource(id = R.dimen.text_size_large_18sp),
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
fun BottomSheetPreview() {
    val charm = TestDataRepository.getTestCharmsEntity()

    SpellBookTheme {
        Surface {
            SpellBottomSheetContent(charm)
        }
    }
}
