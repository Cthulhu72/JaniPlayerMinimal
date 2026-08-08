@Composable
fun FileManagerScreen(
    viewModel: FileManagerViewModel,
    onAddToPlaylist: (Long) -> Unit
) {
    Column(Modifier.fillMaxSize()) {

        // Breadcrumb
        Text(
            text = viewModel.currentPath,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        // Rendezés
        Row(Modifier.padding(horizontal = 16.dp)) {
            TextButton(onClick = { viewModel.sortByName() }) { Text("Név") }
            TextButton(onClick = { viewModel.sortByDate() }) { Text("Dátum") }
            TextButton(onClick = { viewModel.sortBySize() }) { Text("Méret") }
        }

        Divider()

        // Lista
        LazyColumn {
            items(viewModel.files) { item ->

                val selected = viewModel.selected.contains(item)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (viewModel.multiSelectMode) {
                                viewModel.toggleSelect(item)
                            } else {
                                viewModel.open(item)
                            }
                        }
                        .padding(12.dp)
                ) {

                    if (viewModel.multiSelectMode) {
                        Checkbox(
                            checked = selected,
                            onCheckedChange = { viewModel.toggleSelect(item) }
                        )
                        Spacer(Modifier.width(8.dp))
                    }

                    Icon(
                        imageVector = if (item.isDirectory)
                            Icons.Default.Folder else Icons.Default.MusicNote,
                        contentDescription = null
                    )

                    Spacer(Modifier.width(12.dp))

                    Column {
                        Text(item.name)
                        Text(
                            text = if (item.isDirectory) "Mappa" else "${item.size} bájt",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }

        // Multi-select műveletek
        if (viewModel.multiSelectMode) {
            Divider()
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { viewModel.deleteSelected() }) {
                    Text("Törlés")
                }
                Button(onClick = { onAddToPlaylist(1L) }) {
                    Text("Playlistbe")
                }
            }
        }
    }
}
