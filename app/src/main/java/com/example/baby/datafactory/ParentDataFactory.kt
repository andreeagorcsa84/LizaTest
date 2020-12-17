package com.example.baby.datafactory

import com.example.baby.model.ChildModel
import com.example.baby.model.ParentModel

object ParentDataFactory{

    private fun randomChildren() : List<ChildModel>{
        return ChildDataFactory.getChildren(5)
    }

    fun getParents(count : Int) : List<ParentModel>{
        val parents = mutableListOf<ParentModel>()
        repeat(count){
            val parent = ParentModel(randomChildren())
            parents.add(parent)
        }
        return parents
    }
}