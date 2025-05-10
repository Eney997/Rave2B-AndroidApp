package com.example.rave2b.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rave2b.R
import com.example.rave2b.data.TicketDto
import com.example.rave2b.data.TicketViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventsScreen(myNavController: NavController) {
    val ticketViewModel: TicketViewModel = viewModel()
    val tickets by ticketViewModel.tickets.collectAsState()
    val isLoading by ticketViewModel.isLoading.collectAsState()
    val hasInternetError by ticketViewModel.hasInternetError.collectAsState()

    LaunchedEffect(Unit) {
        ticketViewModel.fetchTickets()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 50.dp, bottom = 120.dp, start = 10.dp, end = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> {
                Text(text = "Loading...",  color = Color.White, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Normal)
            }

            hasInternetError -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "No internet connection",
                        color = Color.White, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Button(onClick = { ticketViewModel.fetchTickets() },
                        modifier = Modifier
                            .width(140.dp)
                            .height(55.dp)
                            .border(
                                2.dp,
                                Color.Gray,
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                            ),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                    ) {
                        Text("Retry",style = MaterialTheme.typography.titleLarge , color = Color.Gray)
                    }
                }
            }

            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize(), reverseLayout = true) {
                    items(tickets) { ticket ->
                        TicketItem(ticket, myNavController)
                    }
                }
            }
        }
    }
}
//card for tickets
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TicketItem(ticket: TicketDto,myNavController: NavController)
{
    //catch event happened already or not
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val eventDate = try {
        LocalDate.parse(ticket.eventDate, formatter)
    } catch (e: Exception) {
        null
    }
    //take date for today
    val today = LocalDate.now()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
            .padding(bottom = 15.dp)
            .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color(ContextCompat.getColor(LocalContext.current, R.color.snackBarColor)))
    )
    {
        Text(
            text = "RAVE2B",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 20.dp)
        )
        // Text and ticket info column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, top = 30.dp, bottom = 30.dp, end = 20.dp)
        )
        {
            Text("SET-I: ${ticket.djNameOne}", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Normal, color = Color.White)
            Text("SET-II: ${ticket.djNameTwo}", color = Color.White, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Normal)
            Text("SET-III: ${ticket.djNameThree}", color = Color.White, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Normal)
            Text("SET-IV: ${ticket.djNameFour}", color = Color.White, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Normal)
            Text("Date: ${ticket.eventDate}", color = Color.White, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Normal)
            Text("Price: ${ticket.price}", color = Color.White, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Normal)

            Spacer(modifier = Modifier.height(30.dp))

            if (eventDate != null && !eventDate.isBefore(today)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "BUY",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        fontSize = 19.sp
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_right),
                        contentDescription = "buy click",
                        tint = Color.White,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                myNavController.navigate("BuyTicketScreen/${ticket.djNameOne}/${ticket.djNameTwo}/${ticket.djNameThree}/${ticket.djNameFour}/${ticket.price}/${ticket.eventDate}")
                            }
                    )
                }
            } else {
                Text(
                    text = "Event already happened",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
        // Bottom-right logo image
        Image(
            painter = painterResource(id = R.drawable.applogo),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.BottomEnd)
                .padding(bottom = 30.dp)
        )
    }
}