package com.janiplayer.filemanager

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class OptimizedFileScanner {

    private val cache = mutableMapOf<String, List<FileItem>>()

    suspend fun scanDirectory(path: String): List<FileItem> {
        cache[path]?.let { return it }

        return withContext(Dispatchers.IO) {
            val dir = File(path)
            if (!dir.exists() || !dir.isDirectory) return@withContext emptyList<FileItem>()

            val result = dir.listFiles()?.map { file ->
                FileItem(
                    name = file.name,
                    path = file.absolutePath,
                    isDirectory = file.isDirectory,
                    size = file.length(),
                    lastModified = file.lastModified()
                )
            }?.sortedBy { it.name.lowercase() } ?: emptyList()

            cache[path] = result
            result
        }
    }
}
