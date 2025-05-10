package com.example.rave2b.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.rave2b.R
import com.example.rave2b.data.RetrofitClient
import com.example.rave2b.data.TransactionDto
import com.example.rave2b.data.TransactionViewModel
import com.example.rave2b.networkpermission.isNetworkAvailable
import kotlinx.coroutines.launch
import kotlin.String

@Composable
fun BuyTicketScreen(
    djNameOne: String,
    djNameTwo: String,
    djNameThree: String,
    djNameFour: String,
    price: String,
    eventDate: String
)
{
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
    val username = sharedPref.getString("username", "")
    val corScope = rememberCoroutineScope()
    val mySnackBarHostState = remember { SnackbarHostState() }
    val scroll = rememberScrollState()
    val cardHolderName = remember { mutableStateOf("") }
    val cardHolderLastName = remember { mutableStateOf("") }
    val cardDigits = remember { mutableStateOf("") }
    val cardCvv = remember { mutableStateOf("") }
    val cardExpDate = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ){
        Column ( modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scroll)
            .padding(start = 20.dp, end = 20.dp, bottom = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            TextField(
                value = cardHolderName.value,
                onValueChange = { val filteredName = it.filter { char -> char.isLetter()}.uppercase()
                    cardHolderName.value = filteredName
                },
                modifier = Modifier
                    .width(350.dp)
                    .height(65.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                ),
                placeholder = { Text("Card Holder Name", color = Color.Gray, style = MaterialTheme.typography.titleMedium,fontSize = 19.sp) },
                maxLines = 1,
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontSize = 19.sp
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                value = cardHolderLastName.value,
                onValueChange = {
                    val filteredLastName = it.filter { char -> char.isLetter()}.uppercase()
                    cardHolderLastName.value = filteredLastName
                },
                modifier = Modifier
                    .width(350.dp)
                    .height(65.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                ),
                placeholder = { Text("Card Holder Last Name", color = Color.Gray, style = MaterialTheme.typography.titleMedium,fontSize = 19.sp) },
                maxLines = 1,
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontSize = 19.sp
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                value = cardDigits.value,
                onValueChange = {
                    val filteredDigits = it.filter { char -> char.isDigit() }
                    cardDigits.value = filteredDigits
                },
                modifier = Modifier
                    .width(350.dp)
                    .height(65.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                ),
                placeholder = { Text("Card 16 digit number",color = Color.Gray,style = MaterialTheme.typography.titleMedium,fontSize = 19.sp) },
                maxLines = 1,
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontSize = 19.sp
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row (modifier = Modifier,horizontalArrangement = Arrangement.spacedBy(10.dp))
            {
                TextField(
                    value = cardCvv.value,
                    onValueChange = {
                        val filteredDigits = it.filter { char -> char.isDigit() }
                        cardCvv.value = filteredDigits
                    },
                    modifier = Modifier
                        .width(160.dp)
                        .height(65.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.White,
                    ),
                    placeholder = { Text("Cvv/Cvc", color = Color.Gray,style = MaterialTheme.typography.titleMedium,fontSize = 19.sp) },
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontSize = 19.sp
                    )
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    value = cardExpDate.value,
                    onValueChange = {
                        val filteredDigits = it.filter { char -> char.isDigit() }
                        cardExpDate.value = filteredDigits
                    },
                    modifier = Modifier
                        .width(160.dp)
                        .height(65.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.White,
                    ),
                    placeholder = { Text("Exp.Year", color = Color.Gray,style = MaterialTheme.typography.titleMedium,fontSize = 19.sp) },
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontSize = 19.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            Button(
                onClick = {
                    //create transaction
                    val transaction = TransactionDto(
                        userNm = username.toString(),
                        djOne = djNameOne,
                        djTwo = djNameTwo,
                        djThree = djNameThree,
                        djFour = djNameFour,
                        price = price,
                        eventDateSave = eventDate,
                        cardHolderName = cardHolderName.value,
                        cardHolderLastName = cardHolderLastName.value,
                        cardNumber = cardDigits.value,
                        cardCvv = cardCvv.value,
                        cardExpireDate = cardExpDate.value
                    )

                    //no internet con handle
                    if(!isNetworkAvailable(context))
                    {
                        corScope.launch {
                            mySnackBarHostState.showSnackbar("No internet connection")
                        }
                        return@Button
                    }

                    //empty input handle
                    if(cardHolderName.value.isEmpty() || cardHolderLastName.value.isEmpty() || cardDigits.value.isEmpty() || cardCvv.value.isEmpty() || cardExpDate.value.isEmpty())
                    {
                        corScope.launch {
                            mySnackBarHostState.showSnackbar("Please fill all fields")
                        }
                        return@Button
                    }

                    //card holder name and lastname max len
                    if(cardHolderName.value.length > 25 || cardHolderLastName.value.length > 25) {
                        corScope.launch {
                            mySnackBarHostState.showSnackbar("You are out of card holder name or lastname length.")
                        }
                        return@Button
                    }

                    //card holder name and lastname min len
                    if(cardHolderName.value.length < 2 || cardHolderLastName.value.length < 2)
                    {
                        corScope.launch {
                            mySnackBarHostState.showSnackbar("Name or lastname minimum length is 2.")
                        }
                        return@Button
                    }

                    //card digits len
                    if(cardDigits.value.length != 16)
                    {
                        corScope.launch {
                            mySnackBarHostState.showSnackbar("Card digits length is incorrect.")
                        }
                        return@Button
                    }

                    //exp year handle
                    //handle exp year
                    if(cardExpDate.value.length != 4 || cardExpDate.value.toInt() !in 2025..2035)
                    {
                        corScope.launch {
                            mySnackBarHostState.showSnackbar("Please enter a valid year,in range 2025-2035")
                        }
                        return@Button
                    }

                    //cvv or cvc handle
                    if(cardCvv.value.length != 3)
                    {
                        corScope.launch {
                            mySnackBarHostState.showSnackbar("Cvv or Cvc length is incorrect.")
                        }
                        return@Button
                    }

                    corScope.launch {
                        try {
                            val response = RetrofitClient.apiService.addTransaction(transaction)
                            if(response.isSuccessful)
                            {
                                corScope.launch {
                                    mySnackBarHostState.showSnackbar("Successful transaction.")
                                }

                                cardHolderName.value = ""
                                cardHolderLastName.value = ""
                                cardDigits.value = ""
                                cardCvv.value = ""
                                cardExpDate.value = ""
                            }

                        }catch (e: Exception){
                            Log.d("myLog","Coroutine error:${e.localizedMessage}")
                        }
                    }
                },
                modifier = Modifier
                    .width(150.dp)
                    .height(60.dp)
                    .border(
                        2.dp,
                        Color.Gray,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            )
            {
                Text(text = "Confirm", color = Color.Gray, style = MaterialTheme.typography.titleMedium,fontSize = 19.sp)
            }
        }

        SnackbarHost(
            hostState = mySnackBarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 105.dp),
            snackbar = { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = Color(ContextCompat.getColor(LocalContext.current,R.color.snackBarColor)),
                    contentColor = Color.White
                )
            }
        )
    }
}