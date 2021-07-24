package com.example.mybills.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( )
interface ApplicationComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent

    }




}