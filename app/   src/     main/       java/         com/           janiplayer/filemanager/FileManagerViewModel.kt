package com.janiplayer.filemanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.File

class FileManagerViewModel(
    private val scanner: FileScanner,
    private val playlistRepo: PlaylistRepository
) : ViewModel() {

    var currentPath = scanner.getRootMusicDir()
        private set

    var files = mutableStateListOf<FileItem>()
        private set

    var selected = mutableStateListOf<FileItem>()
        private set

    var multiSelectMode by mutableStateOf(false)

    init {
        loadDirectory(currentPath)
    }

    fun loadDirectory(path: String) {
        currentPath = path
        files.clear()
        files.addAll(scanner.scanDirectory(path))
        selected.clear()
        multiSelectMode = false
    }

    fun toggleSelect(item: FileItem) {
        if (!multiSelectMode) multiSelectMode = true

        if (selected.contains(item)) {
            selected.remove(item)
            if (selected.isEmpty()) multiSelectMode = false
        } else {
            selected.add(item)
        }
    }

    fun open(item: FileItem) {
        if (item.isDirectory) {
            loadDirectory(item.path)
        } else {
            // audio/video megnyitás
        }
    }

    fun deleteSelected() {
        selected.forEach { File(it.path).delete() }
        loadDirectory(currentPath)
    }

    fun addSelectedToPlaylist(playlistId: Long) {
        viewModelScope.launch {
            selected.forEach { playlistRepo.addFileToPlaylist(playlistId, it.path) }
            selected.clear()
            multiSelectMode = false
        }
    }

    fun sortByName() {
        files.sortBy { it.name.lowercase() }
    }

    fun sortByDate() {
        files.sortByDescending { it.lastModified }
    }

    fun sortBySize() {
        files.sortByDescending { it.size }
    }
}
