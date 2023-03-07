package com.multiplatforge.roomexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.multiplatforge.roomexample.data.AppDatabase
import com.multiplatforge.roomexample.data.User
import com.multiplatforge.roomexample.data.UserRepository
import com.multiplatforge.roomexample.ui.theme.RoomExampleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        val userDao = db.userDao()

        val userRepository = UserRepository(userDao)

        setContent {
            RoomExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scope = rememberCoroutineScope()
                    val users = userRepository.getAll().collectAsState(initial = emptyList())

                    Column {
                        users.value.forEach { user ->
                            Text(user.name)
                        }
                        Button(
                            onClick = {
                                scope.launch {
                                    userRepository.insert(User(name = "Alex"))
                                }
                            }
                        ) {
                            Text("Add user")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomExampleTheme {
        Greeting("Android")
    }
}