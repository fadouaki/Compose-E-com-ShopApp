package com.accessoire.ecommerce.shoppingaffiliate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun CouponActivity(hazeState: HazeState, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .haze(
                hazeState,
                backgroundColor = MaterialTheme.colorScheme.background,
                tint = Color.Black.copy(alpha = .2f),
                blurRadius = 30.dp,
            )
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            Column {
                Text(
                    text = "New discount",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .background(
                            Color.Red,
                            shape = RoundedCornerShape(23)
                        ),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center // Center the text horizontally
                )
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(minSize = 150.dp),
                    modifier = Modifier
                        .fillMaxSize(),

                    ) {
                    items(3) {
                        cardProduct()
                    }

                }

            }

        }
        Box(
            modifier = Modifier.weight(1f)
        ) {
            Column {
                Text(
                    text = "Coupons",
                    color = Color.White,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .background(
                            Color.Red,
                            shape = RoundedCornerShape(23)
                        ),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center // Center the text horizontally
                )
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(1),
                    modifier = Modifier
                        .fillMaxSize(),

                    ) {
                    items(3) {
                        CouponCard()
                    }

                }


            }

        }
    }
}
@Preview
@Composable
fun CouponActivityPreview() {
    CouponActivity(HazeState(), PaddingValues(0.dp))
}


@Preview
@Composable
fun cardProduct() {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(200.dp)
            .height(190.dp)
            .clickable {
                // onClickAtProduct()
            },
        shape = RoundedCornerShape(0.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = Color.Black,
            disabledContentColor = Color.LightGray,
            disabledContainerColor = Color.Black,
        ),
    ) {
        Box {
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.product), contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(0.dp)),
                )
                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                        .fillMaxWidth()
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.Start),
                    text = "back cartable product",
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1,
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.Start),
                    text = "23 \$",
                    style = MaterialTheme.typography.labelSmall.copy(
                        textDecoration = TextDecoration.LineThrough
                    ),
                    color = Color.Red,
                    maxLines = 1,
                )
            }
            Text(
                text = " Offer only 12\$ ",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier

                    .background(
                        Color.Red, CircleShape.copy(
                            topStart = CornerSize(0), bottomStart = CornerSize(0)
                        )
                    )
                    .padding(end = 5.dp, top = 3.dp, bottom = 3.dp)
            )

        }

    }
}

@Preview
@Composable
fun CouponCard() {
    Card(
        modifier = Modifier.padding(5.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
            contentColor = Color.Black,
            disabledContentColor = Color.LightGray,
            disabledContainerColor = Color.Black,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(130.dp)
                    .clip(
                        RoundedCornerShape(0.dp).copy(
                            bottomStart = CornerSize(0), topStart = CornerSize(0)
                        )
                    ),
                painter = painterResource(id = R.drawable.product),
                contentDescription = "coupon"
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    color = Color.White,
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleSmall,
                    text = "Coupon title here - deadline date"
                )
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .background(Color.Black, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_file_copy_24),
                            contentDescription = "copy",
                            tint = Color.White,
                            modifier = Modifier.padding(5.dp)
                        )
                        Text(
                            text = "HFIJ78XNH",
                            color = Color.White,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.W800
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                                .blur(10.dp)
                        )
                    }
/*
                    Text(
                        text = "Ad",
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .background(Color.Red, CircleShape)
                            .padding(horizontal = 25.dp, vertical = 5.dp)
                    )*/

                }
                Text(
                    text = "get coupon",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End)
                        .background(Color.Red, CircleShape)
                        .padding(5.dp)
                )
            }

        }
    }

}