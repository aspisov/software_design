package s7

data class EditorState(val content: String)

class TextEditor {
    private var content: String = ""

    fun createState(): EditorState {
        return EditorState(content)
    }

    fun setState(state: EditorState) {
        content = state.content
    }

    fun type(words: String) {
        content += words
    }

    override fun toString(): String {
        return content
    }
}

class History {
    private val states = ArrayDeque<EditorState>()

    fun saveState(state: EditorState) {
        states.addLast(state)
    }

    fun undo(): EditorState? {
        if (states.isNotEmpty()) {
            states.removeLast()
            if (states.isNotEmpty()) {
                return states.last()
            }
        }
        return null
    }
}

fun main() {
    val editor = TextEditor()
    val history = History()

    editor.type("Это первое предложение. ")
    history.saveState(editor.createState())

    editor.type("Это второе предложение. ")
    history.saveState(editor.createState())

    println("Текущее состояние: ${editor}")

    // Откат изменений
    editor.setState(history.undo() ?: return)
    println("Состояние после отката: ${editor}")

    editor.setState(history.undo() ?: return)
    println("Состояние после второго отката: ${editor}")
}
