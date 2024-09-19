package com.company.tictactoebootcamp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.company.tictactoebootcamp.ScoreItems
import com.company.tictactoebootcamp.data.ScoreSerializer
import com.company.tictactoebootcamp.data.repository.ScoreItemsImpl
import com.company.tictactoebootcamp.data.repository.ScoreItemsRepository
import com.company.tictactoebootcamp.utils.ApplicationBuildConfigFieldsProvider
import com.company.tictactoebootcamp.utils.BuildConfigFieldsProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val DATA_STORE_FILE_NAME = "scoreItemsList.pb"

    private val Context.scoreItemsStore: DataStore<ScoreItems> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = ScoreSerializer,
    )

    @Provides
    @Singleton
    fun provideProtoDataStore(@ApplicationContext context: Context) =
        context.scoreItemsStore

    @Provides
    @Singleton
    internal fun providesDataRepository(
        scoreItemsDataStore: DataStore<ScoreItems>
    ): ScoreItemsRepository {
        return ScoreItemsImpl(
            scoreItemsDataStore
        )
    }

    @Provides
    @Singleton
    internal fun provideBuildConfigFieldsProvider(): BuildConfigFieldsProvider {
        return ApplicationBuildConfigFieldsProvider()
    }
}