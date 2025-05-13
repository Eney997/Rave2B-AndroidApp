package com.example.rave2b.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rave2b.R
import com.example.rave2b.dtos.TransactionDto
import com.example.rave2b.viewmodels.TransactionViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TicketScreen() {
    val transactionViewModel: TransactionViewModel = viewModel()
    val transactions = transactionViewModel.transactions.collectAsState().value
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
    val username = sharedPref.getString("username", "") ?: ""
    val isLoading = transactionViewModel.isLoading.collectAsState().value
    val hasInternetError = transactionViewModel.hasInternetError.collectAsState().value
    val emptyMessage = transactionViewModel.emptyMessage.collectAsState().value

    LaunchedEffect(Unit) {
        transactionViewModel.fetchTransactions(username)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 50.dp, bottom = 110.dp, start = 10.dp, end = 10.dp),
    ) {
        when {

            emptyMessage != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = emptyMessage,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Loading...",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

            hasInternetError -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "No internet connection",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Button(
                            onClick = { transactionViewModel.fetchTransactions(username) },
                            modifier = Modifier
                                .width(140.dp)
                                .height(55.dp)
                                .border(
                                    2.dp,
                                    Color.Gray,
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                        ) {
                            Text(
                                "Retry",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(transactions.reversed()) { tran ->
                        PurchasedTickets(tran)
                    }
                }
            }
        }
    }
}

//card for tickets
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PurchasedTickets(transaction: TransactionDto) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .padding(bottom = 15.dp)
            .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = Color(
                    ContextCompat.getColor(
                        LocalContext.current,
                        R.color.snackBarColor
                    )
                )
            )
    ) {
        Text(
            text = "RAVE2B",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 20.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, top = 30.dp, bottom = 30.dp, end = 20.dp)
        ) {
            Text(
                "SET-I: ${transaction.djOne}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
            Text(
                "SET-II: ${transaction.djTwo}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
            Text(
                "SET-III: ${transaction.djThree}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
            Text(
                "SET-IV: ${transaction.djFour}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
            Text(
                "Price: ${transaction.price}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
            Text(
                "Date: ${transaction.eventDateSave}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
        }

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