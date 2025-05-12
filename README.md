🎫 Rave2B – Techno Rave Ticketing App for Android

---
Rave2B is a sleek and dynamic Android application designed for techno rave enthusiasts. Built using Kotlin and Jetpack Compose, it lets users explore upcoming rave events, purchase tickets, and receive QR-coded confirmations—all in real time. The app integrates tightly with an ASP.NET Core Web API and MS SQL Server backend to deliver a seamless and secure ticketing experience.

---

✨ Features
🔐 Authentication
Register & Login with strong input validation

Enforced unique usernames

User info stored securely on the backend

🧠 Session Management
SharedPreferences used to manage login state

Auto-login for smooth user re-entry

📅 Event Browsing
Scrollable list of future and past events

Powered by ViewModel and LazyColumn

Smart filters disable ticket purchases for past events

🎟️ Ticket Purchasing
One ticket per event per user (validated by backend)

Real-time purchase updates using Retrofit

QR-coded confirmation email sent after each successful purchase

🎫 Bought Tickets Screen
Displays real-time data of purchased tickets

Data tied to the logged-in user

⚙️ Settings
Change password and delete account

View app info and log out securely

🌐 Requirements
Internet connection required for:

Viewing events

Purchasing tickets

Receiving email confirmations

🛠️ Tech Stack
Kotlin

Jetpack Compose

ViewModel + Coroutines

Retrofit (API Communication)

SharedPreferences (Login State)

ASP.NET Core Web API (Backend)

MS SQL Server (Database)

📱 UI/UX
Clean, dark-themed rave-inspired interface

Responsive layouts built with Jetpack Compose

Smooth navigation and real-time feedback
