package ai.sunnystratgies.mapelites

class FeatureDescriptorTransformer(val featureMeta : List<FeatureMeta>) {
    fun featureDescriptor(solution: Solution): List<Int> {
        return solution.features.zip(featureMeta).map { (feature, meta) ->
            val relativePosition = (feature - meta.min) / (meta.max - meta.min)

            // Determine the discrete index based on the number of cells
            // The min(.., meta.numCells - 1) is used to ensure the index is within bounds
            // in case the feature value is exactly equal to meta.max
            val index = (relativePosition * meta.numCells).toInt().coerceIn(0, meta.numCells - 1)
            index
        }
    }
}
