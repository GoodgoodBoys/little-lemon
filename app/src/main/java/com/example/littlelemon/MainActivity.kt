package com.example.littlelemon

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import io.ktor.client.*
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.littlelemon.MenuNetwork
import com.google.gson.Gson
import io.ktor.client.statement.bodyAsText
import io.ktor.client.*
import io.ktor.client.request.get
import io.ktor.client.call.body
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json as KtorJson
import kotlinx.serialization.decodeFromString
import android.content.Context
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val client = HttpClient {
            install(ContentNegotiation) {
                json(Json {ignoreUnknownKeys = true})
            }
        }
        data class MenuItem(
            val id: Int,
            val title: String,
            val description: String,
            val price: String,
            val image: String,
            val category: String
        )
        data class MenuResponse(val menu: List<MenuItem>)
        val db = AppDatabase.getDatabase(applicationContext)
        val menuItemDao = db.menuItemDao()
        val menuItemsState = mutableStateOf<List<MenuItemEntity>>(emptyList())
        val menuItems = Gson().fromJson(menuItemsJson, MenuResponse::class.java)

        val menuEntities = menuItems.menu.map { item ->
            MenuItemEntity(
                id = item.id,
                title = item.title,
                description = item.description,
                price = item.price.toDouble(),
                image = item.image,
                category = item.category,
            )
        }

        lifecycleScope.launch {
            try {
                val response: HttpResponse =
                    client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")

                val jsonString = response.bodyAsText()

                val menuNetwork: MenuNetwork = KtorJson.decodeFromString(jsonString)

                val menuItems = menuNetwork.menu

                val menuItemEntities = menuItems.map { networkItem ->
                    MenuItemEntity(
                        id = networkItem.id,
                        title = networkItem.title,
                        description = networkItem.description,
                        price = networkItem.price,
                        image = networkItem.image,
                        category = networkItem.image
                    )
                }

                withContext(Dispatchers.Main) {
                    menuItemsState.value = menuItemEntities
                    Toast.makeText(this@MainActivity,
                        "Menu loaded: ${menuItems.size} items",
                        Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Failed to load menu", Toast.LENGTH_SHORT).show()
                }
            }
        }

        setContent {
            val navController = rememberNavController();
            MyNavigation(navController = navController, context = applicationContext, menuEntities);
        }
    }
}


@Composable
fun DisplayMenuItems(menuItems: List<MenuItemEntity>) {
    val context = LocalContext.current

    Column(
    ) {
        if (menuItems.isEmpty()) {
            Text("Loading menu...")
        } else {
            menuItems.forEach { menuItem ->
                Text("ID: ${menuItem.id}")
                Text("Title: ${menuItem.title}")
                Text("Description: ${menuItem.description}")
                Text("Price: $${menuItem.price}")
//                Image(
//                    painter = painterResource(id = getDrawableId(context, menuItem.image)),
//                    contentDescription = null
//                )
            }
        }
    }
}
