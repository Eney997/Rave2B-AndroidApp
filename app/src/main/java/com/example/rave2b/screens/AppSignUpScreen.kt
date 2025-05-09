package com.example.rave2b.screens

import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.rave2b.R
import com.example.rave2b.data.RegistrationDto
import com.example.rave2b.data.RetrofitClient
import com.example.rave2b.networkpermission.isNetworkAvailable
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSignUpScreen()
{
    val context = LocalContext.current
    val locations = listOf("Georgia", "Germany", "France", "Poland", "Spain")
    var expanded by remember { mutableStateOf(false) }
    val scroll = rememberScrollState()
    val corScope = rememberCoroutineScope()
    val mySnackBarHostState = remember { SnackbarHostState() }
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
                onValueChange = {
                    val filteredUsername = it.filter { char -> char.isLetter()}.uppercase()
                    userName.value = filteredUsername
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

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier
                    .width(350.dp)
                    .height(65.dp)
            ) {
                TextField(
                    value = country.value,
                    onValueChange = {},
                    readOnly = true,
                    placeholder = {
                        Text("Select country", color = Color.Gray, style = MaterialTheme.typography.titleMedium,fontSize = 19.sp)
                    },
                    textStyle = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontSize = 19.sp
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.White,
                    ),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown Arrow"
                        )
                    },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    locations.forEach { location ->
                        DropdownMenuItem(
                            text = { Text(location) },
                            onClick = {
                                country.value = location
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = fName.value,
                onValueChange = {
                    val filteredFName = it.filter { char -> char.isLetter()}.uppercase()
                    fName.value = filteredFName
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
                onValueChange = {
                    val filteredLName = it.filter { char -> char.isLetter() }.uppercase()
                    lName.value = filteredLName
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
                onValueChange = {
                    val filteredIdNumber = it.filter { num -> num.isDigit() }
                    idNumber.value = filteredIdNumber
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

                val user = RegistrationDto(
                    username =  userName.value,
                    password = password.value,
                    gmail = gmail.value,
                    country = country.value,
                    fName = fName.value,
                    lName = lName.value,
                    idNumber = idNumber.value
                )

                if(!isNetworkAvailable(context))
                {
                    corScope.launch {
                        mySnackBarHostState.showSnackbar("No Internet Connection")
                    }
                    return@Button
                }

                //empty input handle
                if(userName.value.isEmpty() || password.value.isEmpty() || gmail.value.isEmpty() || country.value.isEmpty() || fName.value.isEmpty() || lName.value.isEmpty() || idNumber.value.isEmpty())
                {
                    corScope.launch {
                        mySnackBarHostState.showSnackbar("Empty input!")
                    }
                    return@Button
                }

                //username max len
                if(userName.value.length > 25)
                {
                    corScope.launch {
                        mySnackBarHostState.showSnackbar("You are out of username length.")
                    }
                    return@Button
                }

                //userName min length
                if (userName.value.length < 7) {
                    corScope.launch {
                        mySnackBarHostState.showSnackbar("Username minimum length is 7.")
                    }
                    return@Button
                }

                //password max len
                if(password.value.length > 25)
                {
                    corScope.launch {
                        mySnackBarHostState.showSnackbar("You are out of password length.")
                    }
                    return@Button
                }

                //userName min length
                if (password.value.length < 7)
                {
                    corScope.launch {
                        mySnackBarHostState.showSnackbar("Password minimum length is 7.")
                    }
                    return@Button
                }

                //gmail handle
                val gmailRegex = Regex("^[a-zA-Z0-9._%+-]+@gmail\\.com$")
                if (!gmailRegex.matches(gmail.value))
                {
                    corScope.launch {
                        mySnackBarHostState.showSnackbar("Gmail ends only with @gmail.com")
                    }
                    return@Button
                }

                //gmail len
                if (gmail.value.length > 40)
                {
                    corScope.launch {
                        mySnackBarHostState.showSnackbar("Try another gmail.")
                    }
                    return@Button
                }

                //first name min handle
                if(fName.value.length < 2)
                {
                    corScope.launch {
                        mySnackBarHostState.showSnackbar("Name should be more than 2 character.")
                    }
                    return@Button
                }

                //first name max handle
                if(fName.value.length > 20)
                {
                    corScope.launch {
                        mySnackBarHostState.showSnackbar("Are you sure that's name?")
                    }
                    return@Button
                }

                //lastname min handle
                if(lName.value.length < 2)
                {
                    corScope.launch {
                        mySnackBarHostState.showSnackbar("Last name should be more than 2 character.")
                    }
                    return@Button
                }

                //lastname max handle
                if(lName.value.length > 20)
                {
                    corScope.launch {
                        mySnackBarHostState.showSnackbar("Are you sure that's last name?")
                    }
                    return@Button
                }

                //id number len handle
                if(idNumber.value.length != 11)
                {
                    corScope.launch {
                        mySnackBarHostState.showSnackbar("Id number length is 11 character.")
                    }
                    return@Button
                }

                //corScope for account creation
                corScope.launch {
                   try {
                       val response = RetrofitClient.apiService.createUser(user)

                       if(response.isSuccessful){
                           corScope.launch {
                               mySnackBarHostState.showSnackbar("Account created successfully.")
                            }
                               //clear inputs
                               userName.value = ""
                               password.value = ""
                               gmail.value = ""
                               country.value = ""
                               fName.value = ""
                               lName.value = ""
                               idNumber.value = ""
                       }else if (response.code() == 409)
                       {
                           corScope.launch {
                               mySnackBarHostState.showSnackbar("Username or Id number already taken")
                           }
                           return@launch
                       }
                       else {
                           Log.e("myLog", "Server error: ${response.code()}")
                       }
                   }
                   catch (e: Exception)
                   {
                        Log.d("myLog","Coroutine error:${e.localizedMessage}")
                   }
                }
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
        //snackBar
        SnackbarHost(
            hostState = mySnackBarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
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