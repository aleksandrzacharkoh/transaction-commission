package org.zacharko.transaction.commission.helper;

import org.zacharko.transaction.commission.controller.request.TransactionCommissionRequest;
import org.zacharko.transaction.commission.csv.TransactionCommissionTestData;

public class TransactionCommissionHelper
{
   public static TransactionCommissionRequest mapToRequest(TransactionCommissionTestData data) {
      return new TransactionCommissionRequest(data.getDate(), data.getAmount(), data.getCommissionCurrency(), data.getClientId());
   }
}
