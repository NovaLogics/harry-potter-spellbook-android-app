package novalogics.android.hexa.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import novalogics.android.hexa.R
import novalogics.android.hexa.ui.common.textSizeResource
import novalogics.android.hexa.ui.theme.SpellBookTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetSample() {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var skipPartiallyExpanded by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = skipPartiallyExpanded
        )

    // App content
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            Modifier.toggleable(
                value = skipPartiallyExpanded,
                role = Role.Checkbox,
                onValueChange = { checked -> skipPartiallyExpanded = checked }
            )
        ) {
            Checkbox(checked = skipPartiallyExpanded, onCheckedChange = null)
            Spacer(Modifier.width(16.dp))
            Text("Skip partially expanded State")
        }
        Button(
            onClick = { openBottomSheet = !openBottomSheet },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Show Bottom Sheet")
        }
    }


    // Sheet content
    if (openBottomSheet) {

        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = bottomSheetState,

        ) {
            SpellBottomSheetContent()
        }
    }
}

@Composable
fun SpellBottomSheetContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append("Spell Name : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Accio")
                }
            },
            style = MaterialTheme.typography.titleLarge,
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
            style = MaterialTheme.typography.bodyMedium,
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


@Preview(showSystemUi = true)
@Composable
fun BottomSheetPreview() {
    SpellBookTheme {
        Surface {
            ModalBottomSheetSample()
        }
    }
}
