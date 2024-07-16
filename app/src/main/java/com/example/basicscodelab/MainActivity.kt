package com.example.basicscodelab

import android.os.Bundle
import android.util.Log
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme


object myModifier {
    val myName = "Stefan"
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        calculate(onResult = {
            Log.e("Result", it.toString())
        })

        val test1 = myModifier
        val test2 = myModifier
        setContent {
            BasicsCodelabTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}


//what is this like some while loop? how it on click showing Greetings?
//is it because it is under Surface?
@Composable
fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnboarding = remember { mutableStateOf(true) }


    Surface(modifier) {
        if (shouldShowOnboarding.value) {
            OnboardingScreen(onContinueClicked = { name ->
                Log.e("StefanLog", name);
                shouldShowOnboarding.value = false
            })
        } else {
            Greetings()
        }
    }
}



fun calculate(onResult: (Int) -> Unit ) : String {
    var x = 5 + 6
    onResult(x)

    return onResult.toString();
}

fun returnString(s: String) : String {
    return s
}





@Composable
fun OnboardingScreen(
    onContinueClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var inputValue = remember { mutableStateOf("writeSomethigng") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = inputValue.value, onValueChange = { inputValue.value = it })
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = {
                val res = onContinueClicked(inputValue.value)
            }
        ) {
            Text("Continue")
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

//@Preview(showBackground = true, widthDp = 320, heightDp = 320)
//@Composable
//fun OnboardingPreview() {
//    BasicsCodelabTheme {
//        OnboardingScreen(onContinueClicked = {})
//    }
//}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    var expanded by rememberSaveable { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow

        )
    )
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello, ")
                Text(text = name, style = MaterialTheme.typography.headlineMedium)
            }
            ElevatedButton(
                onClick = { expanded = !expanded }
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

//@Preview(showBackground = true, widthDp = 320)
//@Composable
//fun GreetingPreview() {
//    BasicsCodelabTheme {
//        Greetings()
//    }
//}
//
//@Preview
//@Composable
//fun MyAppPreview() {
//    BasicsCodelabTheme {
//        MyApp()
//    }
//}