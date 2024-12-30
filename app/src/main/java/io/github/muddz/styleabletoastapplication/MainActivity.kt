package io.github.muddz.styleabletoastapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
//import io.github.muddz.styleabletoast.StyleableToast
import io.github.muddz.styleabletoastapplication.ui.theme.StyleableToastApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StyleableToastApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val mContext: Context = LocalContext.current

    Text(
        text = "Hello $name!",
        modifier = modifier.clickable {
//            StyleableToast.makeText(mContext,
//                "this is a test message", R.style.AllStyles).show()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StyleableToastApplicationTheme {
        Greeting("Android")
    }
}