package com.gana.ebenezer.caleb.di

import com.gana.ebenezer.caleb.MainViewModel
import com.gana.ebenezer.caleb.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel(get()) }

}

val repositoryModule = module {
    single { Repository(get()) }
}
