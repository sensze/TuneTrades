package com.ifkusyoba.tunetrades.di

import com.ifkusyoba.tunetrades.data.Repository

object Injection {
    fun provideRepository(): Repository{
        return Repository.getInstance()
    }
}