package novalogics.android.harrysspellbook.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import novalogics.android.harrysspellbook.R
import novalogics.android.harrysspellbook.ui.common.textSizeResource
import novalogics.android.harrysspellbook.ui.theme.SpellBookTheme

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .wrapContentSize(Alignment.Center)
    ) {
        ElevatedCard(
            modifier = Modifier,
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(id = R.dimen.elevation_medium_4dp)
            ),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_large_16dp))
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.size_xlarge_64dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
                Spacer(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.size_medium_32dp))
                )
                Text(
                    text = stringResource(id = R.string.please_wait),
                    fontSize = textSizeResource(id = R.dimen.text_size_xlarge_24sp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun SimpleLoadingPreview() {
    SpellBookTheme {
        Surface {
            LoadingScreen()
        }
    }
}
