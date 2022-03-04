package com.vickikbt.devtyme.android.ui.screens.login_screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.vickikbt.devtyme.android.R
import com.vickikbt.devtyme.android.ui.navigation.NavigationItem
import com.vickikbt.devtyme.android.utils.findActivity
import com.vickikbt.devtyme.domain.utils.Constants
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.getViewModel
import java.util.*

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = getViewModel()) {

    viewModel.getUserToken()

    val context = LocalContext.current

    val userAccessToken by remember { mutableStateOf(viewModel.accessToken.value) }
    val isLoading by remember { mutableStateOf(viewModel.isLoading.value) }

    Napier.e("Access token: $$userAccessToken")

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (imageLogo, buttonLogin) = createRefs()

        /*Image(
            modifier = Modifier
                .constrainAs(imageLogo) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(288.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(id = R.string.app_name)
        )*/

        Button(
            modifier = Modifier
                .constrainAs(buttonLogin) {
                    width = Dimension.fillToConstraints
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 32.dp, end = 32.dp, bottom = 32.dp),
            onClick = {
                if (userAccessToken?.accessToken.isNullOrEmpty()) wakatimeOAuth(context = context)
                else navController.navigate(NavigationItem.Home.route)
            },
            contentPadding = PaddingValues(vertical = 8.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.onSurface,
                contentColor = MaterialTheme.colors.background
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Text(
                    modifier = Modifier.align(Alignment.Bottom),
                    text = stringResource(R.string.title_login).uppercase(Locale.getDefault()),
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    DisposableEffect(key1 = viewModel) {
        onResume(context = context, viewModel = viewModel)
        onDispose { /*ToDo*/ }
    }
}

private fun wakatimeOAuth(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.WEB_URL))
    context.startActivity(intent)
}

fun onResume(context: Context, viewModel: LoginViewModel) {
    val uri = context.findActivity()?.intent?.data

    if (uri != null && uri.toString().contains(Constants.REDIRECT_URL)) {
        val code = uri.getQueryParameter("code")

        if (code != null) {
            Napier.e("Code: $code")
            viewModel.fetchUserToken(code = code)
        } else uri.getQueryParameter("error")?.let {
            Napier.e("Error: $it")
        }
    } else {
        Napier.e("Nothing was returned")
    }
}

@Preview
@Composable
fun Preview() {
}
