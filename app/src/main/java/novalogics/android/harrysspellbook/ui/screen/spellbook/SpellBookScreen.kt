package novalogics.android.harrysspellbook.ui.screen.spellbook

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import novalogics.android.harrysspellbook.R
import novalogics.android.harrysspellbook.data.model.Spell
import novalogics.android.harrysspellbook.data.repository.HomeRepositoryOffline
import novalogics.android.harrysspellbook.ui.common.StyledText
import novalogics.android.harrysspellbook.ui.common.textSizeResource
import novalogics.android.harrysspellbook.ui.theme.SpellBookTheme
import novalogics.android.harrysspellbook.util.Constants


@JvmOverloads
@Composable
fun SpellBookScreen(
    viewModel: SpellBookViewModel = hiltViewModel(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val uiState by viewModel.uiState.collectAsState()

    ScreenUiContent(
        uiState = uiState
    )
}

@Composable
fun ScreenUiContent(
    uiState: SpellBookUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.surface)
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

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(
                space = dimensionResource(id = R.dimen.space_regular_8dp)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(id = R.dimen.padding_regular_12dp)),
            content = {
                items(uiState.spellList) { spell ->
                    if (spell.isSection) {
                        SectionHeader(stringResource(id = R.string.section_title, spell.section))
                    } else {
                        SectionEntity(spell = spell)
                    }
                }
            }
        )

    }
}


@Composable
fun SectionHeader(
    title: String
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.element_bookmark_purple),
            contentDescription = title,
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_large_48dp))
        )
        StyledText(
            stringValue = title,
            letterSpacing = R.dimen.latter_space_small_2dp,
            style = typography.displayMedium,
            fontSize = R.dimen.text_size_xlarge_24sp,
            color = colorScheme.secondary,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_regular_12dp),
                )
        )
    }
}

@Composable
fun SectionEntity(
    spell: Spell
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(
                    width = dimensionResource(id = R.dimen.border_stroke_small_0_5dp),
                    color = colorScheme.onPrimaryContainer
                )
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation_medium_4dp)
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(id = R.dimen.padding_regular_8dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(modifier = Modifier.weight(1f)) {

                StyledText(
                    stringValue = spell.spellName,
                    fontSize = R.dimen.text_size_large_20sp,
                    letterSpacing = R.dimen.latter_space_medium_4dp,
                    style = typography.displayLarge,
                    isUppercase = true,
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.padding_small_4dp),
                        start = dimensionResource(id = R.dimen.padding_regular_8dp)
                    ),
                )

                Text(
                    text = spell.description,
                    style = typography.bodyMedium,
                    fontSize = textSizeResource(id = R.dimen.text_size_medium_16sp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_regular_12dp),
                            start = dimensionResource(id = R.dimen.padding_regular_8dp),
                            end = dimensionResource(id = R.dimen.padding_medium_16dp)
                        )
                )

                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_regular_12dp),
                            start = dimensionResource(id = R.dimen.padding_regular_8dp),
                            bottom = dimensionResource(id = R.dimen.padding_small_4dp)
                        ),
                ) {
                    IconWithText(
                        R.drawable.ic_wand_sparkles,
                        stringResource(id = R.string.light_value, spell.lightColor)
                    )
                    Spacer(
                        modifier = Modifier.size(dimensionResource(id = R.dimen.size_xsmall_16dp))
                    )
                    IconWithText(
                        R.drawable.ic_doubled,
                        stringResource(id = R.string.type_value, spell.type)
                    )
                }

            }

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_star_normal),
                contentDescription = null,
                modifier = Modifier
                    .size(size = dimensionResource(id = R.dimen.size_medium_32dp))
            )
            Spacer(
                modifier = Modifier.size(dimensionResource(id = R.dimen.size_xsmall_4dp))
            )

        }
    }
}

@Composable
fun IconWithText(
    @DrawableRes
    iconResId: Int,
    textValue: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy( space = dimensionResource(id = R.dimen.space_regular_8dp) )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = iconResId),
            contentDescription = textValue,
            modifier = Modifier.size(size = dimensionResource(id = R.dimen.icon_size_xsmall_16dp))
        )
        Text(
            text = textValue,
            style = typography.displayMedium.copy(
                fontSize = textSizeResource(id = R.dimen.text_size_small_12sp)
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
fun HomeScreenPreview() {

    val context = LocalContext.current

    val uiState = SpellBookUiState(
        true,
        HomeRepositoryOffline(context).getJsonData().subList(0,5),
        "Welcome to Home",
        null)

    SpellBookTheme {
       // SectionElement()
        ScreenUiContent(
            uiState = uiState
        )
    }
}
