package s5

import com.google.gson.Gson

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

fun main() {
    // Builder
    val report = ReportBuilder()
        .setTitle("Исследование Kotlin")
        .setTask("Описание особенностей языка Kotlin")
        .setAnnotation("Аннотация отчета")
        .setContent("Содержимое отчета")
        .setBibliography(listOf("Источник 1", "Источник 2"))
        .setAppendices(listOf("Приложение 1", "Приложение 2"))
        .build()

    // converting report to json
    val jsonReport = Gson().toJson(report)
    println(jsonReport)
}
