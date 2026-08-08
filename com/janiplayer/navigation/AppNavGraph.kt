composable("video/{path}") { backStack ->
    val path = backStack.arguments?.getString("path")!!
    VideoPlayerScreen(context = context, path = path)
}
