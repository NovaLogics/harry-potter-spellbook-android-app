package novalogics.android.hexa.ui.common.component


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append("Spell Name : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(charm.spellName)
                }
            },
            style = MaterialTheme.typography.titleLarge,
            fontSize = textSizeResource(id = R.dimen.text_size_medium_16sp),
            letterSpacing = textSizeResource(id = R.dimen.letter_space_small_1dp),
            modifier = Modifier
                .padding(
                    bottom = dimensionResource(id = R.dimen.padding_small_4dp)
                )
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Description : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(charm.description)
                }
            },
            style = MaterialTheme.typography.bodyMedium,
            fontSize = textSizeResource(id = R.dimen.text_size_medium_16sp),
            letterSpacing = textSizeResource(id = R.dimen.letter_space_small_1dp),
            lineHeight = textSizeResource(id = R.dimen.text_size_xlarge_24sp),
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(
                    bottom = dimensionResource(id = R.dimen.padding_small_4dp)
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
