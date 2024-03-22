package ai.sunnystratgies

import ai.sunnystratgies.mapelites.*
import java.util.UUID
data class ModelProgram(val id: String, val programText : String)
interface ProgramBehaviorEvaluator<T> {
    fun evaluate(program: T): Solution
}
fun main() {
    // Define the feature descriptor, mutation, crossover, and evaluate functions
    val metaList = createEmbeddingFeatureMeta(-2.0, 2.0, 10, 16)
    val featureDescriptorTransformer = FeatureDescriptorTransformer(metaList)
    val featureDescriptor = featureDescriptorTransformer::featureDescriptor
    val programMap = ProgramMap<ModelProgram>()
    val eliteMutator = EliteMutatorImpl(programMap)
    val programEvaluator = ProgramEvaluatorImpl()
    val eliteEvaluator = EliteEvaluatorImpl(programEvaluator)
    val eliteMapOperator = EliteMapOperatorImpl(featureDescriptor, mutableMapOf())
    val mapElites = MapElitesAlgorithmImpl<ModelProgram>(programMap,eliteMapOperator, eliteMutator, eliteEvaluator, MapElitesConfiguration((.5)))
    mapElites.initialize(100)
    mapElites.evolve(1000)
}
class ProgramEvaluatorImpl : ProgramBehaviorEvaluator<ModelProgram> {
    override fun evaluate(program: ModelProgram): Solution {
        val programText = program.programText
        val programBehavior = programBehaviorEvaluator(programText)
        return Solution(program.id, programBehavior, 0.0)
    }

    fun programBehaviorEvaluator(programText: String): List<Double> {
        TODO(programText)
    }
}
class EliteMutatorImpl(val programMap: ProgramMap<ModelProgram>) : EliteMutator<ModelProgram> {
    override fun mutate(solution: ModelProgram): ModelProgram {
        val programText = solution.programText
        val programTextMutated = programText
        val id = UUID.randomUUID().toString()
        val modelProgram = ModelProgram(id, programTextMutated)
        programMap.addProgram(id, modelProgram)
        return modelProgram
    }

    override fun crossover(parent1: ModelProgram, parent2: ModelProgram): ModelProgram {
        val programText1 = parent1.programText
        val programText2 = parent2.programText
        val programTextMutated = programText1 + programText2
        val id = UUID.randomUUID().toString()
        val modelProgram = ModelProgram(id, programTextMutated)
        programMap.addProgram(id, modelProgram)
        return modelProgram
    }

    override fun generateRandomSolution(): ModelProgram {
        val id = UUID.randomUUID().toString()
        val programText = "def main():\n  print('hello world')"
        val modelProgram = ModelProgram(id, programText)
        programMap.addProgram(id, modelProgram)
        return modelProgram
    }
}
class EliteEvaluatorImpl(val programBehaviorEvaluator: ProgramBehaviorEvaluator<ModelProgram>) : EliteEvaluator<ModelProgram> {
    override fun evaluate(program: ModelProgram): Solution {
        val solution = programBehaviorEvaluator.evaluate(program)
        return solution
    }
}


//Program & Solution
//The program represents the underlying model
//The solution represents the behavior of the model and it's fitness score
//The behavior is a list of doubles
//The program is evaluated to determine behavior
//The behavior is used to determine the location of the model in the feature space

//So we need a representation of the program, and ways to augement the program through mutations and crossovers
//We also need a way to evaluate the program to determine its behavior
//

