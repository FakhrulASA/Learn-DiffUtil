## Learn DiffUtil
### DiffUtil is a simple differ library to compare lists in a List. Thus optimize list updating and comparison.

### Why DiffUtil?
As updating and sync a big list is always a memory expensive task. When we work with big list of thousands of data, it will be very bad practice to update the whole list for few items. So, we come to use diffutl which will specifically get the new items and only update them in the existed list without chaning others. As a result lots of work will be saved and the task will be faster. 

### Follow the following steps for integrate diffutil in your recyclerview adapter.

#### 1. First create diffUtilCalback for comparing two list. This callback will be used to compare and get the uncommon item from the new list.
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

#### 2. Now you need to initialize the diffutil.
```kotlin
    private var differ: AsyncListDiffer<DocumentItem> =
        AsyncListDiffer(this, diffUtilsCallBack)
```

#### 3. Now to submit the new/old list, just call submit list from diffutil.
```kotlin
differ.submitList(itemList)
```
#### 4. To add new item list and update the existed list create a function according to the need,
```kotlin
    fun addDataSet(dataList: MutableList<DocumentItem>) {
        val previousList =
            ArrayList<DocumentItem>(differ.currentList.toMutableList())
        previousList.addAll(dataList)
        differ.submitList(dataList)
    }
```
Here we are saving our previous list(if there) by getting the current available list in diffutil. Else it will return nothing. If it gets previous list, it will do following things.
1. If contents are the same it will not change the item.
2. If they are not same or changed it will update the specefit item but not the list
3. If there are new items, it will add the new item to the old item list.
4. If items are same it will use the previous list and will not update anything.
#### 5. To clear list just put null in submit list.
```kotlin
    fun clearDataSet() {
        this.differ.submitList(null)
        this.notifyDataSetChanged()
    }
```
