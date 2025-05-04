package com.example.rave2b.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppSignUpScreen()
{

    val scroll = rememberScrollState()
    val userName = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val gmail = remember { mutableStateOf("") }
    val country = remember { mutableStateOf("") }
    val fName = remember { mutableStateOf("") }
    val lName = remember { mutableStateOf("") }
    val idNumber = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scroll),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            TextField(
                value = userName.value,
                onValueChange = { userName.value = it },
                modifier = Modifier
                    .width(350.dp)
                    .height(65.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                ),
                placeholder = {
                    Text(
                        "Enter UserName",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 19.sp
                    )
                },
                maxLines = 1,
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontSize = 19.sp
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                modifier = Modifier
                    .width(350.dp)
                    .height(65.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                ),
                placeholder = {
                    Text(
                        "Enter Password",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 19.sp
                    )
                },
                maxLines = 1,
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontSize = 19.sp
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = gmail.value,
                onValueChange = { gmail.value = it },
                modifier = Modifier
                    .width(350.dp)
                    .height(65.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                ),
                placeholder = {
                    Text(
                        "Enter Gmail",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 19.sp
                    )
                },
                maxLines = 1,
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontSize = 19.sp
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = country.value,
                onValueChange = { country.value = it },
                modifier = Modifier
                    .width(350.dp)
                    .height(65.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                ),
                placeholder = {
                    Text(
                        "Enter Country",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 19.sp
                    )
                },
                maxLines = 1,
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontSize = 19.sp
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = fName.value,
                onValueChange = { fName.value = it },
                modifier = Modifier
                    .width(350.dp)
                    .height(65.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                ),
                placeholder = {
                    Text(
                        "Enter First Name",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 19.sp
                    )
                },
                maxLines = 1,
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontSize = 19.sp
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = lName.value,
                onValueChange = { lName.value = it },
                modifier = Modifier
                    .width(350.dp)
                    .height(65.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                ),
                placeholder = {
                    Text(
                        "Enter Last Name",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 19.sp
                    )
                },
                maxLines = 1,
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontSize = 19.sp
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = idNumber.value,
                onValueChange = { idNumber.value = it },
                modifier = Modifier
                    .width(350.dp)
                    .height(65.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                ),
                placeholder = {
                    Text(
                        "Enter Id Number",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 19.sp
                    )
                },
                maxLines = 1,
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontSize = 19.sp
                ),
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = {

            },
                modifier = Modifier
                .height(75.dp)
                .width(350.dp)
                .padding(start = 230.dp, bottom = 20.dp)
                .border(
                    2.dp,
                    Color.Gray,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            )
            {
                Text(text = "Sign Up", color = Color.Gray, style = MaterialTheme.typography.titleMedium,fontSize = 19.sp)
            }


        }

    }
    
}