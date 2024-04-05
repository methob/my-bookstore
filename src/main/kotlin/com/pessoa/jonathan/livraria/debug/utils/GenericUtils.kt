package com.pessoa.jonathan.livraria.debug.utils

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

fun loadDotenv(filePath: String) {
    try {
        val allLines: List<String> = Files.readAllLines(Paths.get(filePath))
        for (line in allLines) {
            val keyValuePair = line.split("=".toRegex(), limit = 2).toTypedArray()
            if (keyValuePair.size == 2) {
                System.setProperty(keyValuePair[0], keyValuePair[1])
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}