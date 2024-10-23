package novalogics.android.harrysspellbook.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import novalogics.android.harrysspellbook.data.repository.HomeRepositoryOffline
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRepository(): HomeRepositoryOffline {
        return HomeRepositoryOffline()
    }
}
