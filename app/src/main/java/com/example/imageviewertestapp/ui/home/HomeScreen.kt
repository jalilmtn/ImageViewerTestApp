package com.example.imageviewertestapp.ui.home


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import com.example.imageviewertestapp.R
import com.example.imageviewertestapp.domain.Cat
import com.example.imageviewertestapp.ui.components.CatItem
import com.example.imageviewertestapp.ui.components.CircleIconButton

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    cats: LazyPagingItems<Cat>,
    dialogState: DialogState,
    fave: (Cat) -> Unit,
    showImage: (Cat) -> Unit,
    hideDialog: () -> Unit,
    navigateToFav: () -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = cats.loadState, block = {
        if (cats.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (cats.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    })

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0),
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = stringResource(R.string.spotlight),
                    style = MaterialTheme.typography.titleMedium
                )
            }, actions = {
                CircleIconButton(
                    onclick = navigateToFav,
                    iconId = R.drawable.ic_save,
                    iconSize = 40.dp,
                    iconPadding = 6.dp
                )
            })
        }
    ) {
        if (cats.loadState.refresh is LoadState.Loading)
            CircularProgressIndicator(Modifier.size(24.dp))
        else {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                columns = GridCells.Fixed(3)
            ) {
                items(
                    count = cats.itemCount,
                    contentType = cats.itemContentType { "contentType" }
                ) { index ->
                    val item = cats[index]
                    if (item != null)
                        CatItem(item) {
                            showImage.invoke(item)
                        }
                }
                item {
                    if (cats.loadState.append == LoadState.Loading)
                        CircularProgressIndicator(Modifier.size(24.dp))
                }
            }
        }
    }

    if (dialogState.showDialog)
        ImageViewerDialog(
            cat = dialogState.cat,
            fave = fave,
            isSaved = dialogState.isSaved == true,
            onDismissRequest = hideDialog
        )
}
