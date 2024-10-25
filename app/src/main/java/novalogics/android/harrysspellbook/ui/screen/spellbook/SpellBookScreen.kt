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
import androidx.compose.ui.draw.alpha
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
            .background(colorScheme.background)
    ) {

        Image(
            painter = painterResource(id = R.drawable.bg_home_effect),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5F)
        )


        Column {

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.size_4xlarge_600dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    items(uiState.spellList) { spell ->
                        if(spell.isSection){
                            SectionHeader(stringResource(id = R.string.section_title, spell.section))
                        }
                        else{
                            SectionEntity(spell = spell)
                            Spacer(modifier = Modifier.padding(16.dp))
                        }
                    }
                }
            )
        }

    }
}

@Composable
fun SectionElement(

) {
   val spell = Spell(
       "a_aberto",
    "Aberto",
   "A spell used to open doors; it is probably related to Alohamora.",
    "Charm",
    "Blue",
    "Ah-bare-toh",
   "Opened doors",
    "A",
    false
    )
    Column (modifier = Modifier.padding(8.dp)){
        SectionHeader(stringResource(id = R.string.section_title, "A"))
        SectionEntity(spell)
    }

}

@Composable
fun SectionHeader(
    stringValue: String
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.element_bookmark_purple),
            contentDescription = stringValue,
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_large_48dp))
        )
        StyledText(
            stringValue = stringValue,
            letterSpacing = R.dimen.latter_space_small_2dp,
            style = typography.displayMedium,
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
                    width = dimensionResource(id = R.dimen.border_stroke_medium_1dp),
                    color = colorScheme.onPrimaryContainer
                )
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation_large_8dp)
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
                    fontSize = R.dimen.text_size_small_14sp,
                    letterSpacing = R.dimen.latter_space_small_1dp,
                    style = typography.labelSmall,
                    isUppercase = true,
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.padding_small_4dp),
                        start = dimensionResource(id = R.dimen.padding_regular_8dp)
                    ),
                )

                Text(
                    text = spell.description,
                    style = typography.bodyMedium,
                    fontSize = textSizeResource(id = R.dimen.text_size_small_14sp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(
                        start = 8.dp,
                        top = 12.dp,
                        end = 16.dp
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_regular_8dp),
                            start = dimensionResource(id = R.dimen.padding_regular_8dp),
                            bottom = dimensionResource(id = R.dimen.padding_small_4dp)
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconWithText(
                        R.drawable.ic_nav_fire,
                        stringResource(id = R.string.light_value, spell.lightColor)
                    )
                    Spacer(
                        modifier = Modifier.padding(
                            all = dimensionResource(id = R.dimen.padding_regular_8dp)
                        )
                    )
                    IconWithText(
                        R.drawable.ic_nav_home,
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
            Spacer(modifier = Modifier.padding(all = dimensionResource(id = R.dimen.padding_small_4dp)))

        }
    }
}

@Composable
fun IconWithText(
    @DrawableRes icon: Int,
    text: String
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = dimensionResource(id = R.dimen.space_regular_8dp)
        )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = text,
            modifier = Modifier.size(size = dimensionResource(id = R.dimen.icon_size_xsmall_16dp))
        )
        Text(text = text, style = typography.bodyMedium)
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
        HomeRepositoryOffline(context).getJsonData(),
        "Welcome to Home",
        null)

    SpellBookTheme {
       // SectionElement()
        ScreenFlow(
            uiState = uiState
        )
    }
}
