package com.aviv.ui_components.details_header

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.aviv.ui_components.R

@Composable
fun DetailsHeaderComponent(
    model: DetailsHeaderModel,
    customHeight: Dp
) {
    AsyncImage(
        model = model.url,
        contentDescription = null,
        error = painterResource(R.drawable.landscape_placeholder_svgrepo_com),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(customHeight)
    )
}
