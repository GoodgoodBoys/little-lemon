package com.example.littlelemon

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "price")
    val price: Double,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "category")
    val category: String
)

@Dao
interface MenuItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(menuItems: List<MenuItemEntity>)

    @Query("SELECT * FROM menu_items")
    fun getAllMenuItems(): List<MenuItemEntity>
}

@Database(entities = [MenuItemEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "menu_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

val menuItemsJson = """
{
  "menu": [
    {
      "id": 1,
      "title": "Greek Salad",
      "description": "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
      "price": "10",
      "image": "greeksalad",
      "category": "starters"
    },
    {
      "id": 2,
      "title": "Lemon Desert",
      "description": "Traditional homemade Italian Lemon Ricotta Cake.",
      "price": "10",
      "image": "lemondessert",
      "category": "desserts" 
    },
    {
      "id": 3,
      "title": "Grilled Fish",
      "description": "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil.",
      "price": "10",
      "image": "grilledfish",
      "category": "mains"
    },
    {
      "id": 4,
      "title": "Pasta",
      "description": "Penne with fried aubergines, cherry tomatoes, tomato sauce, fresh chilli, garlic, basil & salted ricotta cheese.",
      "price": "10",
      "image": "pasta",
      "category": "mains"
    },
    {
      "id": 5,
      "title": "Bruschetta",
      "description": "Oven-baked bruschetta stuffed with tomatos and herbs.",
      "price": "10",
      "image": "bruschetta",
      "category": "starters"
    }
  ]
}
"""


