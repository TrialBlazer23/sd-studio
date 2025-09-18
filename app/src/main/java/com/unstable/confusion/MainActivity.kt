package com.unstable.confusion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { App() }
  }
}

@Composable
fun App() {
  MaterialTheme {
    Surface(Modifier.fillMaxSize()) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text("SD Studio — v0.1.0", style = MaterialTheme.typography.headlineSmall)
        Text("Runtime: Genie/QNN (to be wired)")
        Text("Models: Not imported yet")

        Button(onClick = { /* TODO: navigate to Generate screen */ }) {
          Text("Generate (coming soon)")
        }
      }
    }
  }
}
