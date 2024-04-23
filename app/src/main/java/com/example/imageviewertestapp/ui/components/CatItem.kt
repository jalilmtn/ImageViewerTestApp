package com.example.imageviewertestapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.imageviewertestapp.R
import com.example.imageviewertestapp.domain.Cat

@Composable
fun CatItem(cat: Cat, modifier: Modifier = Modifier, onclick: () -> Unit) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = cat.url, contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .clickable(onClick = onclick)
                .aspectRatio(.7f)
                .clip(RoundedCornerShape(CornerSize(8.dp)))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = cat.id, style = MaterialTheme.typography.labelMedium)
        Text(
            text = stringResource(R.string.size, cat.width, cat.height),
            style = MaterialTheme.typography.labelSmall
        )

    }

}