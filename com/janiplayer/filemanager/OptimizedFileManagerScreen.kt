OptimizedLazyList(
    items = viewModel.files,
    keySelector = { it.path }
) { item ->
    JaniListItem(
        icon = stable(if (item.isDirectory) Icons.Default.Folder else Icons.Default.MusicNote),
        title = item.name,
        subtitle = if (item.isDirectory) "Mappa" else "${item.size} bájt",
        selected = viewModel.selected.any { it.path == item.path },
        onClick = { ... }
    )
}
