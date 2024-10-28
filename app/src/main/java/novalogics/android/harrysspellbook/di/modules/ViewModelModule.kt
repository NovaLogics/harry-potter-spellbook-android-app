package novalogics.android.harrysspellbook.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import novalogics.android.harrysspellbook.di.components.ViewModelKey
import novalogics.android.harrysspellbook.ui.screen.spellcircle.SpellCircleViewModel
import novalogics.android.harrysspellbook.ui.screen.spellbook.SpellBookViewModel

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SpellCircleViewModel::class)
    abstract fun bindSpellCircleViewModel(viewModel: SpellCircleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SpellBookViewModel::class)
    abstract fun bindSpellBookViewModel(viewModel: SpellBookViewModel): ViewModel


}
