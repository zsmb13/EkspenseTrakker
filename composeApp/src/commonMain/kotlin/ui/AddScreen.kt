import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ekspensetrakker.composeapp.generated.resources.Res
import ekspensetrakker.composeapp.generated.resources.add_expense
import ekspensetrakker.composeapp.generated.resources.amount
import ekspensetrakker.composeapp.generated.resources.description
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AddScreen(
    onRecordCreated: () -> Unit,
    onBack: () -> Unit,
    viewModel: AddViewModel = koinViewModel(),
) {
    LaunchedEffect(viewModel) {
        viewModel.initialize()
    }

    val recordCreated by viewModel.recordCreated.collectAsState()
    LaunchedEffect(recordCreated) {
        if (recordCreated) onRecordCreated()
    }

    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val people by viewModel.people.collectAsState()
    val personId by viewModel.personId.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(vertical = 20.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
        Spacer(Modifier.height(100.dp))
        val roundedShape = remember { RoundedCornerShape(12.dp) }
        LazyRow {
            items(people) { person ->
                val borderColor by animateColorAsState(
                    when (person.id) {
                        personId -> MaterialTheme.colors.primary
                        else -> Color.Transparent
                    },
                    animationSpec = tween(200),
                )
                PersonItem(
                    person = person,
                    modifier = Modifier
                        .clip(roundedShape)
                        .border(2.dp, borderColor, roundedShape)
                        .clickable { viewModel.selectPerson(person.id) },
                )
            }
        }

        TextField(
            value = amount,
            onValueChange = { amount = it.filter(Char::isDigit) },
            label = { Text(stringResource(Res.string.amount)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(vertical = 24.dp).widthIn(max = 200.dp),
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(stringResource(Res.string.description)) },
        )


        Button(
            onClick = { viewModel.createRecord(amount.toInt(), description) },
            enabled = personId != null && amount.toIntOrNull() != null,
        ) {
            Text(stringResource(Res.string.add_expense))
        }
    }
}
