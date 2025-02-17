package com.accessoire.ecommerce.shoppingaffiliate.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.accessoire.ecommerce.shoppingaffiliate.Model.Category.CategoryModelItem
import com.accessoire.ecommerce.shoppingaffiliate.R

@Composable
fun CategoryCard(categories: List<CategoryModelItem>,){
    LazyRow(
        modifier = Modifier.padding(horizontal = 5.dp)
    ) {
        items(categories.size) { index ->
            Card(
                colors = CardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContentColor =Color.LightGray ,
                    disabledContainerColor = Color.Black,
                ),
                modifier = Modifier.padding(5.dp)
                    .clickable {

                    },
                shape = RoundedCornerShape(8.dp),
                elevation=CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ){
                Row (
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier =  Modifier
                        .padding(vertical = 10.dp, horizontal = 22.dp)
                ){
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(
                                categories[index].image
                            )
                            .crossfade(true)
                            .build(),
                        contentDescription =" stringResource(R.string.image_slider)",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier .size(30.dp)
                            .padding(5.dp),
                        onState = { state ->

                        }
                    )
                    Text(text = categories[index].name,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(5.dp))
                }


            }
        }
    }


}
