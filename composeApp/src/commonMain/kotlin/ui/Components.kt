import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.imageResource

@Composable
fun PersonItem(person: Person, modifier: Modifier = Modifier) {
    Column(modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Avatar(person)
        Spacer(Modifier.size(8.dp))
        Text(person.name)
    }
}

@Composable
fun Avatar(person: Person, modifier: Modifier = Modifier) {
    val color = remember(person) { Color(120, 50, person.name.hashCode()) }
    
    Box(modifier.size(48.dp).clip(CircleShape).background(color)) {
        peopleToAvatars[person]?.let {
            Image(imageResource(it), null, Modifier.fillMaxSize())
        }
    }
}

@Composable
fun ExpenseItem(expense: ExpenseWithPerson) {
    Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.ShoppingCart, null)
        Spacer(Modifier.size(16.dp))
        Column(Modifier.weight(1f)) {
            Text("KotlinConf catering", fontSize = 20.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("paid by")
                Avatar(expense.paidBy, Modifier.padding(horizontal = 4.dp).size(20.dp))
                Text(expense.paidBy.name)
            }
        }
        Text("$${expense.expense.amount}")
    }
}
