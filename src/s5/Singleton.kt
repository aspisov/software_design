package s5

import java.io.FileWriter
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.Date
import com.google.gson.Gson
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

data class LogMessage(val type: String, val source: String, val timestamp: String, val content: String)

object Logger {
    private var textLogPath: String = "logs.txt"
    private var jsonLogPath: String = "logs.json"
    private val lock = ReentrantLock()
    private val gson = Gson()

    fun log(type: String, source: String, content: String) {
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
        val message = LogMessage(type, source, timestamp, content)

        lock.withLock {
            writeTextLog(message)
            writeJsonLog(message)
        }
    }

    private fun writeTextLog(message: LogMessage) {
        FileWriter(textLogPath, true).use { fw ->
            PrintWriter(fw).use { pw ->
                pw.println("${message.timestamp} [${message.type}] ${message.source}: ${message.content}")
            }
        }
    }

    private fun writeJsonLog(message: LogMessage) {
        FileWriter(jsonLogPath, true).use { fw ->
            PrintWriter(fw).use { pw ->
                pw.println(gson.toJson(message))
            }
        }
    }

    fun changeTextPath(newPath: String) {
        textLogPath = newPath
    }

    fun changeJsonPath(newPath: String) {
        jsonLogPath = newPath
    }
}
