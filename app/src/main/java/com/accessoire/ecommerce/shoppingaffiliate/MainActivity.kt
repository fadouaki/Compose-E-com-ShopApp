package com.accessoire.ecommerce.shoppingaffiliate

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.accessoire.ecommerce.shoppingaffiliate.Model.Category.CategoryModelItem
import com.accessoire.ecommerce.shoppingaffiliate.Model.OfferModel.OfferModelItem
import com.accessoire.ecommerce.shoppingaffiliate.Model.ProdutStore.dataProductModelItem
import com.accessoire.ecommerce.shoppingaffiliate.RetrofitData.StoreViewModel
import com.accessoire.ecommerce.shoppingaffiliate.ui.CategoryCard
import com.accessoire.ecommerce.shoppingaffiliate.ui.ImageSlider
import com.accessoire.ecommerce.shoppingaffiliate.ui.ProductCard
import com.accessoire.ecommerce.shoppingaffiliate.ui.ProductScreen
import com.accessoire.ecommerce.shoppingaffiliate.ui.SplashScreen
import com.accessoire.ecommerce.shoppingaffiliate.ui.theme.ShoppingAffiliateTheme
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingAffiliateTheme {
                val navController = rememberNavController()
                val viewModel: StoreViewModel = viewModel()
                val products by viewModel.products.collectAsState()
                val categories by viewModel.categories.collectAsState()
                val offers by viewModel.offers.collectAsState()
                val indexProduct = remember { mutableIntStateOf(0) }

                NavHost(navController = navController, startDestination = "splash_screen") {
                    composable("splash_screen") {
                        SplashScreen(
                            products,
                            categories,
                            offers,
                            navController = navController
                        )
                    }
                    composable("home_screen") {
                        Home_Screen(
                            products,
                            categories,
                            offers,
                            indexProduct,
                            navController = navController
                        )
                    }
                    composable("product_screen") {
                        ProductScreen(
                            products,
                            indexProduct,
                            navController = navController,
                            onClickAddToCart = {}
                        )
                    }
                }


            }

        }
    }
}

@Composable
fun Home_Screen(
    products: List<dataProductModelItem>,
    categories: List<CategoryModelItem>,
    offers: List<OfferModelItem>,
    indexProduct: MutableState<Int>,
    navController: NavHostController) {
    val Home_navController = rememberNavController()
    val hazeState = remember { HazeState() }
    Scaffold(
        bottomBar = {
            var selectedTabIndex by remember { mutableIntStateOf(1) }
            Box(
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 50.dp,
                        top = 4.dp
                    )
                    .fillMaxWidth()
                    .height(50.dp)
                    .hazeChild(state = hazeState, shape = CircleShape)
                    .border(
                        width = Dp.Hairline,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = .8f),
                                Color.White.copy(alpha = .2f),
                            ),
                        ),
                        shape = CircleShape
                    )
            ) {
                BottomBarTabs(
                    tabs,
                    selectedTab = selectedTabIndex,
                    onTabSelected = {
                        selectedTabIndex = tabs.indexOf(it)
                        when (it) {
                            BottomBarTab.Home -> Home_navController.navigate("main_screen")
                            BottomBarTab.Coupon -> Home_navController.navigate("coupon_screen")
                            BottomBarTab.Settings -> Home_navController.navigate("settings_screen")
                        }
                    }
                )
                val animatedSelectedTabIndex by animateFloatAsState(
                    targetValue = selectedTabIndex.toFloat(),
                    label = "animatedSelectedTabIndex",
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioLowBouncy,
                    )
                )
                val animatedColor by animateColorAsState(
                    targetValue = tabs[selectedTabIndex].color,
                    label = "animatedColor",
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                    )
                )
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                ) {
                    val path = Path().apply {
                        addRoundRect(
                            RoundRect(
                                size.toRect(),
                                CornerRadius(size.height)
                            )
                        )
                    }
                    val length = PathMeasure().apply { setPath(path, false) }.length

                    val tabWidth = size.width / tabs.size
                    drawPath(
                        path,
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                animatedColor.copy(alpha = 0f),
                                animatedColor.copy(alpha = 1f),
                                animatedColor.copy(alpha = 1f),
                                animatedColor.copy(alpha = 0f),
                            ),
                            startX = tabWidth * animatedSelectedTabIndex,
                            endX = tabWidth * (animatedSelectedTabIndex + 1),
                        ),
                        style = Stroke(
                            width = 6f,
                            pathEffect = PathEffect.dashPathEffect(
                                intervals = floatArrayOf(length / 2, length)
                            )
                        )
                    )
                }
            }
        }
    ) { padding ->
        NavigationGraph(hazeState, padding, Home_navController, products,categories,offers, indexProduct,navController)
    }
}

@Composable
fun NavigationGraph(
    hazeState: HazeState,
    paddingValues: PaddingValues,
    navController: NavHostController,
    products: List<dataProductModelItem>,
    categories: List<CategoryModelItem>,
    offers: List<OfferModelItem>,
    indexProduct: MutableState<Int>,
    mainNavController: NavHostController
) {
    NavHost(navController = navController, startDestination = "main_screen") {

        composable("settings_screen") {
            SettingActivity(hazeState)
        }
        composable("coupon_screen") {
            CouponActivity(hazeState,
                paddingValues)
        }
        composable("main_screen") {
            HomePreview(
                hazeState,
                paddingValues,
                products,
                categories,
                offers,
                indexProduct,
                navController = mainNavController
            )
        }
        composable("product_screen") {
            ProductScreen(
                products,
                indexProduct,
                navController = mainNavController,
                onClickAddToCart = {}
            )
        }

    }
}

@Composable
fun HomePreview(
    hazeState: HazeState,
    paddingValues: PaddingValues,
    products: List<dataProductModelItem>,
    categories: List<CategoryModelItem>,
    offers: List<OfferModelItem>,
    indexProduct: MutableState<Int>,
    navController: NavHostController
) {
    ShoppingAffiliateTheme {

        Column(
            modifier = Modifier
                .haze(
                    hazeState,
                    backgroundColor = MaterialTheme.colorScheme.background,
                    tint = Color.Black.copy(alpha = .2f),
                    blurRadius = 30.dp,
                )
                .padding(top = 30.dp)
        ) {
            ImageSlider(offers)
            CategoryCard(categories)

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(
                    top = 5.dp,
                    bottom = paddingValues.calculateBottomPadding()
                ),
                modifier = Modifier
                    .fillMaxSize(),

                ) {
                items(products.size) { index ->
                    ProductCard(
                        products[index],
                        onClickAtProduct = {
                            indexProduct.value = products[index].id
                            navController.navigate("product_screen")
                        }
                    )
                }

            }
        }


    }
}

sealed class BottomBarTab(val title: String, val icon: ImageVector, val color: Color) {
    data object Coupon : BottomBarTab(
        title = "Coupons",
        icon = Icons.Rounded.ShoppingCart,
        color = Color(0xFFFFA574)
    )

    data object Home : BottomBarTab(
        title = "Home",
        icon = Icons.Rounded.Home,
        color = Color(0xFFFA6FFF)
    )

    data object Settings : BottomBarTab(
        title = "Settings",
        icon = Icons.Rounded.Settings,
        color = Color(0xFFADFF64)
    )
}

val tabs = listOf(
    BottomBarTab.Coupon,
    BottomBarTab.Home,
    BottomBarTab.Settings,
)

@Composable
fun BottomBarTabs(
    tabs: List<BottomBarTab>,
    selectedTab: Int,
    onTabSelected: (BottomBarTab) -> Unit,
) {
    CompositionLocalProvider(
        LocalTextStyle provides LocalTextStyle.current.copy(
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
        ),
        LocalContentColor provides Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            for (tab in tabs) {
                val alpha by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .35f,
                    label = "alpha"
                )
                val scale by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .98f,
                    visibilityThreshold = .000001f,
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                    ),
                    label = "scale"
                )
                Column(
                    modifier = Modifier
                        .scale(scale)
                        .alpha(alpha)
                        .fillMaxHeight()
                        .weight(1f)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                onTabSelected(tab)
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(imageVector = tab.icon,
                        modifier = Modifier.size(20.dp),
                        contentDescription = "tab ${tab.title}")
                    Text(text = tab.title)
                }
            }
        }
    }
}
