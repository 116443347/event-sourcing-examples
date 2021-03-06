package net.chrisrichardson.eventstore.examples.bank.web


import com.fasterxml.jackson.databind.ObjectMapper
import net.chrisrichardson.eventstore.json.EventStoreCommonObjectMapping
import org.springframework.context.annotation.{Bean, Configuration, Import}
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

import scala.collection.JavaConversions._

@Configuration
@Import(Array(classOf[AccountsQuerySideServiceConfiguration]))
class AccountsQuerySideServiceTestConfiguration {

  @Bean
  def restTemplate() = {
    val restTemplate = new RestTemplate()
    restTemplate.getMessageConverters foreach {
      case mc: MappingJackson2HttpMessageConverter =>
        mc.setObjectMapper(EventStoreCommonObjectMapping.getObjectMapper)
      case _ =>
    }
    restTemplate
  }

}
