package com.example.rave2b.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.rave2b.R

@Composable
fun HomeScreen()
{
    val scroll = rememberScrollState()
    val raveObligationTitle = stringArrayResource(id = R.array.rave_obligation_titles)
    val raveObligationDesc = stringArrayResource(id = R.array.rave_obligation_descriptions)
    val raveObligations = raveObligationTitle.zip(raveObligationDesc)

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .padding(top = 50.dp, bottom = 120.dp, start = 10.dp, end = 10.dp),
        contentAlignment = Alignment.TopStart)
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scroll)
        )
        {
            Column (modifier = Modifier.clip(RoundedCornerShape(10.dp))){

                Image(
                    painter = painterResource(id = R.drawable.hometop),
                    contentDescription = "rave",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                raveObligations.forEach { (title, description) ->
                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .height(150.dp)
                            .background(color = Color(color = ContextCompat.getColor(LocalContext.current, R.color.snackBarColor)), shape = RoundedCornerShape(10.dp))
                            .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = description,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}