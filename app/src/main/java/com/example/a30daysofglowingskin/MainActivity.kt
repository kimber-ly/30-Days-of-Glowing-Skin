package com.example.a30daysofglowingskin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysofglowingskin.ui.theme._30DaysOfGlowingSkinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30DaysOfGlowingSkinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SkincareApp()
                }
            }
        }
    }
}
@Composable
fun SkincareTipsCard(
    skincareTips: SkincareTips,
    modifier: Modifier = Modifier
){
    var expand by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(targetValue = if (expand) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.tertiaryContainer)
    val textColor by animateColorAsState(targetValue = if (expand) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.onTertiaryContainer)
    Card (
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.large)
            .padding(12.dp)
        ,
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row (
                modifier = Modifier
            ) {
                Column (
                    modifier = Modifier
                        .weight(3f)
                ) {
                    Text(
                        text = "Day ${skincareTips.day}",
                        color = textColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = skincareTips.tips),
                        style = MaterialTheme.typography.titleLarge,
                        color = textColor,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { expand = !expand }) {
                    Icon(
                        painter = if (expand) painterResource(id = R.drawable.baseline_expand_less_24)
                        else painterResource(id = R.drawable.baseline_expand_more_24),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                        ,
                        tint = textColor
                    )
                }
            }

            if(expand){
                Spacer(modifier = Modifier.height(16.dp))
                Box (
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ){
                    Image(
                        painter = painterResource(id = skincareTips.image),
                        contentDescription = stringResource(id = skincareTips.tips),
                        modifier = Modifier
                            .clip(shape = MaterialTheme.shapes.medium)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = skincareTips.description),
                    style = MaterialTheme.typography.bodyLarge,
                    color = textColor
                )
            }
        }
    }
}
@Composable
fun SkincareApp(){
    SkincareTipsList(skincareTipsList = DataSource.data)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkincareTipsList(
    modifier: Modifier = Modifier,
    skincareTipsList: List<SkincareTips>
){
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            )
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding
            ){
                items(skincareTipsList){list ->
                    SkincareTipsCard(skincareTips = list)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SkincareTipsCardPreview() {
    _30DaysOfGlowingSkinTheme {
        SkincareTipsCard(DataSource.data[0])
    }
}

@Preview(showBackground = true)
@Composable
fun SkincareTipsCardDarkPreview() {
    _30DaysOfGlowingSkinTheme (
        darkTheme = true
    ) {
        SkincareTipsCard(DataSource.data[0])
    }
}

@Preview(showBackground = true)
@Composable
fun SkincareAppPreview() {
    _30DaysOfGlowingSkinTheme {
        SkincareApp()
    }
}

@Preview(showBackground = true)
@Composable
fun SkincareDarkAppPreview() {
    _30DaysOfGlowingSkinTheme (
        darkTheme = true
    ) {
        SkincareApp()
    }
}