package com.example.transpapptest.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.transpapptest.R
import com.example.transpapptest.ui.events.SignInEvent
import com.example.transpapptest.ui.navigation.Screen
import com.example.transpapptest.ui.viewmodels.SignInViewModel
import com.example.transpapptest.ui.components.NormalTextComponent
import com.example.transpapptest.ui.components.ButtonComponent
import com.example.transpapptest.ui.components.ClickableSignInTextComponent
import com.example.transpapptest.ui.components.DividerTextComponent
import com.example.transpapptest.ui.components.HeadingTextComponent
import com.example.transpapptest.ui.components.MyTextFieldComponent
import com.example.transpapptest.ui.components.PasswordTextFieldComponent
import com.example.transpapptest.ui.components.UnderLinedTextComponent

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = hiltViewModel(key = "SignInScreen"),
    navController: NavHostController
) {
    Log.d("SignInScreen", "signInViewModel = $signInViewModel")

    SignInForm(
        signInViewModel = signInViewModel,
        navController = navController,
    )
}

@Composable
fun SignInForm(
    signInViewModel: SignInViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            NormalTextComponent(value = stringResource(id = R.string.sign_in))
            HeadingTextComponent(value = stringResource(id = R.string.welcome))
            Spacer(modifier = Modifier.height(40.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.username),
                painterResource = painterResource(id = R.drawable.profile),
                onTextSelected = { signInViewModel.onEvent(SignInEvent.UsernameChanged(it)) },
                errorStatus = signInViewModel.state.usernameError
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.ic_lock),
                onTextSelected = { signInViewModel.onEvent(SignInEvent.PasswordChanged(it)) },
                errorStatus = signInViewModel.state.passwordError
            )

            Spacer(modifier = Modifier.height(40.dp))

            UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(
                value = stringResource(id = R.string.sign_in),
                isEnabled = signInViewModel.allValidationsPassed
            ) {
                Log.d("SignInScreen", "Sign In BTN CLICK!")
                Log.d("SignInScreen", "signInViewModel = $signInViewModel")
                signInViewModel.onEvent(SignInEvent.LoginButtonClicked(context))
            }

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableSignInTextComponent(
                tryingToSignIn = false
            ) {
                navController.navigate(Screen.SignUpScreen.route)
            }
        }
    }
}

@Composable
fun SignInScreenLoading() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
