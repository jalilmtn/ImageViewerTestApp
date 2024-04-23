package com.example.imageviewertestapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.imageviewertestapp.R
import com.example.imageviewertestapp.domain.Cat
import com.example.imageviewertestapp.ui.components.CircleIconButton

@Composable
fun ImageViewerDialog(cat: Cat?, fave: (Cat) -> Unit, onDismissRequest: () -> Unit, isSaved :Boolean) {

    Dialog(
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true,
            decorFitsSystemWindows = false,
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            cat?.let {
                Box {

                    AsyncImage(
                        model = cat.url, contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Column(Modifier.padding(top = 24.dp, start = 16.dp)) {
                        Text(
                            text = cat.id,
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White
                        )
                        Text(
                            text = stringResource(R.string.size, cat.width, cat.height),
                            style = MaterialTheme.typography.labelSmall, color = Color.White
                        )
                    }
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(24.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        CircleIconButton(
                            onclick = { /*TODO*/ },
                            iconId = R.drawable.chat_ic,
                            iconSize = 48.dp
                        )
                        CircleIconButton(
                            onclick = { /*TODO*/ },
                            iconId = R.drawable.eat_ic,
                            iconSize = 48.dp
                        )
                        CircleIconButton(
                            onclick = {
                                fave.invoke(cat)
                            },
                            iconId = R.drawable.hurt_ic,
                            iconTint = if (isSaved) Color.Red else Color.Unspecified,
                            iconSize = 48.dp
                        )
                    }
                }
            }
        }
    }
}