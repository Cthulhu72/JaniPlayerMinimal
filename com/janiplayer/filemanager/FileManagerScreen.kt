onClick = {
    if (!item.isDirectory && item.path.endsWith(".mp4")) {
        navController.navigate("video/${item.path}")
    }
}
