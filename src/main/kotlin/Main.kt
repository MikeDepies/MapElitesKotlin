package ai.sunnystratgies

import ai.sunnystratgies.mapelites.*
import java.util.UUID
data class ModelProgram(val id: String, val programText : String)
interface ProgramBehaviorEvaluator {
    fun evaluate(program: ModelProgram): Solution
}
fun main() {
    // Define the feature descriptor, mutation, crossover, and evaluate functions
    val metaList = createEmbeddingFeatureMeta(-2.0, 2.0, 10, 16)
    val featureDescriptorTransformer = FeatureDescriptorTransformer(metaList)
    val featureDescriptor = featureDescriptorTransformer::featureDescriptor
    val programMap = ProgramMap<ModelProgram>()
    // val programBehaviorEvaluator : (ModelProgram) -> List<Double> = { modelProgram ->
    //     val programText = modelProgram.programText
    //     TODO()
    // }
    val eliteMutator = object : EliteMutator<ModelProgram> {
        override fun mutate(solution: ModelProgram): ModelProgram {
            TODO("Not yet implemented")
        }

        override fun crossover(parent1: ModelProgram, parent2: ModelProgram): ModelProgram {
            TODO("Not yet implemented")
        }

        override fun generateRandomSolution(): ModelProgram {
            val id = UUID.randomUUID().toString()
            val programText = "def main():\n  print('hello world')"
            val modelProgram = ModelProgram(id, programText)
            programMap.addProgram(id, modelProgram)
            return modelProgram
        }
    }
    val programEvaluator = object : ProgramBehaviorEvaluator {
        override fun evaluate(program: ModelProgram): Solution {
            // val programText = program.programText
            TODO()
        }
    }
    val eliteEvaluator = object : EliteEvaluator<ModelProgram> {
        override fun evaluate(program: ModelProgram): Solution {
            return programEvaluator.evaluate(program)
        }
    }
    val eliteMapOperator = EliteMapOperatorImpl(featureDescriptor, mutableMapOf())
    val mapElites = MapElitesAlgorithmImpl<ModelProgram>(programMap,eliteMapOperator, eliteMutator, eliteEvaluator, MapElitesConfiguration((.5)))
    mapElites.initialize(100)
    mapElites.evolve(1000)
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

