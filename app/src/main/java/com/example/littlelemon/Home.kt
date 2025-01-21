package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun HomeScreen(navController: NavController, menuEntities: List<MenuItemEntity>) {
    var SearchValue by remember { mutableStateOf(TextFieldValue()) };
    var selectedCategory by remember { mutableStateOf("all") };

    Column(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .height(100.dp)
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "HeaderLogo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(80.dp)
                    .padding(start = 110.dp, top = 40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
//                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .padding(start = 20.dp, top = 40.dp)
                    .clickable { navController.navigate("profile") }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(color = Color(0xFF495E57)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Little Lemon",
                    color = Color(0xFFF4CE14),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier
                        .padding(top = 20.dp, start = 20.dp)
                )
                Row(
                    modifier = Modifier
                        .height(150.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .width(230.dp)
                    ) {
                        Text(
                            text = "Chicago",
                            color = Color.White,
                            fontFamily = FontFamily.Serif,
                            fontSize = 30.sp,
                            modifier = Modifier
                                .padding(top = 0.dp, start = 20.dp)
                        )
                        Text(
                            text = "We are a family-owned Mediterranean restaurant, " +
                                    "focused on traditional recipes served with a modern twist",
                            color = Color.White,
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 20.dp, top = 5.dp)
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.heroimage),
                        contentDescription = "HeroImage",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .size(120.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
                TextField(
                    value = SearchValue,
                    onValueChange = {SearchValue = it},
                    label = { Text(text = "Search") },
                    modifier = Modifier
                        .width(390.dp)
                        .padding(start = 20.dp)

                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(start = 20.dp, top = 10.dp)
                ) {
                    Text(
                        text = "ORDER FOR DELIVERY!",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Button(
                        onClick = {
                            if(selectedCategory == "starters") {
                                selectedCategory = "all"
                            } else {
                                selectedCategory = "starters"
                            }
                        },
                        modifier = Modifier
                            .padding(start = 20.dp, top = 10.dp)
                            .width(85.dp)
                            .height(30.dp)
                            .clip(RoundedCornerShape(7.dp)),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFEDEFEE),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "Starters")
                    }
                    Button(
                        onClick = {
                                if(selectedCategory == "mains") {
                                    selectedCategory = "all"
                                } else {
                                    selectedCategory = "mains"
                                }
                        },
                        modifier = Modifier
                            .padding(start = 20.dp, top = 10.dp)
                            .width(75.dp)
                            .height(30.dp)
                            .clip(RoundedCornerShape(7.dp)),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFEDEFEE),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "Mains")
                    }
                    Button(
                        onClick = {
                            if(selectedCategory == "desserts") {
                                selectedCategory = "all"
                            } else {
                                selectedCategory = "desserts"
                            }
                        },
                        modifier = Modifier
                            .padding(start = 20.dp, top = 10.dp)
                            .width(95.dp)
                            .height(30.dp)
                            .clip(RoundedCornerShape(7.dp)),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFEDEFEE),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "Desserts")
                    }
                    Button(
                        onClick = {
                            if(selectedCategory == "drinks") {
                                selectedCategory = "all"
                            } else {
                                selectedCategory = "drinks"
                            }
                        },
                        modifier = Modifier
                            .padding(start = 20.dp, top = 10.dp)
                            .width(80.dp)
                            .height(30.dp)
                            .clip(RoundedCornerShape(7.dp)),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFEDEFEE),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "Drinks")
                    }
                }
            }

        }

        val filteredMenu = menuEntities.filter { item ->
            (selectedCategory == "all" || item.category.lowercase() == selectedCategory) &&
                    item.title.contains(SearchValue.text, ignoreCase = true)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            items(filteredMenu) { menuItem ->
                MenuItemCard(menuItem)
            }
        }

    }

}


fun getDrawableId(context: Context, imageName: String): Int {
    return context.resources.getIdentifier(imageName, "drawable", context.packageName)
}

@Composable
fun MenuItemCard(MenuItem: MenuItemEntity) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
        modifier = Modifier
            .padding(start = 10.dp)
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.Gray)
        ){}
        Row(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(295.dp)
            ) {
                Text(
                    text = MenuItem.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 5.dp)
                )
                Text(
                    text = MenuItem.description,
                    modifier = Modifier
                        .padding(top = 3.dp)
                )
                Text(
                    text = "$${MenuItem.price} ",
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp),
                )
            }
            Image(
                painter = painterResource(id = getDrawableId(context, MenuItem.image)),
                contentDescription = MenuItem.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
                    .size(86.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
        }
    }
}
