package com.example.rave2b.popups

import android.content.Context
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import com.example.rave2b.R
import com.example.rave2b.data.RetrofitClient.apiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChangePassword
(
    onDismiss: () -> Unit
)
{
    val txt = remember { mutableStateOf("") }
    val context = LocalContext.current
    val alertColor = Color(ContextCompat.getColor(context, R.color.snackBarColor))
    val errorMessage = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val sharedPref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)

    Box(modifier = Modifier.fillMaxSize())
    {
        Dialog(onDismissRequest = onDismiss)
        {
            Column(
                modifier = Modifier
                    .height(240.dp)
                    .background(alertColor, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Privacy Policy",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text  = when {
                        errorMessage.value.isEmpty() -> "Please enter new password!"
                        else -> errorMessage.value
                    },
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = txt.value,
                    onValueChange = { txt.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.White,
                    ),
                    placeholder = {
                        Text(text = "Type here", color = Color.White, fontSize = 19.sp,fontWeight = FontWeight.Bold)
                    },
                    maxLines = 1,
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        val username = sharedPref.getString("username", "") ?: ""

                        //Empty text field input handle
                        if(txt.value.isEmpty())
                        {
                            errorMessage.value = "Empty input."
                            return@Button
                        }

                        //if username was not found
                        if (username.isEmpty()) {
                            Log.d("myLog", "Username not found in shared preferences.")
                            return@Button
                        }

                        //coroutine scope for update password in database
                        val newPassword = txt.value

                        coroutineScope.launch {
                            try {
                                val response = apiService.updatePas(username, newPassword)
                                if (response.isSuccessful) {
                                    errorMessage.value = "Password updated successfully."
                                    delay(1000)
                                    onDismiss()
                                } else {
                                    Log.d("myLog", "Failed to update password: ${response.code()}")
                                    errorMessage.value = "Update failed: ${response.code()}"
                                }
                            } catch (e: Exception) {
                                Log.d("myLog", "Error: ${e.message}")
                                errorMessage.value = "Exception: ${e.message}"
                            }
                        }
                    },
                    modifier = Modifier
                        .height(50.dp).width(120.dp)
                        .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = alertColor),
                ) {
                    Text(text = "Confirm", fontSize = 18.sp, color = Color.White)
                }
            }
        }
    }
    
}