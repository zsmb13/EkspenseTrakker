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
    Box(modifier.size(48.dp).clip(CircleShape)) {
        peopleToAvatars[person]?.let {
            Image(imageResource(it), null, Modifier.fillMaxSize())
        }
    }
}

@Composable
fun ExpenseItem(expense: ExpenseWithPerson) {
    Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Avatar(expense.paidBy, Modifier.size(40.dp))
        Spacer(Modifier.size(16.dp))
        Column(Modifier.weight(1f)) {
            Text(expense.expense.description, fontSize = 20.sp)
            Text("paid by ${expense.paidBy.name}")
        }
        Text("$${expense.expense.amount}")
    }
}
