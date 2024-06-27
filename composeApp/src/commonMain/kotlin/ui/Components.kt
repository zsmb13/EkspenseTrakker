package ui

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
import data.Expense
import data.Person
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PersonItemPreview() {
    PersonItem(Person("random-id-for-b", "Blake"))
}

@Composable
fun PersonItem(person: Person, modifier: Modifier = Modifier) {

}

@Composable
fun Avatar(person: Person, modifier: Modifier = Modifier) {
    val color = remember(person) { Color(120, person.name.hashCode() % 255, 100) }

    Box(modifier.size(48.dp).clip(CircleShape).background(color))
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

}
