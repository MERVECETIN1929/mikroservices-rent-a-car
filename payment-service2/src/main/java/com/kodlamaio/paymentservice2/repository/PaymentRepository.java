package com.kodlamaio.paymentservice2.repository;

import com.kodlamaio.paymentservice2.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    @Query("select p from Payment p where p.cardNumber =?1  and p.cvv=?2 and p.year=?3 and p.month=?4 and p.cardHolderName=?5")
    boolean validateCard(String cardNumber,String cvv,String year,String month,String cardHolderName);
    boolean existsByCardNumberAndCardHolderNameAndYearAndMonthAndCvv(
            String cardNumber, String cardHolderName, String year, String month, String cvv);
    Payment findPaymentByCardNumber(String cardNumber);

}
