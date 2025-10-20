package com.aviv.ui_components.listings_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ListingsItemComponent(
    model: ListingsItemModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(12.dp),
                color = Color.White
            )
    ) {
        AsyncImage(
            model = model.image,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListingsItemComponentPreview() {
    ListingsItemComponent(
        model = ListingsItemModel(
            title = "Preview title",
            price = "15000",
            image = "https://picsum.photos/200/300"
        )
    )
}