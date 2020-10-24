package dragor.international.repository

import androidx.lifecycle.LiveData
import dragor.international.api.model.Example
import dragor.international.api.network.ApiResponses
import dragor.international.api.network.ApiService


class Repository (
    private val apiService: ApiService
) {
    fun getApiData(): LiveData<ApiResponses<List<Example>>> {
        return apiService.exampleList()
    }
}