package com.demo.bustracking

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

import com.demo.bustracking.ui.theme.BusTrackingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusTrackingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val uri = "tel:${stringResource(R.string.phoneNumber)}"
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse(uri))
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)){
                        MapScreen() {
                            openGMapWithQuery(query = "${it.latitude},${it.longitude}")
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        BusList {
                            openGMapWithQuery(query = "${it.latitude},${it.longitude}")
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        InfoTextBox("Number of passengers:")
                        Spacer(modifier = Modifier.height(20.dp))
                        InfoTextBox("Number of Seats available:")
                        Spacer(modifier = Modifier.height(250.dp))
                        Text(
                            text = "For Queries Contact us" ,
                            color = Color.Blue ,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .align(Alignment.End)
                                .clickable {
                                    this@MainActivity.startActivity(intent)
                                }
                            )
                    }
                }
            }
        }
    }


    // this function is used to open the map with location based on the longitude latitude
    fun openGMapWithQuery(latitude : String="0 ", longitude : String ="0",query : String  ){
        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?q=$query")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps") // Use Google Maps app

        if (mapIntent.resolveActivity(this.packageManager) != null) {
            this.startActivity(mapIntent)
        } else {

        }
    }
}


@Composable
fun MapScreen(
    onClick : (Location)-> Unit
){

    var latitude by remember {
        mutableStateOf("")
    }
    var longitude by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ){
        Text(text =  "Latitude" , modifier = Modifier.padding(10.dp).fillMaxWidth() , fontWeight = FontWeight.ExtraBold)
       TextField(
           value = latitude,
           onValueChange = {
               latitude = it
           },
           modifier = Modifier
               .fillMaxWidth()
               .clip(RoundedCornerShape(15.dp))
       )
       Spacer(modifier = Modifier.height(20.dp))
        Text(text =  "Longitude" , modifier = Modifier.padding(10.dp).fillMaxWidth() , fontWeight = FontWeight.ExtraBold)
        TextField(
            value = longitude,
            onValueChange = {
                longitude = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .width(150.dp)
                .height(40.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(15.dp)
                )
                .align(Alignment.CenterHorizontally)
                .clickable {
                           onClick(Location(
                               latitude, longitude
                           ))
                },
            contentAlignment = Alignment.Center
        ){
            Text(text = "Open Map")
        }
    }
}

@Composable
fun BusList(onItemClick:(Location)-> Unit){
    var isClicked by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .border(width = 1.dp , color = Color.Black , shape = RoundedCornerShape(10.dp))
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isClicked = true
                }
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Select the Bus",
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(0.8f),
            )
            Icon(
                imageVector = if (isClicked) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = isClicked,
            onDismissRequest = { isClicked = false}
        ){
            Constant.locations.forEachIndexed { index, location ->
                DropdownMenuItem(
                    text = {
                        Text(text = location.name)
                    },
                    onClick = {
                        onItemClick(location)
                        isClicked = false
                    }
                )
            }
        }
    }
}

@Composable
fun InfoTextBox(
    title : String
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            //.height(50.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
    ){
        Text(text = title , modifier = Modifier.padding(10.dp))
    }

}
