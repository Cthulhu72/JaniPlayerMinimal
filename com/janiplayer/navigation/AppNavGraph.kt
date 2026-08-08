composable(
    route = "filemanager",
    enterTransition = { slideInRight() },
    exitTransition = { slideOutLeft() }
) {
    FileManagerScreen(...)
}
