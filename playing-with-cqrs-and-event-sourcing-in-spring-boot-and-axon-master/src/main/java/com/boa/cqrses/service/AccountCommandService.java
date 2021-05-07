package com.boa.cqrses.service;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.boa.cqrses.command.CreateAccountCommand;
import com.boa.cqrses.command.CreditMoneyCommand;
import com.boa.cqrses.command.DebitMoneyCommand;
import com.boa.cqrses.entity.BankAccount;
import com.boa.cqrses.rest.dto.AccountCreationDTO;
import com.boa.cqrses.rest.dto.MoneyAmountDTO;

import static com.boa.cqrses.service.ServiceUtils.formatUuid;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class AccountCommandService {
    private final CommandGateway commandGateway;

    public CompletableFuture<BankAccount> createAccount(AccountCreationDTO creationDTO) {
        return this.commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID(),
                creationDTO.getInitialBalance(),
                creationDTO.getOwner()
        ));
    }

    public CompletableFuture<String> creditMoneyToAccount(String accountId,
                                                          MoneyAmountDTO moneyCreditDTO) {
        return this.commandGateway.send(new CreditMoneyCommand(
                formatUuid(accountId),
                moneyCreditDTO.getAmount()
        ));
    }

    public CompletableFuture<String> debitMoneyFromAccount(String accountId,
                                                           MoneyAmountDTO moneyDebitDTO) {
        return this.commandGateway.send(new DebitMoneyCommand(
                formatUuid(accountId),
                moneyDebitDTO.getAmount()
        ));
    }
}
