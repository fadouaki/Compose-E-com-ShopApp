package com.accessoire.ecommerce.shoppingaffiliate.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.accessoire.ecommerce.shoppingaffiliate.Model.ProdutStore.dataProductModelItem
import com.accessoire.ecommerce.shoppingaffiliate.R
import com.accessoire.ecommerce.shoppingaffiliate.ui.theme.PurpleGrey80

@Composable
fun ProductScreen(
    products: List<dataProductModelItem>,
    idProduct: MutableState<Int>,
    navController: NavHostController,
    onClickAddToCart: () -> Unit,
) {

    val product = products.filter { it.id == idProduct.value }[0]
    Column(
        modifier = Modifier
            .padding(vertical = 38.dp)
            .fillMaxSize()
            .padding(6.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val context = LocalContext.current
        Box {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    ImageCarousel(context = context, product = product)
                }
                item {
                    PriceProduct(product = product)
                }
                item {
                    ProductTitleAndReview(product = product)
                }
                item {
                    ProductDescription(product = product)
                }
                item {
                    SameCategoryProduct(
                        product = product,
                        products = products,
                        idProduct = idProduct,
                        navController = navController
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 10.dp)

            ) {
                ActionButtons(
                    context = context,
                    product = product,
                    onClickAddToCart = onClickAddToCart
                )
            }

        }

    }

}

// Helper functions
fun buildShareText(product: dataProductModelItem): String {
    return """
        🌟 Unlock the product everyone's talking about! 🌟
        
        Discover "${product.title}" - the game-changer you've been waiting for!
        
     Click here and check out what reviewers are saying: ${product.link}
    """.trimIndent()
}

@Composable
fun ImageCarousel(context: Context, product: dataProductModelItem) {
    var itemIndex by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(),
    ) {


        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    product.image[itemIndex]
                )
                .crossfade(true)
                .build(),
            contentDescription = "Product Image",
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .size(250.dp),
            contentScale = ContentScale.FillBounds,
            onState = { state ->

            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            LazyRow(
                modifier = Modifier
                    .padding(top = 10.dp, start = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(product.image.size) { index ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(
                                product.image[index]
                            )
                            .crossfade(true)
                            .build(),
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .border(
                                if (itemIndex == index)
                                    2.dp
                                else
                                    0.dp,
                                Color.Black
                            )
                            .clickable {
                                itemIndex = index
                            }
                            .size(40.dp)
                            .padding(4.dp),
                        onState = { state ->

                        }
                    )
                }
            }
            Image(
                modifier = Modifier
                    .graphicsLayer {
                        shape = RoundedCornerShape(26.dp)
                        clip = true
                    }
                    .background(Color.Black.copy(alpha = 0.2f), shape = RoundedCornerShape(26.dp))
                    .align(Alignment.BottomEnd)
                    .clickable {
                        // Create the share intent
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(
                                Intent.EXTRA_TEXT,
                                buildShareText(product)
                            )
                            type = "text/plain"
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        }

                        // Start the share activity
                        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
                    }
                    .padding(8.dp)
                    .size(30.dp),
                painter = painterResource(id = R.drawable.ic_share),
                contentDescription = "Back"
            )

        }


    }


}

@Composable
fun PriceProduct(product: dataProductModelItem) {
    //pricewith discount
    Row(
        modifier = Modifier
            .padding(10.dp)
            .height(30.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Current Price
        Text(
            text = "\$ ${product.price}",
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        // Original Price with Strikethrough
        Text(
            text = "\$ 365",
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough
            )
        )

        // Discount Percentage
        Text(
            text = "19% off",
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                color = Color.Red
            )
        )
    }
}


@Composable
fun ProductDescription(product: dataProductModelItem) {
    // get local density from composable
    Card(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                fontWeight = FontWeight.W400,
                modifier = Modifier.padding(10.dp)
            )
        }
    }


}

@Composable
fun ProductTitleAndReview(product: dataProductModelItem) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = product.title,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            fontWeight = FontWeight.W400,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 5.dp)
        ) {
            repeat(5) { index ->
                if (product.rating.rate - (index) > 0.5 && (product.rating.rate - index) < 1)
                    HalfColoredStarIcon()
                else
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color(
                            if ((product.rating.rate - index).toInt() > 0) {
                                0xFFFFA000
                            } else
                                0xFF888888
                        ),
                        modifier = Modifier.size(15.dp)
                    )

            }
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "${product.rating.rate} • ${product.rating.count} Reviews",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.weight(1f))
        }

    }
}

@Composable
fun SameCategoryProduct(
    product: dataProductModelItem,
    products: List<dataProductModelItem>,
    idProduct: MutableState<Int>,
    navController: NavHostController
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .background(PurpleGrey80)
            .height(5.dp),
    )
    Text(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
        text = "Same Category Products",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
    )
    val sameCategoryProduct =
        products.filter { it.category == product.category && it.id != idProduct.value }
    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(vertical = 5.dp, horizontal = 5.dp),
        modifier = Modifier
            .padding(bottom = 60.dp)
            .height(570.dp),

        ) {
        items(sameCategoryProduct.size) { index ->
            ProductCard(sameCategoryProduct[index], onClickAtProduct = {
                idProduct.value = sameCategoryProduct[index].id
                navController.navigate("product_screen")
                {
                    popUpTo("product_screen") { inclusive = true }
                }
            })
        }
    }

}

@Composable
fun ActionButtons(context: Context, product: dataProductModelItem, onClickAddToCart: () -> Unit) {
    if (product.link.isNotEmpty())
        Row(
            modifier = Modifier
                .height(50.dp)
                .wrapContentWidth()
                .graphicsLayer {
                    shape = RoundedCornerShape(26.dp)
                    clip = true
                }
                .background(Color.Black.copy(alpha = 0.1f), shape = RoundedCornerShape(26.dp)),
        ) {
            /* Button(
                 onClick = { onClickAddToCart() },
                 shape = RoundedCornerShape(15.dp),
                 colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                 modifier = Modifier.padding(horizontal = 10.dp),
                 border = BorderStroke(1.dp, Color.Black)

             ) {
                 Text("Add to Cart", color = Color.Black)
             }*/
                Button(
                    onClick = {
                        val intent =
                            Intent(Intent.ACTION_VIEW, Uri.parse(product.link))
                        context.startActivity(intent)
                    },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    modifier = Modifier.padding(horizontal = 10.dp)

                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        tint = Color.White,
                        contentDescription = ""
                    )
                    Text(" Check Store ", color = Color.White)

                }


        }
}

@Preview
@Composable
fun HalfColoredStarIcon() {
    Canvas(modifier = Modifier.size(15.dp)) {
        val starPath = Path().apply {
            // Define the star path here
            // This is a simplified example; you might need to adjust the path to match the star shape
            moveTo(size.width * 0.5f, 0f)
            lineTo(size.width * 0.61f, size.height * 0.35f)
            lineTo(size.width, size.height * 0.35f)
            lineTo(size.width * 0.68f, size.height * 0.57f)
            lineTo(size.width * 0.79f, size.height)
            lineTo(size.width * 0.5f, size.height * 0.75f)
            lineTo(size.width * 0.21f, size.height)
            lineTo(size.width * 0.32f, size.height * 0.57f)
            lineTo(0f, size.height * 0.35f)
            lineTo(size.width * 0.39f, size.height * 0.35f)
            close()
        }

        // Draw the left half of the star in yellow
        clipPath(starPath) {
            drawRect(
                color = Color(0xFFFFA000),
                size = Size(size.width / 2, size.height)
            )
        }

        // Draw the right half of the star in gray
        clipPath(starPath) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(size.width / 2, 0f),
                size = Size(size.width / 2, size.height)
            )
        }
    }
}