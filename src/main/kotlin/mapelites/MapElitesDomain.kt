package ai.sunnystratgies.mapelites

data class Solution(val id: String, val features: List<Double>, var performance: Double)
data class FeatureMeta(val min : Double, val max : Double, val numCells : Int)
fun createEmbeddingFeatureMeta(min : Double, max : Double, numCells: Int, numberOfFeatures : Int): List<FeatureMeta> {
    return (0 until numberOfFeatures).map { FeatureMeta(min, max, numCells) }
}