package ai.sunnystratgies.mapelites

interface ProgramBehaviorEvaluator<T> {
    fun evaluate(program: T): Solution
}