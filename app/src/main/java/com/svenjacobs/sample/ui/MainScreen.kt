package com.svenjacobs.sample.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainScreen(
    viewModel: MainHiltViewModel,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    var pressed by remember { mutableIntStateOf(0) }
    val updatedViewModel by rememberUpdatedState(viewModel)
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = {
            viewModel.someCallback()
//            updatedViewModel.someCallback()
            true
        }
    )

    LaunchedEffect(sheetState.targetValue) {
        Log.d("XXX", "targetState: ${sheetState.targetValue}" )
    }

    if (sheetState.isVisible || sheetState.targetValue == SheetValue.Expanded) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { scope.launch { sheetState.hide() } }
        ) {
            Text("Sheet content")
        }
    }

    Scaffold(
        modifier = modifier,
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding),
            contentAlignment = Alignment.Center,
        ) {
            Button(
                onClick = {
                    pressed += 1
                    scope.launch { sheetState.show() }
                }
            ) {
                Text("Click me: $pressed")
            }
        }
    }
}