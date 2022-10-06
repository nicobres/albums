import com.example.leboncoin.data.local.AppDatabase
import com.example.leboncoin.data.remote.ApiAdapter
import com.example.leboncoin.data.repository.AlbumRepository
import com.example.leboncoin.data.repository.AlbumRepositoryImpl
import com.example.leboncoin.utils.NetworkHelper
import com.example.leboncoin.usecase.FetchAlbumUseCase
import com.example.leboncoin.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { FetchAlbumUseCase(get(), get()) }
    viewModel { MainViewModel(get()) }
    single { ApiAdapter() }
    single { NetworkHelper(get()) }
    single<AlbumRepository> { AlbumRepositoryImpl(AppDatabase.getAppDataBase(get()), get()) }
}