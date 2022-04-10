package org.zacharko.transaction.commission.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
@EnableCaching
public class Configuration
{
   @Bean
   public CacheManager cacheManager() {
      return new ConcurrentMapCacheManager("exchangeRates");
   }

   @Bean
   public RestTemplate restTemplate() {
      return new RestTemplate();
   }
}
