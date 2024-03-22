# MapElitesKotlin

MapElitesKotlin is a Kotlin-based implementation of the Map Elites algorithm, an evolutionary computation technique for generating diverse, high-quality solutions. It's designed for flexibility and efficiency in exploring various solution spaces.

## NOTICE
This is a work in progress and is not yet ready for use. Please do not use this library in production code until it is stable. 

## Getting Started

Ensure Kotlin is installed in your development environment. Clone this repository to include it in your project's dependencies. Alternatively, use jitpack for easy integration:

[![](https://jitpack.io/v/MikeDepies/MapElitesKotlin.svg)](https://jitpack.io/#MikeDepies/MapElitesKotlin)

To integrate MapElitesKotlin into your project, follow these steps:

### Step 1: Add JitPack repository

First, add the JitPack repository to your build file. If you're using Gradle, add it to your root `build.gradle` at the end of repositories:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add MapElitesKotlin dependency
```gradle
dependencies {
    implementation 'com.github.MikeDepies:MapElitesKotlin:0.1.5'
}
```

### Step 3: Use MapElitesKotlin

```kotlin
fun main() {
    // Define the feature descriptor, mutation, crossover, and evaluate functions
    val metaList = createEmbeddingFeatureMeta(-2.0, 2.0, 10, 16)
    val featureDescriptorTransformer = FeatureDescriptorTransformer(metaList)
    val featureDescriptor = featureDescriptorTransformer::featureDescriptor
    
    val eliteMutator = object : EliteMutator {
        override fun mutate(solution: Solution): Solution {
            TODO("Not yet implemented")
        }

        override fun crossover(parent1: Solution, parent2: Solution): Solution {
            TODO("Not yet implemented")
        }

        override fun generateRandomSolution(): Solution {
            TODO("Not yet implemented")
        }
    }
    val eliteEvaluator = object : EliteEvaluator {
        override fun evaluate(solution: Solution): Double {
            TODO("Not yet implemented")
        }
    }
    val eliteMapOperator = EliteMapOperatorImpl(featureDescriptor, mutableMapOf())
    val mapElites = MapElitesAlgorithm(eliteMapOperator, eliteMutator, eliteEvaluator, MapElitesConfiguration((.5)))
    mapElites.initialize(100)
    mapElites.evolve(1000)
}
```
## Goals
The primary goal of this project is to develop a simple yet extensible Map Elites framework tailored for Kotlin. This framework is designed with versatility in mind, making no assumptions about the model type it is applied to. As a result, it can be seamlessly integrated with various domains, ranging from Neural Networks to more abstract use cases. This flexibility allows developers to leverage the power of the Map Elites algorithm in a wide array of applications, fostering innovation and efficiency in problem-solving.


## Features

- Map Elites algorithm implementation in Kotlin
- Seamless integration with Kotlin projects
- Configurable for different problem domains

## Requirements

- Kotlin version 1.9.22+
- JDK 18

## Usage

See the `examples` directory for how to implement and utilize the Map Elites algorithm in your projects.

## Contributing

We welcome contributions! Feel free to submit pull requests or open issues for any suggestions or issues.

## License

Licensed under the MIT License. See the LICENSE file for more details.
