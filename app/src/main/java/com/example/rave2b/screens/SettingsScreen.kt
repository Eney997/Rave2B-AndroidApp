package com.example.rave2b.screens

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.example.rave2b.MainActivity
import com.example.rave2b.R
import com.example.rave2b.networkpermission.isNetworkAvailable
import com.example.rave2b.popups.ChangePassword
import com.example.rave2b.popups.DeleteUser

@Composable
fun SettingsScreen()
{
    val scrollIfNeeded = rememberScrollState()
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
    val username = sharedPref.getString("username", "")
    var isTextVisible by remember { mutableStateOf(false) }
    var showDialogDelete by remember { mutableStateOf(false) }
    var showDialogPutPass by remember { mutableStateOf(false) }

    //show dialog box for delete user
    if (showDialogDelete && username != null)
    {
        DeleteUser (onDismiss = {showDialogDelete = false})
    }

    //show dialog box for update password
    if (showDialogPutPass && username != null)
    {
        ChangePassword (onDismiss = {showDialogPutPass = false})
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black), contentAlignment = Alignment.TopStart)
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollIfNeeded)
            .padding(bottom = 100.dp))
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Icon(painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = "user icon",
                    tint = Color.White,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.fillMaxWidth())
                {
                    Text("$username", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                }
            }

            HorizontalDivider(modifier = Modifier
                .padding(top = 10.dp,start = 20.dp,end = 20.dp),
                thickness = 1.dp,
                color = Color.DarkGray
            )


            Spacer(modifier = Modifier.height(20.dp))

            Column (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp))
            {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(), verticalAlignment = Alignment.CenterVertically)
                {
                    Icon(painter = painterResource(id = R.drawable.ic_password), contentDescription = "Change password", tint = Color.White)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Change Password", color = Color.White, fontSize = 20.sp,fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = "Change password", tint = Color.White, modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable {
                            if(!isNetworkAvailable(context))
                            {
                                return@clickable
                            }
                            showDialogPutPass  =  true
                        })
                }

                HorizontalDivider(modifier = Modifier,thickness = 1.dp, color = Color.DarkGray)

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(), verticalAlignment = Alignment.CenterVertically)
                {
                    Icon(painter = painterResource(id = R.drawable.ic_delete), contentDescription = "Delete account", tint = Color.White)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Delete account", color = Color.White, fontSize = 20.sp,fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = "Delete account", tint = Color.White, modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable {
                            if(!isNetworkAvailable(context))
                            {
                                return@clickable
                            }
                            showDialogDelete = true
                        })
                }

                HorizontalDivider(modifier = Modifier,thickness = 1.dp, color = Color.DarkGray)

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(), verticalAlignment = Alignment.CenterVertically)
                {
                    Icon(painter = painterResource(id = R.drawable.ic_smartphone), contentDescription = "App Story", tint = Color.White)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "About App", color = Color.White, fontSize = 20.sp,fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = "About App", tint = Color.White, modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable {
                            isTextVisible = !isTextVisible
                        })
                }

                AnimatedVisibility(visible = isTextVisible)
                {
                    Text(
                        text = "Welcome to Rave2B — your ultimate gateway to the underground nightlife scene.\n" +
                                "\n" +
                                "This app was born from a deep love for music, freedom, and the thrill of exclusive late-night experiences. Whether you're a seasoned raver or new to the scene, Rave2B connects you with unforgettable events that pulse with energy.\n" +
                                "\n" +
                                "Built by an independent developer who lives for the beat, every part of this app was carefully crafted to make your clubbing journey smooth, exciting, and secure.\n" +
                                "\n" +
                                "Register, grab your ticket, and get your unique QR code to unlock the night. It’s fast, it’s easy, and it’s your pass to the underground.\n" +
                                "\n" +
                                "Thank you for joining Rave2B.\n",
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                    )
                }

                HorizontalDivider(modifier = Modifier,thickness = 1.dp, color = Color.DarkGray)

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(), verticalAlignment = Alignment.CenterVertically)
                {
                    Icon(painter = painterResource(id = R.drawable.ic_exit), contentDescription = "Log out", tint = Color.White)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Log out", color = Color.White, fontSize = 20.sp,fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = "Log out", tint = Color.White, modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable {
                            if(!isNetworkAvailable(context))
                            {
                                return@clickable
                            }
                            sharedPref.edit { clear() }
                            val i = Intent(context, MainActivity::class.java)
                            i.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            context.startActivity(i)
                        }
                    )
                }
            }
        }
    }
}