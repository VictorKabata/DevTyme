package com.vickikbt.devtyme.android.di

import com.vickikbt.devtyme.android.ui.screens.login_screen.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { LoginViewModel(authRepository = get()) }
}
