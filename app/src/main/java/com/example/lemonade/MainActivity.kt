package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier) {innerPadding ->
                LemonadeApp(modifier = Modifier.padding(innerPadding))
            }

        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    LemonadeWithButtonAndImage(
        modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonadeWithButtonAndImage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var step by remember {
            mutableIntStateOf(1)
        }

        var tapsRequired by remember {
            mutableIntStateOf(0)
        }

        val imageResource = when (step) {
            1 -> R.drawable.lemon_tree
            2 -> R.drawable.lemon_squeeze
            3 -> R.drawable.lemon_drink
            else -> R.drawable.lemon_restart
        }

        val imageDescription = when (step) {
            1 -> R.string.lemon_tree_description
            2 -> R.string.lemon_description
            3 -> R.string.glass_of_lemonade_description
            else -> R.string.empty_glass_description
        }

        val instruction = when (step) {
            1 -> R.string.lemon_tree
            2 -> R.string.lemon
            3 -> R.string.glass_of_lemonade
            else -> R.string.empty_glass
        }

        Button(
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffc3ecd2)
            ),
            onClick = {
                when (step) {
                    1 -> {
                        step = 2
                        tapsRequired = (2..4).random()
                    }
                    2 -> {
                        tapsRequired--
                        if (tapsRequired == 0) {
                            step = 3
                        }
                    }
                    3 -> {
                        step = 4
                    }
                    else -> {
                        step = 1
                    }
                }
            }
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = stringResource(id = imageDescription)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(text = stringResource(id = instruction))
    }
}