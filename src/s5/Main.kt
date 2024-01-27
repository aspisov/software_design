package s5

import com.google.gson.Gson

fun main() {
    // Singleton
    Logger.log("inf", "MainModule", "Application started")
    Logger.log("err", "NetworkModule", "Network error occurred")

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

    // Factory
    val motherboard =
        ProductFactory.createMotherboard("MB1234", "SuperBoard X1", 199.99, "AM4", 1, "DDR4", 4, "3200MHz")
    val processor = ProductFactory.createProcessor("CPU5678", "FastChip Z2", 299.99, "AM4", 6, "3.6GHz")
    val hardDrive = ProductFactory.createHardDrive("HD9101", "SpeedStorage 4TB", 99.99, "4TB", "7200rpm")
}