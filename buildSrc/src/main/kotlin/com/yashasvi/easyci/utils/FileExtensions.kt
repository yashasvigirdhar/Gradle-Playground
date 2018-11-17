package com.yashasvi.easyci.utils

import java.io.File

val File.delimiterForMap: Char
    get() = '='

fun File.readAsMap(): MutableMap<String, String> {
    if (!canRead()) {
        throw Exception("can't read file $absoluteFile")
    }
    val resultMap = mutableMapOf<String, String>()
    with(readLines()) {
        for (line in this) {
            with(line.split(delimiterForMap)) {
                if (this.size != 2) {
                    throw Exception("file $absoluteFile is not in correct format")
                }
                resultMap[this[0]] = this[1]
            }
        }
    }
    return resultMap
}

fun File.writeMap(props: Map<String, String>) {
    if (!canWrite()) {
        throw Exception("can't write to file $absoluteFile")
    }
    for ((key, value) in props) {
        writeText(key + delimiterForMap + value + "\n")
    }
}