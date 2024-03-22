package mapelites

import ai.sunnystratgies.mapelites.FeatureDescriptorTransformer
import ai.sunnystratgies.mapelites.FeatureMeta
import ai.sunnystratgies.mapelites.Solution
import ai.sunnystratgies.mapelites.createEmbeddingFeatureMeta
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class FeatureDescriptorTransformerTest : BehaviorSpec({
    val id = "testid"
    given("a FeatureDescriptorTransformer") {
        // Common setup for multiple tests
        val min = 0.0
        val max = 10.0
        val numCells = 5
        val numberOfFeatures = 3 // You can adjust this based on your test cases
        val featureMeta = createEmbeddingFeatureMeta(min, max, numCells, numberOfFeatures)
        val transformer = FeatureDescriptorTransformer(featureMeta)
        
        `when`("feature values are within the min-max range") {
            val solution = Solution(id, listOf(2.5, 5.0, 7.5), 0.0)
            then("it should calculate correct indices") {
                val expectedIndices = listOf(1, 2, 3) // Expected indices based on your logic
                transformer.featureDescriptor(solution) shouldBe expectedIndices
            }
        }

        `when`("feature values are exactly at the min and max") {
            val solution = Solution(id, listOf(min, max), 0.0)
            then("it should include indices for min and max within bounds") {
                val expectedIndices = listOf(0, numCells - 1) // Adjust based on your logic for handling bounds
                transformer.featureDescriptor(solution) shouldBe expectedIndices
            }
        }

        `when`("feature values are outside the min-max range") {
            val solution = Solution(id, listOf(min - 1, max + 1), 0.0)
            then("it should clamp the indices to the closest bounds") {
                val expectedIndices = listOf(0, numCells - 1) // Indices clamped to 0 and numCells - 1
                transformer.featureDescriptor(solution) shouldBe expectedIndices
            }
        }

        `when`("there are no features") {
            val solution = Solution(id, emptyList(), 0.0)
            then("it should return an empty list of indices") {
                transformer.featureDescriptor(solution) shouldBe emptyList<Int>()
            }
        }
    }

    given("a FeatureDescriptorTransformer with varied feature metas") {
        // Example setup for tests with different min and max for each feature
        val featureMetas = listOf(
            FeatureMeta(0.0, 10.0, 5), // Feature 1
            FeatureMeta(-5.0, 5.0, 10), // Feature 2 with negative min
            FeatureMeta(10.0, 20.0, 2) // Feature 3
        )
        val transformer = FeatureDescriptorTransformer(featureMetas)
        `when`("features have the same values within range") {
            val solution = Solution(id, listOf(5.0, 0.0, 15.0), 0.0) // Same relative position in their respective ranges
            then("it should calculate indices correctly for uniform values") {
                val expectedIndices = listOf(2, 5, 1) // Expected indices for each feature
                transformer.featureDescriptor(solution) shouldBe expectedIndices
            }
        }

        `when`("feature values include negative numbers fitting the meta") {
            val solution = Solution(id, listOf(-5.0, -2.5, 12.0), 0.0)
            then("it should handle negative values correctly") {
                val expectedIndices = listOf(0, 2, 0) // Expected indices for each feature, including handling of negative values
                transformer.featureDescriptor(solution) shouldBe expectedIndices
            }
        }

        `when`("featureMeta have different min and max values") {
            val solution = Solution(id, listOf(10.0, 0.0, 20.0), 0.0) // Each feature is at its max
            then("it should correctly calculate indices based on each feature's meta") {
                val expectedIndices = listOf(4, 5, 1) // Indices for max value in each range
                transformer.featureDescriptor(solution) shouldBe expectedIndices
            }
        }
    }
})

