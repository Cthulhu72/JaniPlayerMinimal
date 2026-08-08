package com.janiplayer.filemanager

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OptimizedFileManagerViewModel(
    private val scanner: OptimizedFileScanner
) : ViewModel() {

    var currentPath by mutableStateOf("")
        private set

    val files = mutableStateListOf<FileItem>()
    val selected = mutableStateListOf<FileItem>()
    var multiSelectMode by mutableStateOf(false)
        private set

    fun loadDirectory(path: String) {
        currentPath = path
        viewModelScope.launch {
            val newList = scanner.scanDirectory(path)
            files.clear()
            files.addAll(newList)
            selected.clear()
            multiSelectMode = false
        }
    }

    fun toggleSelect(item: FileItem) {
        if (!multiSelectMode) multiSelectMode = true

        if (selected.any { it.path == item.path }) {
            selected.removeAll { it.path == item.path }
            if (selected.isEmpty()) multiSelectMode = false
        } else {
            selected.add(item)
        }
    }
}
