package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.ui.res.painterResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.checkScrollableContainerConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun ProfileScreen(navController: NavController, context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstName", "")
    val lastName = sharedPreferences.getString("lastName", "")
    val email = sharedPreferences.getString("email", "")
    val editor = sharedPreferences.edit()

    Column(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .height(100.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "HeaderLogo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(50.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = Color(0xFF495E57)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Personal information",
                color = Color.White,
                fontSize = 29.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Text(
                text = "First name",
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp)
            )
            Text(
                text = firstName.toString(),
                modifier = Modifier
                    .padding(start = 20.dp, top = 5.dp)
                    .width(370.dp),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Text(
                text = "Last name",
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp)
            )
            Text(
                text = lastName.toString(),
                modifier = Modifier
                    .padding(start = 20.dp, top = 5.dp)
                    .width(370.dp),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Text(
                text = "Email",
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp)
            )
            Text(
                text = email.toString(),
                modifier = Modifier
                    .padding(start = 20.dp, top = 5.dp)
                    .width(370.dp),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 250.dp),
        ) {
            Button(
                onClick = {
                    editor.putString("firstName", "");
                    editor.putString("lastName", "");
                    editor.putString("email", "");
                    editor.putBoolean("is_logged_in", false);

                    editor.apply();
                    navController.navigate("onboarding")

                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFF4CE14),
                    contentColor = Color.Black,
                ),
                modifier = Modifier
                    .width(360.dp)
                    .height(40.dp)
                    .fillMaxWidth()
                    .padding(start = 45.dp)
            ) {
                Text(
                    text = "Log out",
                    modifier = Modifier

                )
            }
        }

    }

}