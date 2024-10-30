package novalogics.android.hexa.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import novalogics.android.hexa.di.components.ViewModelKey
import novalogics.android.hexa.ui.screen.central.CentralViewModel
import novalogics.android.hexa.ui.screen.charms.CharmsViewModel

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CentralViewModel::class)
    abstract fun bindSpellCircleViewModel(viewModel: CentralViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharmsViewModel::class)
    abstract fun bindSpellBookViewModel(viewModel: CharmsViewModel): ViewModel


}
