package com.fransbudikashira.suitmediatest.di

import android.content.Context
import androidx.room.Room
import com.fransbudikashira.suitmediatest.data.source.local.UserDatabase
import com.fransbudikashira.suitmediatest.util.Constants.USER_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            USER_DATABASE
        ).build()
    }
}