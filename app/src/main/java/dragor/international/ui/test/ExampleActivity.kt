package dragor.international.ui.test

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dragor.international.R
import dragor.international.databinding.ActivityExampleBinding
import dragor.international.ui.base.BaseActivity
import dragor.international.ui.test.adapter.ExampleListClickItem
import dragor.international.ui.test.adapter.ExampleRecyclerAdapter
import dragor.international.api.model.Example
import org.koin.androidx.viewmodel.ext.android.viewModel


class ExampleActivity : BaseActivity<ActivityExampleBinding>(), ExampleListClickItem {

    private  val viewModel: ExampleViewModel by viewModel()

    private lateinit var adapter:ExampleRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView ()
        initObserver()
    }

    fun initView (){
        adapter= ExampleRecyclerAdapter(this)
        dataBinding.exampleRecycler.setLayoutManager(LinearLayoutManager(this))
        dataBinding.exampleRecycler.setHasFixedSize(true)
        dataBinding.exampleRecycler.addItemDecoration(
            DividerItemDecoration(
                application,
                DividerItemDecoration.VERTICAL
            )
        )
        dataBinding.exampleRecycler.setAdapter(adapter)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_example
    }

    fun initObserver(){
        viewModel.getApiData().observe(this, Observer {
            it.data?.let { it1 -> adapter.setData(it1) }
        })
    }

    override fun onListItemClick(doctorentity: Example?) {

    }
}