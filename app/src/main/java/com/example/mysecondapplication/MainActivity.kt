package com.example.mysecondapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysecondapplication.ui.theme.MySecondApplicationTheme
import androidx.compose.ui.draw.clip
import com.example.mysecondapplication.model.Product
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySecondApplicationTheme {
                MainScreen()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("Shop list") },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Person, contentDescription = "Profile")
            }
        }
    )
}

@Composable
fun ProductCard(
    product: Product,
    onAddToFavourite: () -> Unit,
    onBuy: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = product.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(text = product.price)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = onAddToFavourite,
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Add to favourite")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = onBuy,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B4513)),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Buy")
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(onItemSelected: (String) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Product") },
            label = { Text("Product") },
            selected = false,
            onClick = { onItemSelected("Product") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            label = { Text("Search") },
            selected = false,
            onClick = { onItemSelected("Search") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
            label = { Text("Cart") },
            selected = false,
            onClick = { onItemSelected("Cart") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = { onItemSelected("Profile") }
        )
    }
}

@Composable
fun MainScreen() {
    val products = listOf(
        Product(R.drawable.sample_image, "Leather boots", "27,5 $", "Great warm shoes from the artificial leather. You can buy this model only in our shop"),
        Product(R.drawable.sample_image, "Winter Jacket", "49,9 $", "Stylish and warm jacket perfect for winter."),
        Product(R.drawable.sample_image, "Denim Jeans", "35,0 $", "Classic denim jeans with a modern fit."),
        Product(R.drawable.sample_image, "Sneakers", "59,0 $", "Comfortable everyday sneakers.")
    )

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            BottomBar { selectedItem ->
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            items(products) { product: Product ->
                ProductCard(
                    product = product,
                    onAddToFavourite = {},
                    onBuy = { }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MySecondApplicationTheme {
        MainScreen()
    }
}
