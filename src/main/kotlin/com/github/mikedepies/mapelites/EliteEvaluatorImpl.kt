package com.github.mikedepies.mapelites

class EliteEvaluatorImpl(val programBehaviorEvaluator: ProgramBehaviorEvaluator<ModelProgram>) :
    EliteEvaluator<ModelProgram> {
    override fun evaluate(program: ModelProgram): Solution {
        val solution = programBehaviorEvaluator.evaluate(program)
        return solution
    }
}