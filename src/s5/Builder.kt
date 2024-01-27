package s5

class Report(val title: String, val task: String, val annotation: String, val content: String, val bibliography: List<String>, val appendices: List<String>)

class ReportBuilder {
    private var title: String = ""
    private var task: String = ""
    private var annotation: String = ""
    private var content: String = ""
    private var bibliography: List<String> = listOf()
    private var appendices: List<String> = listOf()

    fun setTitle(title: String) = apply { this.title = title }
    fun setTask(task: String) = apply { this.task = task }
    fun setAnnotation(annotation: String) = apply { this.annotation = annotation }
    fun setContent(content: String) = apply { this.content = content }
    fun setBibliography(bibliography: List<String>) = apply { this.bibliography = bibliography }
    fun setAppendices(appendices: List<String>) = apply { this.appendices = appendices }

    fun build() = Report(title, task, annotation, content, bibliography, appendices)
}
