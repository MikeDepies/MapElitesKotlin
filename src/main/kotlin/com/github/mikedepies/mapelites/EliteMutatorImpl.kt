package com.github.mikedepies.mapelites

import java.util.*

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