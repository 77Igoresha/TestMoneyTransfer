import java.util.Scanner

fun main() {
    println("Введите сумму перевода")
    val scanner = Scanner(System.`in`)
    val amount = scanner.nextInt()           //Сумма перевода
    val amountTransferInThisMonth = 0        //Сумма предыдущих переводов в этом месяце
    val cards = "Mastercard"           //тип карты

    val userCommission = calculationCommission(cards, amountTransferInThisMonth, amount)

    val totalMessage = "Комиссия за перевод: $userCommission рублей"
    println(totalMessage)
}

fun calculationCommission(cards: String, amountTransferInThisMonth: Int, amount: Int): Double {
    val userCommission = when (cards) {
        "Mastercard", "Maestro" -> transferFromMastercardOrMaestro(amountTransferInThisMonth, amount)
        "Visa", "Мир" -> transferFromVisaOrMir(amount)
        else -> 0.0    //VK Pay
    }
    return userCommission
}

fun transferFromMastercardOrMaestro(amountTransferInThisMonth: Int, amount: Int): Double {
    var userFee = 0.0
    val allAmountTransferInThisMonth = amountTransferInThisMonth + amount

    if (allAmountTransferInThisMonth >= 75000) {
        val transferFee = 0.6                          //0.6% комиссия за перевод
        userFee = amount * transferFee / 100 + 20     //комиссия пользователя 0,6% + 20 руб
    }
    return userFee
}

fun transferFromVisaOrMir(amount: Int): Double {
    val transferFee = 0.75           //0.75% комиссия за перевод
    val minTransferFee = 35         //минимальная комиссия за перевод в рублях
    var userFee: Double = amount * transferFee / 100   //комиссия пользователя

    if (userFee <= minTransferFee) {
        userFee = minTransferFee.toDouble()
    }
    return userFee
}