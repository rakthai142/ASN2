package bec.c231371.u8_apis._services.dependencyinjection

import android.content.Context
import androidx.room.Room
import bec.c231371.u8_apis._services.database.AppDatabase
import bec.c231371.u8_apis.courses._models.ProductDao
import bec.c231371.u8_apis.courses._models.ProductWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://asia-southeast2-s2-asn2-point-of-sale.cloudfunctions.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideProductService(retrofit: Retrofit): ProductWebService =
        retrofit.create(ProductWebService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app-database").build()

    @Singleton
    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao =
        db.ProductDao()

}