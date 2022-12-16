package io.bensing.ledger.usecase.cash_deposit;

import io.bensing.kernel.SuccessfulOutcome;
import io.bensing.kernel.UnsuccessfulOutcome;
import io.bensing.kernel.identity.Id;
import io.bensing.kernel.identity.IdentityGatewayMock;
import io.bensing.ledger.usecase.chart_of_accounts.UserWalletAccountGatewayMock;
import io.bensing.ledger.usecase.ledger.LedgerGatewayMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CashDeposit_Tests {

    @Test
    @Tag("Small")
    @DisplayName("Deposit money into wallet.")
    public void DepositIntoWallet_Successfully() {

        long userIdentity = 1;
        double money = 25.00;
        String currency = "USD";

        var identityGateway = new IdentityGatewayMock(new Id(2L));
        var userWalletAccountGateway = new UserWalletAccountGatewayMock(234232342);
        var ledgerGateway = new LedgerGatewayMock(new SuccessfulOutcome());
        var usecase = new CashDeposit(identityGateway, userWalletAccountGateway, ledgerGateway);

        var receipt = usecase.Deposit(userIdentity, money, currency);

        // Validate there were not transactions process issues with the deposit.
        Assertions.assertTrue(usecase.WasSuccessful());
        Assertions.assertFalse(usecase.HasError());
        Assertions.assertEquals("", usecase.ErrorMessage());

        // Validate the deposit transactions occurred as expected.
        // Check outcome of deposit transaction
        Assertions.assertTrue(receipt.WasSuccessful());
        Assertions.assertFalse(receipt.HasError());
        Assertions.assertEquals("", receipt.ErrorMessage());

        // Check receipt data
        Assertions.assertEquals(2L, receipt.TransactionId().Value());
        receipt.DateAndTime();
        Assertions.assertEquals(1L, receipt.UserId());
        Assertions.assertEquals(25.00, receipt.MoneyDeposited());
        Assertions.assertEquals("USD", receipt.CurrencyDeposited());

    }

    @Test
    @Tag("Small")
    @DisplayName("Deposit money is unsuccessful, ledger gateway unsuccessful outcome.")
    public void LedgerGatewayUnsuccessfulOutcome() {

        long userIdentity = 1;
        double money = 25.00;
        String currency = "USD";

        var identityGateway = new IdentityGatewayMock(new Id(2L));
        var userWalletAccountGateway = new UserWalletAccountGatewayMock(234232342);
        var ledgerGateway = new LedgerGatewayMock(new UnsuccessfulOutcome("Some issue with ledger."));
        var usecase = new CashDeposit(identityGateway, userWalletAccountGateway, ledgerGateway);

        var receipt = usecase.Deposit(userIdentity, money, currency);

        // Validate there were not transactions process issues with the deposit.
        Assertions.assertFalse(usecase.WasSuccessful());
        Assertions.assertTrue(usecase.HasError());
        Assertions.assertEquals("Some issue with ledger.", usecase.ErrorMessage());

        // Validate the deposit transactions occurred as expected.
        // Check outcome of deposit transaction
        Assertions.assertFalse(receipt.WasSuccessful());
        Assertions.assertTrue(receipt.HasError());
        Assertions.assertEquals("There was an issue making the deposit. Please try again.", receipt.ErrorMessage());

        // Check receipt data
        Assertions.assertEquals(2L, receipt.TransactionId().Value());
        receipt.DateAndTime();
        Assertions.assertEquals(1L, receipt.UserId());
        Assertions.assertEquals(25.00, receipt.MoneyDeposited());
        Assertions.assertEquals("USD", receipt.CurrencyDeposited());

    }

}