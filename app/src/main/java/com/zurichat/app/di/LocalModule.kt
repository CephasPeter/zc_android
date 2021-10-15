package com.zurichat.app.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.zurichat.app.data.localSource.AppDatabase
import com.zurichat.app.data.localSource.dao.OrganizationMembersDao
import com.zurichat.app.data.localSource.dao.UserDao
import com.zurichat.app.util.SHARED_PREF_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "zuri_chat").fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideOrganizationMembersDao(database: AppDatabase): OrganizationMembersDao {
        return database.organizationMembersDao()
    }
}
