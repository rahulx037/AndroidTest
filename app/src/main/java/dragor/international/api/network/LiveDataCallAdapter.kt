package dragor.international.api.network

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class LiveDataCallAdapter<R>(private val responseType: Type): CallAdapter<R, LiveData<ApiResponses<R>>> {
    override fun adapt(call: Call<R>): LiveData<ApiResponses<R>> {
        return object : LiveData<ApiResponses<R>>() {
            private var isSuccess = false

            override fun onActive() {
                super.onActive()
                if (!isSuccess) enqueue()
            }

            override fun onInactive() {
                super.onInactive()
                dequeue()
            }

            private fun dequeue() {
                if (call.isExecuted) call.cancel()
            }

            private fun enqueue() {
                call.enqueue(object : Callback<R> {
                    override fun onFailure(call: Call<R>, t: Throwable) {
                        postValue(ApiResponses(t))
                    }

                    override fun onResponse(call: Call<R>, response: Response<R>) {
                        postValue(ApiResponses(response))
                        isSuccess = true
                    }
                })
            }
        }
    }

    override fun responseType(): Type = responseType
}