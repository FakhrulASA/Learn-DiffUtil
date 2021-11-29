## Learn DiffUtil
### DiffUtil is a simple differ library to compare lists in a List. Thus optimize list updating and comparison.

### It is the diffutil callback for comparing the old and new list item and content. 
```kotlin
    private val diffUtilsCallBack =
        object : DiffUtil.ItemCallback<DocumentItem>() {
            override fun areItemsTheSame(
                oldItem: DocumentItem,
                newItem: DocumentItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DocumentItem,
                newItem: DocumentItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
```

### Here you are initializing the diffutil.
```kotlin
    private var differ: AsyncListDiffer<DocumentItem> =
        AsyncListDiffer(this, diffUtilsCallBack)
```

### To submit the new/old list, just call submit list from diffutil.
```kotlin
differ.submitList(itemList)
```
### Now to add and update list create function according to the need,
```kotlin
    fun addDataSet(dataList: MutableList<DocumentItem>) {
        val previousList =
            ArrayList<DocumentItem>(differ.currentList.toMutableList())
        previousList.addAll(dataList)
        logThis("${this::class.java} - Total new dataset ${dataList.size}")
        differ.submitList(dataList)
    }
```
### To clear list just put null in submit list.
```kotlin
    fun clearDataSet() {
        this.differ.submitList(null)
        this.notifyDataSetChanged()
    }
```
