package dragor.international.api.network

import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class ApiResponses<T> {

    var data: T? =null
    var status: String? = null

    constructor(response: Response<T>) {
        if (response.isSuccessful) {
            data = response.body()
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().toString()
                } catch (ignored: IOException) {
                    Timber.e(ignored, "error while parsing response")
                }

            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            status = message
            data = null
        }

    }

    constructor(error: Throwable) {
        data = null
        status = error.message.toString()

    }

}
