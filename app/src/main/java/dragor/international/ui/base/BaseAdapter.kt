package dragor.international.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<Type : RecyclerView.ViewHolder, Data> : RecyclerView.Adapter<Type>() {

    abstract fun setData(data:List<Data>)

}