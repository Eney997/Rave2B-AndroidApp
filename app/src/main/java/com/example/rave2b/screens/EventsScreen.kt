package com.example.rave2b.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rave2b.R
import com.example.rave2b.data.TicketDto
import com.example.rave2b.data.TicketViewModel

@Composable
fun EventsScreen() {
    val ticketViewModel: TicketViewModel = viewModel()
    val tickets by ticketViewModel.tickets.collectAsState()

    LaunchedEffect(Unit) {
        ticketViewModel.fetchTickets()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 50.dp, bottom = 120.dp, start = 10.dp, end = 10.dp),
        contentAlignment = Alignment.TopStart
    ) {
        LazyColumn(modifier = Modifier) {
            items(tickets) { ticket ->
                TicketItem(ticket)
            }
        }
    }
}

@Composable
fun TicketItem(ticket: TicketDto) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(bottom = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp)) // Clip children
                .background(
                    color = Color(ContextCompat.getColor(LocalContext.current, R.color.snackBarColor)),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(start = 190.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.applogo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("DJ One: ${ticket.djNameOne}", color = Color.White)
            Text("DJ Two: ${ticket.djNameTwo}", color = Color.White)
            Text("DJ Three: ${ticket.djNameThree}", color = Color.White)
            Text("DJ Four: ${ticket.djNameFour}", color = Color.White)
            Text("Date: ${ticket.eventDate}", color = Color.Cyan)
        }
    }

}



