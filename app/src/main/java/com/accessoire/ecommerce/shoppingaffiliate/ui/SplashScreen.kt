package com.accessoire.ecommerce.shoppingaffiliate.ui

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.accessoire.ecommerce.shoppingaffiliate.Model.Category.CategoryModelItem
import com.accessoire.ecommerce.shoppingaffiliate.Model.OfferModel.OfferModelItem
import com.accessoire.ecommerce.shoppingaffiliate.Model.ProdutStore.dataProductModelItem
import com.accessoire.ecommerce.shoppingaffiliate.R
import java.lang.Thread.sleep


@Composable
fun SplashScreen(
    products: List<dataProductModelItem>,
    category: List<CategoryModelItem>,
    offers: List<OfferModelItem>,
    navController: NavController
) {
    // State to manage the alpha and scale animations
    val animatedAlpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )

    val animatedScale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )

    // Box to hold the entire content centered
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        // Column to hold logo and progress indicator
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Splash Logo with animation
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "App Logo",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(200.dp)
                    .graphicsLayer(
                        alpha = animatedAlpha,
                        scaleX = animatedScale,
                        scaleY = animatedScale
                    )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Splash Text with animation
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.W300,
                color = Color.Black,
                modifier = Modifier
                    .alpha(animatedAlpha)
            )

            Spacer(modifier = Modifier.height(106.dp))
            // Progress indicator
            LoadingIndicator()
        }
    }

    LaunchedEffect(products.isNotEmpty() && category.isNotEmpty() && offers.isNotEmpty()) {
        if (products.isNotEmpty() && category.isNotEmpty() && offers.isNotEmpty()) {
            sleep(500)
            navController.navigate("home_screen") {
                popUpTo("splash_screen") { inclusive = true }
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    CircularProgressIndicator(
        color = Color.Black,
        modifier = Modifier.size(48.dp)
    )
}