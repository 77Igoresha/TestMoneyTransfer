import junit.framework.Assert.assertEquals
import org.junit.Test

class MoneyTransferAsVKKtTest {

    @Test
    fun should_calculate_correct_if_card_is_Maestro() {
        //arrange
        val cards = "Maestro"
        val amountTransferInThisMonth = 0
        val amount = 50000
        val realUserCommission = 0.0

        //act
        val calFee = calculationCommission(
            cards = cards,
            amountTransferInThisMonth = amountTransferInThisMonth,
            amount = amount
        )

        //assert
        assertEquals(realUserCommission, calFee)
    }

    @Test
    fun should_calculate_correct_if_card_is_Visa() {
        //arrange
        val cards = "Visa"
        val amountTransferInThisMonth = 0
        val amount = 50000
        val realUserCommission = 375.0

        //act
        val calFee =
            calculationCommission(
                cards = cards,
                amountTransferInThisMonth = amountTransferInThisMonth,
                amount = amount
            )

        //assert
        assertEquals(realUserCommission, calFee)
    }

    @Test
    fun should_calculate_correct_if_card_is_Mir() {
        //arrange
        val cards = "Мир"
        val amountTransferInThisMonth = 0
        val amount = 50000
        val realUserCommission = 375.0

        //act
        val calFee = calculationCommission(
            cards = cards,
            amountTransferInThisMonth = amountTransferInThisMonth,
            amount = amount
        )

        //assert
        assertEquals(realUserCommission, calFee)
    }

    @Test
    fun should_calculate_correct_if_card_is_VKPay() {
        //arrange
        val cards = "VK"
        val amountTransferInThisMonth = 0
        val amount = 50000
        val realUserCommission = 0.0

        //act
        val calFee = calculationCommission(
            cards = cards,
            amountTransferInThisMonth = amountTransferInThisMonth,
            amount = amount
        )

        //assert
        assertEquals(realUserCommission, calFee)
    }

    @Test
    fun should_calculate_correct_userFee_for_Mastercard_Or_Maestro_if_limit_is_not_max() {
        //arrange
        val amount = 50000
        val amountTransferInThisMonth = 0
        val realUserFee = 0.0

        //act
        val userFee = transferFromMastercardOrMaestro(
            amountTransferInThisMonth = amountTransferInThisMonth,
            amount = amount
        )

        //assert
        assertEquals(realUserFee, userFee)
    }

    @Test
    fun should_calculate_correct_userFee_for_Mastercard_Or_Maestro_if_limit_is_MaxedOut() {
        //arrange
        val amount = 50000
        val amountTransferInThisMonth = 40000
        val realUserFee = 320.0

        //act
        val userFee = transferFromMastercardOrMaestro(
            amountTransferInThisMonth = amountTransferInThisMonth,
            amount = amount
        )

        //assert
        assertEquals(realUserFee, userFee)
    }

    @Test
    fun should_calculate_correct_userFee_for_Mir_and_Visa() {
        //arrange
        val amount = 50000
        val realUserFee = 375.0

        //act
        val userFee = transferFromVisaOrMir(amount)

        //assert
        assertEquals(realUserFee, userFee)
    }

    @Test
    fun should_calculate_correct_minTransferFee_for_Mir_and_Visa() {
        //arrange
        val amount = 500
        val realUserFee = 35.0

        //act
        val userFee = transferFromVisaOrMir(amount)

        //assert
        assertEquals(realUserFee, userFee)
    }
}