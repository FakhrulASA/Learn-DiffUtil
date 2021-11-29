    private val diffUtilsCallBack =
        object : DiffUtil.ItemCallback<TrainingDocument>() {
            override fun areItemsTheSame(
                oldItem: TrainingDocument,
                newItem: TrainingDocument
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TrainingDocument,
                newItem: TrainingDocument
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    private var differ: AsyncListDiffer<TrainingDocument> =
        AsyncListDiffer(this, diffUtilsCallBack)