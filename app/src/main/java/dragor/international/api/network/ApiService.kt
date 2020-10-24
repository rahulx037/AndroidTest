package dragor.international.api.network

import androidx.lifecycle.LiveData
import dragor.international.api.model.Example
import retrofit2.http.GET

interface ApiService {

    @GET("blogs?page=1&limit=10")
    fun exampleList(): LiveData<ApiResponses<List<Example>>>
}