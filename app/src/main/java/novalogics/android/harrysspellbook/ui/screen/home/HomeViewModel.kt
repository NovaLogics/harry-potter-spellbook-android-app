package novalogics.android.harrysspellbook.ui.screen.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import novalogics.android.harrysspellbook.data.repository.HomeRepositoryOffline
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryOffline: HomeRepositoryOffline
) : ViewModel() {

}
