package org.diffran.bicingplanner.util

import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.PathParser

fun svgPath(string: String): List<PathNode> =
    PathParser().parsePathString(string).toNodes()
