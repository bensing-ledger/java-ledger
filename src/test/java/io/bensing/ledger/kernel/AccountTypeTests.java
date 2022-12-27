package io.bensing.ledger.kernel;

import io.bensing.ledger.jupiter.SmallTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class AccountTypeTests {

    @SmallTest
    @DisplayName("Check that five (5) account type values return correctly.")
    public void CheckAccountTypeValues() {
        Assertions.assertEquals("asset", AccountType.Asset.Value(), "Expected the value of [asset].");
        Assertions.assertEquals("liability", AccountType.Liability.Value(), "Expected the value of [liability].");
        Assertions.assertEquals("revenue", AccountType.Revenue.Value(), "Expected the value of [revenue].");
        Assertions.assertEquals("expense", AccountType.Expense.Value(), "Expected the value of [expense].");
        Assertions.assertEquals("equity", AccountType.Equity.Value(), "Expected the value of [equity].");
    }

    @SmallTest
    @DisplayName("Check that five (5) account types are equal when compared to themselves.")
    public void CheckAccountTypesEqual() {
        Assertions.assertTrue(AccountType.Asset.Equals(AccountType.Asset), "Expected Asset to equal Asset.");
        Assertions.assertTrue(AccountType.Liability.Equals(AccountType.Liability),"Expected Liability to equal Liability.");;
        Assertions.assertTrue(AccountType.Revenue.Equals(AccountType.Revenue),"Expected Revenue to equal Revenue.");
        Assertions.assertTrue(AccountType.Expense.Equals(AccountType.Expense),"Expected Expense to equal Expense.");
        Assertions.assertTrue(AccountType.Equity.Equals(AccountType.Equity),"Expected Equity to equal Equity.");
    }

    @SmallTest
    @DisplayName("Check that five (5) account types are equal when compared to others.")
    public void CheckAccountTypesNotEqual() {
        Assertions.assertFalse(AccountType.Asset.Equals(AccountType.Liability), "Asset should not equal any other account type.");
        Assertions.assertFalse(AccountType.Liability.Equals(AccountType.Revenue), "Liability should not equal any other account type.");
        Assertions.assertFalse(AccountType.Revenue.Equals(AccountType.Expense), "Revenue should not equal any other account type.");
        Assertions.assertFalse(AccountType.Expense.Equals(AccountType.Equity), "Expense should not equal any other account type.");
        Assertions.assertFalse(AccountType.Equity.Equals(AccountType.Asset), "Equity should not equal any other account type.");
    }
}
