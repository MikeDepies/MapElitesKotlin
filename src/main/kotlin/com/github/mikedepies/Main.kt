package com.github.mikedepies

import com.github.mikedepies.mapelites.*
import com.github.mikedepies.mapelites.ProgramEvaluatorImpl

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
    val mapElites = MapElitesAlgorithmImpl(programMap,eliteMapOperator, eliteMutator, eliteEvaluator, MapElitesConfiguration((.5)))
    mapElites.initialize(100)
    mapElites.evolve(1000)
}

//Program & Solution
//The program represents the underlying model
//The solution represents the behavior of the model, and it's fitness score
//The behavior is a list of doubles
//The program is evaluated to determine behavior
//The behavior is used to determine the location of the model in the feature space

//So we need a representation of the program, and ways to augment the program through mutations and crossovers
//We also need a way to evaluate the program to determine its behavior
//

