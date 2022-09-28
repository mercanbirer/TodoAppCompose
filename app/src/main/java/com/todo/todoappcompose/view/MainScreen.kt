package com.todo.todoappcompose.view

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.todo.todoappcompose.R
import com.todo.todoappcompose.database.DeviceApps
import com.todo.todoappcompose.repository.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current

        val isChecked = remember { mutableStateOf(false) }
        val isTaskList = remember { mutableStateOf(false) }
        val isClick = remember { mutableStateOf(false) }

        val textValueTitle = remember { mutableStateOf(TextFieldValue("")) }
        val textValueDesc = remember { mutableStateOf(TextFieldValue("")) }
        var taskList = remember { mutableListOf<DeviceApps>() }
        val index = remember { mutableStateOf(0) }
        val deleteList = remember { mutableListOf<DeviceApps>() }
        val coroutineScope = rememberCoroutineScope()
        val lifecycleOwner = LocalLifecycleOwner.current
        val user = viewModel.user

        LaunchedEffect(Unit, block = {
            viewModel.getList()

            user.observe(lifecycleOwner){
                if (it.isNotEmpty()){
                    isTaskList.value = true
                    isClick.value = true
                    taskList = it.toMutableList()
                }
            }

        })

        Box(modifier = Modifier.background(color = Color.Gray)) {
            Text(
                text = stringResource(R.string.todo),
                fontSize = 26.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color.White
            )
        }

        OutlinedTextField(
            value = textValueTitle.value,
            onValueChange = {
                textValueTitle.value = it
            },
            label = {
                Text(text = stringResource(R.string.new_title))
            },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.custom_red),
                unfocusedBorderColor = colorResource(id = R.color.custom_grey),
                focusedLabelColor = colorResource(id = R.color.custom_grey),
                unfocusedLabelColor = colorResource(id = R.color.custom_grey),
                cursorColor = colorResource(id = R.color.custom_red),
                errorBorderColor = colorResource(id = R.color.custom_red),
                errorLabelColor = colorResource(id = R.color.custom_red),
            ),
        )


        OutlinedTextField(
            value = textValueDesc.value,
            onValueChange = {
                textValueDesc.value = it
            },
            label = {
                Text(text = stringResource(R.string.new_desc))
            },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.custom_red),
                unfocusedBorderColor = colorResource(id = R.color.custom_grey),
                focusedLabelColor = colorResource(id = R.color.custom_grey),
                unfocusedLabelColor = colorResource(id = R.color.custom_grey),
                cursorColor = colorResource(id = R.color.custom_red),
                errorBorderColor = colorResource(id = R.color.custom_red),
                errorLabelColor = colorResource(id = R.color.custom_red),
            ),
        )

        Button(
            onClick = {
                if (textValueTitle.value.text.isEmpty() && textValueDesc.value.text.isEmpty()) {
                    Toast.makeText(context, "Enter the Data", Toast.LENGTH_LONG).show()
                } else {
                    isTaskList.value = true
                    taskList.add(
                        DeviceApps(
                            title = textValueTitle.value.text,
                            desc = textValueDesc.value.text
                        )
                    )
                    index.value = taskList.size
                    coroutineScope.launch {
                        for (i in  taskList.indices){
                            viewModel.insertApp(taskList[i])
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 64.dp, end = 64.dp, top = 16.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = colorResource(id = R.color.custom_red)
            )
        ) {
            Text(stringResource(R.string.add_task))
        }
        if (isTaskList.value) {
            Text(
                text = stringResource(R.string.active),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 6.dp)
                    .alpha(0.8f),
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
            )

            Divider(
                color = colorResource(id = R.color.custom_grey),
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.4f)
            )

            user.observe(lifecycleOwner){
                if (it.isNotEmpty()){
                    isTaskList.value = true
                    taskList = it.toMutableList()
                }
            }

            if (index.value == taskList.size) {
                LazyColumn {
                    itemsIndexed(
                        items = taskList,
                        itemContent = { _, item ->
                            AnimatedVisibility(
                                visible = !deleteList.contains(item),
                                enter = expandVertically(),
                                exit = shrinkVertically(animationSpec = tween(durationMillis = 500))
                            ) {
                                Box {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Checkbox(
                                            checked = isChecked.value,
                                            onCheckedChange = {
                                                isChecked.value = it
                                            },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = colorResource(id = R.color.custom_red),
                                                checkmarkColor = Color.White
                                            ),
                                            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                                        )
                                        IconButton(
                                            onClick = {
                                                deleteList.add(item)
                                                index.value = deleteList.size
                                            },
                                            modifier = Modifier.padding(end = 16.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_icons8_remove_50),
                                                contentDescription = ""
                                            )
                                        }
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 50.dp)
                                    ) {
                                        Text(
                                            text = item.title.toString(),
                                            modifier = Modifier
                                                .padding(top = 10.dp),
                                            fontSize = 14.sp,
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.Bold,
                                        )

                                        Text(
                                            text = item.desc.toString(),
                                            fontSize = 12.sp,
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.Normal,
                                        )
                                    }
                                }

                            }

                        })
                }
            } else if (index.value == deleteList.size) {
                LazyColumn {
                    itemsIndexed(
                        items = taskList,
                        itemContent = { _, it ->
                            AnimatedVisibility(
                                visible = !deleteList.contains(it),
                                enter = expandVertically(),
                                exit = shrinkVertically(animationSpec = tween(durationMillis = 500))
                            ) {
                                Box {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Checkbox(
                                            checked = isChecked.value,
                                            onCheckedChange = {
                                                isChecked.value = it
                                            },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = colorResource(id = R.color.custom_red),
                                                checkmarkColor = Color.White
                                            ),
                                            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                                        )
                                        IconButton(
                                            onClick = {
                                                deleteList.add(it)
                                                index.value = deleteList.size
                                            },
                                            modifier = Modifier.padding(end = 16.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_icons8_remove_50),
                                                contentDescription = ""
                                            )
                                        }
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 50.dp)
                                    ) {
                                        Text(
                                            text = it.title.toString(),
                                            modifier = Modifier
                                                .padding(top = 10.dp),
                                            fontSize = 14.sp,
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.Bold,
                                        )

                                        Text(
                                            text = it.desc.toString(),
                                            fontSize = 12.sp,
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.Normal,
                                        )
                                    }
                                }

                            }

                        })
                }

            }
        }
    }
}

