package com.rshea.jetpackcomposecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rshea.jetpackcomposecodelab.ui.theme.JetpackComposeCodeLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCodeLabTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Preview
//@Preview(
//    showBackground = true,
//    heightDp = 320,
//    widthDp = 320,
//    uiMode = UI_MODE_NIGHT_YES,
//    name = "Dark"
//)
@Composable
fun MyAppPreview() {
    JetpackComposeCodeLabTheme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier
) {
    var shouldShowBoarding by rememberSaveable { mutableStateOf(true) }

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        if (shouldShowBoarding) {
            BoardingScreen(onContinueClicked = {
                shouldShowBoarding = false
            })
        } else {
            GreetingsScreen()
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun BoardingPreview() {
    JetpackComposeCodeLabTheme {
        BoardingScreen(onContinueClicked = {})
    }
}

@Composable
fun BoardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to JetPack Compose!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingsPreview() {
    JetpackComposeCodeLabTheme {
        GreetingsScreen()
    }
}

@Composable
fun GreetingsScreen(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items (items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
fun CardContent(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false)}

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ){
        Column (
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ){
            Text(text = "Hello, ")
            Text(text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = if (expanded) {
                    "show_less"
                } else {
                    "show_more"
                }
            )
        }
    }
}

//@Composable
//fun Greeting(name: String) {
//    var expanded by rememberSaveable { mutableStateOf(false)}
//    val extraPadding by animateDpAsState(
//        if (expanded) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        )
//    )
//    Surface(
//        color = MaterialTheme.colorScheme.primary,
//        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
//        ) {
//        Column {
//            Row(modifier = Modifier.padding(24.dp)) {
//                Column(modifier = Modifier
//                    .weight(1f)
//                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
//                ) {
//                    Text(text = "Hello,")//, style = MaterialTheme.typography.headlineMedium)
//                    Text(text = name, style = MaterialTheme.typography.headlineMedium.copy(
//                            fontWeight = FontWeight.ExtraBold
//                    ))
//                }
//                ElevatedButton(
//                    onClick = { expanded = !expanded }
//                ) {
//                    Text(if (expanded) "Show less" else "Show more")
//                }
//            }
//        }
//    }
//}


//                Column(
//                    Modifier.weight(1f),
//                    verticalArrangement = Arrangement.SpaceEvenly,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Button(
//                        onClick = {
//                            //Toast.makeText(context = LocalContext, "Clicked on Button", Toast.LENGTH_LONG).show()
//                        },
//                        shape = CircleShape,
//                        colors = ButtonDefaults.textButtonColors(
//                            backgroundColor = Color.White
//                        )
//                    ){
//                        Text("Show more")
//                    }
//                }
