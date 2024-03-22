package s5

import com.google.gson.Gson

abstract class Product(val nomenclatureNumber: String, val name: String, val cost: Double)

class Motherboard(nomenclatureNumber: String, name: String, cost: Double,
                  val socketType: String, val processorCount: Int, val memoryType: String, val slotCount: Int, val frequency: String)
    : Product(nomenclatureNumber, name, cost)

class Processor(nomenclatureNumber: String, name: String, cost: Double,
                val socketType: String, val coreCount: Int, val frequency: String)
    : Product(nomenclatureNumber, name, cost)

class HardDrive(nomenclatureNumber: String, name: String, cost: Double,
                val capacity: String, val speed: String)
    : Product(nomenclatureNumber, name, cost)

object ProductFactory {
    fun createMotherboard(nomenclatureNumber: String, name: String, cost: Double, socketType: String, processorCount: Int, memoryType: String, slotCount: Int, frequency: String): Motherboard {
        return Motherboard(nomenclatureNumber, name, cost, socketType, processorCount, memoryType, slotCount, frequency)
    }

    fun createProcessor(nomenclatureNumber: String, name: String, cost: Double, socketType: String, coreCount: Int, frequency: String): Processor {
        return Processor(nomenclatureNumber, name, cost, socketType, coreCount, frequency)
    }

    fun createHardDrive(nomenclatureNumber: String, name: String, cost: Double, capacity: String, speed: String): HardDrive {
        return HardDrive(nomenclatureNumber, name, cost, capacity, speed)
    }
}

fun main() {
    // Factory
    val motherboard =
        ProductFactory.createMotherboard("MB1234", "SuperBoard X1", 199.99, "AM4", 1, "DDR4", 4, "3200MHz")
    val processor = ProductFactory.createProcessor("CPU5678", "FastChip Z2", 299.99, "AM4", 6, "3.6GHz")
    val hardDrive = ProductFactory.createHardDrive("HD9101", "SpeedStorage 4TB", 99.99, "4TB", "7200rpm")
}