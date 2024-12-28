package org.sid.ebankingbackend.services;

import jakarta.transaction.Transactional;
import org.sid.ebankingbackend.entities.BankAccount;
import org.sid.ebankingbackend.entities.CurrentAccount;
import org.sid.ebankingbackend.entities.SavingAccount;
import org.sid.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){

        BankAccount bankAccount =
                bankAccountRepository.findById("0a1812a3-4f53-4607-906e-506b87976bf1").orElse(null);
        if (bankAccount != null){
            System.out.println("*******************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());

            if(bankAccount instanceof CurrentAccount){
                System.out.println("Over Draft =>" +((CurrentAccount)bankAccount).getOverdraft());

            }else if(bankAccount instanceof SavingAccount){
                System.out.println("Rate =>"+((SavingAccount)bankAccount).getInterestRate());

            }

            System.out.println("*******************");
            bankAccount.getAccountOperations().forEach(op -> {
                System.out.println(op.getType() + "\t" + op.getOperationDate()+ "\t" + op.getAmount());

            });
        }
    }
}
