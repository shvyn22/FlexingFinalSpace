package shvyn22.flexingfinalspace.ui.episode

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.repository.Repository
import shvyn22.flexingfinalspace.util.toLiveData
import javax.inject.Inject

class EpisodeViewModel @Inject constructor(
    repository: Repository<EpisodeModel>
) : ViewModel() {

    val items = repository
        .getItems()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .toLiveData()
}