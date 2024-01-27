package s5

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