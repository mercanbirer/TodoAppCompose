package com.todo.todoappcompose.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.todo.todoappcompose.R
import com.todo.todoappcompose.model.TextList

@Composable
fun MainScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current

        val isChecked = remember { mutableStateOf(false) }
        val isTaskList = remember { mutableStateOf(false) }
        val textValueTitle = remember { mutableStateOf("") }
        val textValueDesc = remember { mutableStateOf("") }
        var taskList = arrayListOf<TextList>()


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
                if (textValueTitle.value.isEmpty() && textValueDesc.value.isEmpty()) {
                    Toast.makeText(context, "Enter the Data", Toast.LENGTH_LONG).show()
                } else {
                    isTaskList.value= true
                    taskList.add(TextList(
                        textValueTitle = textValueTitle.value,
                        textValueDesc = textValueDesc.value
                    ))

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
        if (isTaskList.value){
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
                        text = textValueTitle.value,
                        modifier = Modifier
                            .padding(top = 10.dp),
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        text = textValueDesc.value,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
        }
    }
}