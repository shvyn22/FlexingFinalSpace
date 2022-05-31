package shvyn22.flexingfinalspace.presentation.character

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import shvyn22.flexingfinalspace.data.local.model.CharacterModel
import shvyn22.flexingfinalspace.repository.Repository
import shvyn22.flexingfinalspace.util.toLiveData
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    repository: Repository<CharacterModel>
) : ViewModel() {

    val items = repository
        .getItems()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .toLiveData()
}