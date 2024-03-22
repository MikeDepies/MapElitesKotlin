package com.github.mikedepies.mapelites

class EliteMapOperatorImpl(val featureDescriptor: (Solution) -> List<Int>, val map: MutableMap<List<Int>, Solution>) :
    EliteMapOperator {
    override fun placeInMap(solution: Solution) {
        val cellIndices = featureDescriptor(solution)
        val cell = map[cellIndices]
        if (cell == null || solution.performance > cell.performance) {
            map[cellIndices] = solution
        }
    }
    override fun getCell(cellIndices: List<Int>): Solution? {
        return map[cellIndices]
    }


    override fun selectParents(): Pair<Solution, Solution> {
        //select two solutions from the map
        val cell = map.values.random()
        val cell2 = map.values.random()
        return Pair(cell, cell2)
    }
}