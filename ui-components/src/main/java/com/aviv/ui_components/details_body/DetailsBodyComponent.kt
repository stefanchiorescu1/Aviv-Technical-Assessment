package com.aviv.ui_components.details_body

import android.widget.GridView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aviv.ui_components.R
import org.w3c.dom.Text

@Composable
fun DetailsBodyComponent(
    model: DetailsBodyModel
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 10.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = stringResource(R.string.details_property_type),
                style = TextStyle(
                    fontSize = 18.sp,
                )
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = model.propertyType,
                style = TextStyle(
                    fontSize = 18.sp
                )
            )



        }


    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsBodyComponentPreview() {
    DetailsBodyComponent(model = DetailsBodyModel(
        name = "Preview",
        price = 200.0,
        bedrooms = 1,
        rooms = 2,
        city = "Bucharest",
        propertyType = "Villa"
    ))
}