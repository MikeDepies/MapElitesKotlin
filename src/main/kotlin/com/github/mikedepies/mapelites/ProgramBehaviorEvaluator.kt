package com.github.mikedepies.mapelites

interface ProgramBehaviorEvaluator<T> {
    fun evaluate(program: T): Solution
}