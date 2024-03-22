package ai.sunnystratgies

import ai.sunnystratgies.mapelites.*

fun main() {
    // Define the feature descriptor, mutation, crossover, and evaluate functions
    val metaList = createEmbeddingFeatureMeta(-2.0, 2.0, 10, 16)
    val featureDescriptorTransformer = FeatureDescriptorTransformer(metaList)
    val featureDescriptor = featureDescriptorTransformer::featureDescriptor
    
    val eliteMutator = object : EliteMutator {
        override fun mutate(solution: Solution): Solution {
            TODO("Not yet implemented")
        }

        override fun crossover(parent1: Solution, parent2: Solution): Solution {
            TODO("Not yet implemented")
        }

        override fun generateRandomSolution(): Solution {
            TODO("Not yet implemented")
        }
    }
    val eliteEvaluator = object : EliteEvaluator {
        override fun evaluate(solution: Solution): Double {
            TODO("Not yet implemented")
        }lll
    }
    val eliteMapOperator = EliteMapOperatorImpl(featureDescriptor, mutableMapOf())
    val mapElites = MapElitesAlgorithm(eliteMapOperator, eliteMutator, eliteEvaluator)
    mapElites.initialize(100)
    mapElites.evolve(1000)
}
class EliteMapOperatorImpl(val featureDescriptor: (Solution) -> List<Int>, val map: MutableMap<List<Int>, Solution>) : EliteMapOperator {
    override fun placeInMap(solution: Solution) {
        val cellIndices = featureDescriptor(solution)
        val cell = map[cellIndices]
        if (cell == null || solution.performance > cell.performance) {
            map[cellIndices] = solution
        }
    }
    override fun getCell(cellIndices: List<Int>): Solution? {
        return map[cellIndices]
    }


    override fun selectParents(): Pair<Solution, Solution> {
        //select two solutions from the map
        val cell = map.values.random()
        val cell2 = map.values.random()
        return Pair(cell, cell2)
    }
}
