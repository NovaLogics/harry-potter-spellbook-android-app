package novalogics.android.harrysspellbook.ui.common.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import novalogics.android.harrysspellbook.R

@Composable
fun CustomHeaderComponent() {
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
}
