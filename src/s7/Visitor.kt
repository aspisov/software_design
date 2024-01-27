package s7

class TreeNode(val type: String, val children: MutableList<TreeNode> = mutableListOf())

interface NodeVisitor {
    fun visit(node: TreeNode)
}

class BracketVisitor : NodeVisitor {
    override fun visit(node: TreeNode) {
        when (node.type) {
            "RB" -> { }
            "SB" -> node.children.clear()
            "FB" -> node.children.add(TreeNode("SB"))
        }
    }
}

fun applyVisitor(node: TreeNode, visitor: NodeVisitor) {
    visitor.visit(node)
    node.children.forEach { applyVisitor(it, visitor) }
}

fun parseBrackets(input: String): TreeNode? {
    val root = TreeNode("ROOT")
    val stack = mutableListOf(root)

    input.forEach { char ->
        when (char) {
            '(', '[', '{' -> {
                val node = TreeNode(when (char) {
                    '(' -> "RB"
                    '[' -> "SB"
                    '{' -> "FB"
                    else -> ""
                })
                stack.last().children.add(node)
                stack.add(node)
            }
            ')', ']', '}' -> stack.removeAt(stack.lastIndex)
        }
    }

    return if (stack.size == 1) root.children.single() else null
}

fun treeToBracketExpression(node: TreeNode): String {
    val brackets = mapOf("RB" to "()", "SB" to "[]", "FB" to "{}")
    return brackets[node.type]?.let { bracket ->
        bracket.first() + node.children.joinToString("") { treeToBracketExpression(it) } + bracket.last()
    } ?: ""
}

fun printTree(node: TreeNode, depth: Int = 0) {
    println(" ".repeat(depth * 2) + node.type)
    node.children.forEach { printTree(it, depth + 1) }
}

fun main() {
    val input = "([]({}()[])[()()])"
    val tree = parseBrackets(input) ?: return

    printTree(tree)
    var modifiedExpression = treeToBracketExpression(tree)
    println(modifiedExpression)

    // Apply the visitor to the tree
    print("\n\n\n")
    applyVisitor(tree, BracketVisitor())

    printTree(tree)
    modifiedExpression = treeToBracketExpression(tree)
    println(modifiedExpression)
}
