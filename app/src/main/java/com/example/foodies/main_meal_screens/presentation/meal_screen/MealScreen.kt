package com.example.foodies.main_meal_screens.presentation.meal_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.foodies.R
import com.example.foodies.bottom_bar.presentation.noRippleClickable
import com.example.foodies.cart_screen.data.db.CartMeal
import com.example.foodies.cart_screen.presentation.CartScreenVM
import com.example.foodies.main_meal_screens.data.remote.meal.Meal
import com.example.foodies.main_meal_screens.presentation.MainMealScreensVM
import com.example.foodies.ui.theme.dimens
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlin.reflect.full.memberProperties

@Composable
fun MealScreen(
    mainMealScreensVM: MainMealScreensVM,
    navController: NavHostController,
    systemUiController: SystemUiController,
    cartScreenVM: CartScreenVM
) {
    //Get meal and check is it in cart
    val meal = mainMealScreensVM.mealByName.meals[0]
    cartScreenVM.checkIsMealInCart(meal.strMeal)

    //Changing text if meal in cart
    var textForAddButton by rememberSaveable { mutableStateOf("") }
    textForAddButton = if(cartScreenVM.isInCart) "В корзине" else "В корзину за 345 р."

    //Iterate over data class and add values to ingredients
    var ingredients = ""
    for(ingredient in Meal::class.memberProperties) {
        if(ingredient.name.take(13) == "strIngredient") {
            if((ingredient.get(meal) != "") && ingredient.get(meal) != null) {
                ingredients += if(ingredients.isNotEmpty()) {
                    "${ingredient.get(meal)}, ".lowercase()
                } else {
                    "${ingredient.get(meal)}, "
                }
            }
        }
    }
    ingredients = ingredients.dropLast(2)

    //Iterate over data class again
    val measures = mutableListOf<String>()
    for(measure in Meal::class.memberProperties) {
        if(measure.name.take(10) == "strMeasure") {
            if((measure.get(meal) != " ") && (measure.get(meal) != "") && (measure.get(meal) != null)) {
                measures += measure.get(meal).toString()
            }
        }
    }

    //I used scaffold to create bottom add to cart button
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(70.dp),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    //Add to cart button
                    Button(
                        onClick = {
                            cartScreenVM.upsertNewMealToCart(CartMeal(
                                name = meal.strMeal,
                                amount = 1,
                                image = meal.strMealThumb
                            ))
                            textForAddButton = "В корзине"
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxSize(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.tertiaryContainer
                        )
                    ) {
                        Text(
                            text = textForAddButton,
                            fontSize = 17.sp
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        //Main column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Box with image
            Box(
                modifier = Modifier
                    .fillMaxHeight(MaterialTheme.dimens.mealScreenImageBoxHeight)
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if(meal.strMealThumb != "") {
                        AsyncImage(
                            model = meal.strMealThumb,
                            contentDescription = "Meal image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Icon go back",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 16.dp, top = 8.dp)
                        .noRippleClickable { navController.popBackStack() }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            //Column with text and ingredients
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = meal.strMeal,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = ingredients,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 17.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            //Lazy column with ingredients and measures
            val ingredientsList = ingredients.split(", ")
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.surfaceTint)

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            PaddingValues(
                                bottom = innerPadding.calculateBottomPadding()
                            )
                        ),
                ) {
                    itemsIndexed(measures) { index, measure ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(start = 16.dp, end = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = ingredientsList[index].lowercase(),
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = 17.sp
                            )

                            Text(
                                text = measure,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.surfaceTint)
                    }
                }
            }
        }
    }
}