package ai.sunnystratgies.mapelites

interface EliteMutator<T> {
    fun generateRandomSolution(): T
    fun mutate(solution: T): T
    fun crossover(parent1: T, parent2: T): T
}

interface EliteEvaluator<T> {
    fun evaluate(program: T): Solution
}

interface EliteMapOperator {
    fun placeInMap(solution: Solution)
    fun getCell(cellIndices: List<Int>): Solution?
    fun selectParents(): Pair<Solution, Solution>
}

interface MapElitesAlgorithmInterface {
    fun initialize(populationSize: Int)
    fun evolve(iterations: Int)
}

class ProgramMap<Model> {
    private val solutions = mutableMapOf<String, Model>()
    fun addProgram(id: String, model: Model) {
        solutions[id] = model
    }

    fun getProgram(id: String): Model? {
        return solutions[id]
    }

    fun removeProgram(id: String) {
        solutions.remove(id)
    }

}

class MapElitesAlgorithmImpl<T>(
    private val eliteProgramMap: ProgramMap<T>,
    private val eliteMapOperator: EliteMapOperator,
    private val eliteMutator: EliteMutator<T>,
    private val eliteEvaluator: EliteEvaluator<T>,
    private val configuration: MapElitesConfiguration
) : MapElitesAlgorithmInterface {

    override fun initialize(populationSize: Int) {
        // Generate initial population and place into the map
        repeat(populationSize) {
            val program = eliteMutator.generateRandomSolution()
            val solution = eliteEvaluator.evaluate(program)
            eliteMapOperator.placeInMap(solution)
        }
    }

    override fun evolve(iterations: Int) {
        repeat(iterations) {
            // Selection
            val (p1, p2) = eliteMapOperator.selectParents()
            val parents = Pair<T, T>(
                eliteProgramMap.getProgram(p1.id) ?: throw Exception("Failed to find solution for ${p1.id}"),
                eliteProgramMap.getProgram(p2.id) ?: throw Exception("Failed to find solution for ${p2.id}")
            )
            // Variation
            // Crossover or mutation
            val shouldCrossover = kotlin.random.Random.nextDouble() < configuration.crossoverChance
            val offspring = if (shouldCrossover) {
                eliteMutator.crossover(parents.first, parents.second)
            } else {
                eliteMutator.mutate(parents.first)
            }
            
            val offspringSolution  = eliteEvaluator.evaluate(offspring)
            // Update map
            eliteMapOperator.placeInMap(offspringSolution)
        }
    }
}

data class MapElitesConfiguration(val crossoverChance: Double)
