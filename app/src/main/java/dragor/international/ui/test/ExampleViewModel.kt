package dragor.international.ui.test

import androidx.lifecycle.LiveData
import dragor.international.api.model.Example
import dragor.international.api.network.ApiResponses
import dragor.international.repository.Repository
import dragor.international.ui.base.BaseViewModel

class ExampleViewModel(private val repo: Repository) : BaseViewModel() {

    fun getApiData(): LiveData<ApiResponses<List<Example>>> {
        return repo.getApiData()
    }

}