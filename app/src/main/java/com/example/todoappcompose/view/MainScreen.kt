package com.example.todoappcompose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoappcompose.R

@Composable
fun MainScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        val textValueTitle = remember {
            mutableStateOf("")
        }

        val textValueDesc = remember {
            mutableStateOf("")
        }

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
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(start = 64.dp,end = 64.dp,top = 16.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = colorResource(id = R.color.custom_red)
            )
        ) {
            Text(stringResource(R.string.add_task))
        }
    }
}