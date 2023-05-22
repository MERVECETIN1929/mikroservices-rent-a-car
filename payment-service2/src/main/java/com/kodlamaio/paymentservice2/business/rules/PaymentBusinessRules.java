package com.kodlamaio.paymentservice2.business.rules;


import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.paymentservice2.entities.Payment;
import com.kodlamaio.paymentservice2.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository repository;
    public void isBalanceEnough(double price, Payment payment){
        if(payment.getBalance()<price){
            throw new BusinessException("money not enought");
        }

    }
    public void ensureCheckValidateIfTrueCard(Payment request){
        boolean result=repository.existsByCardNumberAndCardHolderNameAndYearAndMonthAndCvv(request.getCardNumber(),request.getCardHolderName(),request.getYear(),request.getMonth(), request.getCvv());
        if(!result){
            throw new BusinessException("card  not validate");
        }
    }
}
