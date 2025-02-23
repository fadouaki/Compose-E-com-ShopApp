package com.accessoire.ecommerce.shoppingaffiliate.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.accessoire.ecommerce.shoppingaffiliate.Model.ProdutStore.dataProductModelItem

@Composable
fun ProductCard(
    dataProduct: dataProductModelItem,
    onClickAtProduct: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .width(200.dp)
            .height(230.dp)
            .clickable {
                onClickAtProduct()
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),

            ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(
                        dataProduct.image[0]
                    )
                    .crossfade(true)
                    .build(),
                contentDescription = dataProduct.title,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(4.dp)),
                onState = { state ->

                }
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = dataProduct.title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color(0xFFFFA000),
                        modifier = Modifier.size(10.dp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "203", style = MaterialTheme.typography.labelSmall)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${dataProduct.rating.count} sold",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "\$ ${dataProduct.price}",
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black
            )
        }
    }
}
