package com.example.championsapp.injection

import com.example.championsapp.MainActivity
import com.example.championsapp.screen.bottom.BottomSheetFragment
import com.example.championsapp.screen.detail.ConstructorFragment
import com.example.championsapp.screen.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        RepositoryModule::class,
        NetworkConnectionModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(constructorFragment: ConstructorFragment)
    fun inject(bottomSheetFragment: BottomSheetFragment)
}