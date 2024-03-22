package ai.sunnystratgies.mapelites

interface EliteMutator {
    fun generateRandomSolution(): Solution
    fun mutate(solution: Solution): Solution
    fun crossover(parent1: Solution, parent2: Solution): Solution
}

interface EliteEvaluator {
    fun evaluate(solution: Solution): Double
}

interface EliteMapOperator {
    fun placeInMap(solution: Solution)
    fun getCell(cellIndices: List<Int>): Solution?
    
    fun selectParents(): Pair<Solution, Solution>
}
class MapElitesAlgorithm(
    private val eliteMapOperator: EliteMapOperator,
    private val eliteMutator: EliteMutator,
    private val eliteEvaluator: EliteEvaluator,
    private val configuration: MapElitesConfiguration
) {

    fun initialize(populationSize: Int) {
        // Generate initial population and place into the map
        repeat(populationSize) {
            val solution = eliteMutator.generateRandomSolution()
            solution.performance = eliteEvaluator.evaluate(solution)
            eliteMapOperator.placeInMap(solution)
        }
    }

    fun evolve(iterations: Int) {
        repeat(iterations) {
            // Selection
            val parents = eliteMapOperator.selectParents()
            // Variation
            // Crossover or mutation
            val shouldCrossover = kotlin.random.Random.nextDouble() < configuration.crossoverChance
            val offspring = if (shouldCrossover) {
                eliteMutator.crossover(parents.first, parents.second)
            } else {
                eliteMutator.mutate(parents.first)
            }
            offspring.performance = eliteEvaluator.evaluate(offspring)
            // Update map
            eliteMapOperator.placeInMap(offspring)
        }
    }
}

data class MapElitesConfiguration(
    val crossoverChance: Double,
    
)
