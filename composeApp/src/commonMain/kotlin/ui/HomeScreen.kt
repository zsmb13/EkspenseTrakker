package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Column(Modifier.fillMaxSize().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(Modifier.fillMaxWidth().weight(1f)) {

        }
        Spacer(
            Modifier.padding(5.dp).height(2.dp).fillMaxWidth().background(MaterialTheme.colors.primary)
        )
        Text("Total: ?", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        Button(onClick = { }) {
            Text("Add?")
        }
    }
}
