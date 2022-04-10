package org.zacharko.transaction.commission.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class CSVReader
{
   @SneakyThrows
   public List<TransactionCommissionTestData> loadData(String fileName) {
      File file = ResourceUtils.getFile("classpath:" + fileName);
      try (Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
         CsvToBean<TransactionCommissionTestData> csvToBean = new CsvToBeanBuilder(reader)
               .withType(TransactionCommissionTestData.class)
               .withIgnoreLeadingWhiteSpace(true)
               .build();
         return csvToBean.parse();
      }
   }
}
