package dragor.international.ui.test.adapter
import dragor.international.api.model.Example


interface ExampleListClickItem {
    abstract fun onListItemClick(doctorentity: Example?)

}