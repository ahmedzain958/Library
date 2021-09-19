package com.zainco.library

import java.math.BigDecimal

sealed class Optional<out V> {
    // ...
    abstract fun isPresent(): Boolean
}


//sealed vs enum
sealed class Payment
data class CashPayment(val amount: BigDecimal, val pointId: Int): Payment()
data class CardPayment(val amount: BigDecimal, val orderId: Int): Payment()
data class BankTransfer(val amount: BigDecimal, val orderId: Int): Payment()
fun process(payment: Payment) {
    when (payment) {
        is CashPayment -> {
            //showPaymentInfo(payment.amount, payment.pointId)
        }
        is CardPayment -> {
            //moveToCardPaiment(payment.amount, payment.orderId)
        }
        is BankTransfer -> {
            /*val bankTransferRepo = BankTransferRepo()
            val transferDetails = bankTransferRepo.getDetails()
            displayTransferInfo(payment.amount, transferDetails)
            bankTransferRepo.setUpPaymentWathcher(payment.orderId, payment.amount, transferDetails)*/
        }
    }
}

/*
enum class Payment2{
     CashPayment( amount: BigDecimal, val pointId: Int),
     CardPayment(val amount: BigDecimal, val orderId: Int),
     BankTransfer(val amount: BigDecimal, val orderId: Int);
}*/
