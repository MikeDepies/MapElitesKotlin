package ai.sunnystratgies.mapelites

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