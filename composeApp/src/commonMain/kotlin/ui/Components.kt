import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ekspensetrakker.composeapp.generated.resources.Res
import ekspensetrakker.composeapp.generated.resources.paid_by
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PersonItemPreview() {
    PersonItem(Person("random-id-for-b", "Blake"))
}

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
    val color = remember(person) { Color(120, person.name.hashCode() % 255, 100) }

    Box(modifier.size(48.dp).clip(CircleShape).background(color)) {
        peopleToAvatars[person]?.let {
            Image(
                bitmap = imageResource(it),
                contentDescription = person.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview
@Composable
fun ExpenseItemPreview() {
    ExpenseItem(
        expense = Expense("", "", 20, "Real business expense"),
        paidBy = Person("random-id-for-b", "Blake"),
    )
}

@Composable
fun ExpenseItem(expense: Expense, paidBy: Person) {
    Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Avatar(paidBy, Modifier.size(40.dp))
        Column(Modifier.padding(horizontal = 16.dp).weight(1f)) {
            Text(expense.description, fontSize = 20.sp)
            Text(stringResource(Res.string.paid_by, paidBy.name))
        }
        Text("$${expense.amount}")
    }
}
