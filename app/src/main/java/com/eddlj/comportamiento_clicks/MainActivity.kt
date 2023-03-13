package com.eddlj.comportamiento_clicks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eddlj.comportamiento_clicks.ui.theme.Comportamiento_ClicksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Comportamiento_ClicksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PasosParaBeberLimonada()
                }
            }
        }
    }
}

@Composable
fun PasosParaBeberLimonada() {
    var paso by remember { mutableStateOf(1) }
    var clicksExprimir by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (paso) {
            1 -> {
                Limonada(
                    textLabelResourceId = R.string.tap_lemon_tree,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.lemon_tree,
                    onImageClick = {
                        paso = 2
                        clicksExprimir = (2..4).random()
                    }
                )
            }
            2 -> {
                Limonada(
                    textLabelResourceId = R.string.tap_lemon_tree,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.lemon,
                    onImageClick = {
                        clicksExprimir--
                        if (clicksExprimir == 0) {
                            paso = 3
                        }
                    }
                )
            }
            3 -> {
                Limonada(
                    textLabelResourceId = R.string.tap_drink_lemonade,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.glass_of_lemonade,
                    onImageClick = {
                        paso = 4
                    }
                )
            }
            4 -> {
                Limonada(
                    textLabelResourceId = R.string.tap_start_again,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.empty_glass,
                    onImageClick = {
                        paso = 1
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Comportamiento_ClicksTheme {
        PasosParaBeberLimonada()
    }
}

@Composable
fun Limonada(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(textLabelResourceId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(1.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        )
    }
}