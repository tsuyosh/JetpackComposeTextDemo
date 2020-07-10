package dev.tsuyosh.jetpackcomposetextdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import androidx.ui.res.vectorResource
import androidx.ui.text.*
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontStyle
import androidx.ui.text.font.FontSynthesis
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextDecoration
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.sp
import dev.tsuyosh.jetpackcomposetextdemo.ui.JetpackComposeTextDemoTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTextDemoTheme {
                Column {
                    StyledGreeting("Android")
                    StyledGreeting2("Android")
                    StyledGreeting3("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun StyledGreeting(name: String) {
    Text(
        text = "Hello $name!",
        color = Color.Green,
        fontSize = 30.sp,
        fontFamily = FontFamily.Cursive,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun StyledGreeting2(name: String) {
    val greetingText = annotatedString {
        pushStyle(
            SpanStyle(
                color = Color.Green
            )
        )
        append("Hello ")
        pushStyle(
            SpanStyle(
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSynthesis = FontSynthesis.All,
                textDecoration = TextDecoration.Underline
            )
        )
        append("$name!")
        pop() // 直近でpushしたstyleはpopできる
    }
    Text(
        text = greetingText,
        fontSize = 30.sp
    )
}

@Composable
fun StyledGreeting3(name: String) {
    val inlineContentId = "InlineContent"
    val greetingText = annotatedString {
        append("Hello $name!")
        // inline contentを挿入
        appendInlineContent(id = inlineContentId, alternateText = ":droid:")
    }
    val inlineContent = mapOf(
        inlineContentId to InlineTextContent(
            Placeholder(
                width = 30.sp,
                height = 30.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) { alternateText ->
            // このlambdaはComposable関数
            CustomEmojiImage(imageResId = R.drawable.ic_android_robot)
        }
    )
    Text(text = greetingText, fontSize = 30.sp, inlineContent = inlineContent)
}

@Composable
fun CustomEmojiImage(imageResId: Int) {
    Image(
        modifier = Modifier.fillMaxSize(),
        asset = vectorResource(id = imageResId),
        contentScale = ContentScale.FillWidth
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTextDemoTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun StyledGreetingPreview() {
    JetpackComposeTextDemoTheme {
        StyledGreeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun StyledGreeting2Preview() {
    JetpackComposeTextDemoTheme {
        StyledGreeting2("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun StyledGreeting3Preview() {
    JetpackComposeTextDemoTheme {
        StyledGreeting3("Android")
    }
}
