package br.com.hcb.ecommerce.payment.streaming;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CheckoutProcessor {

  String OUTPUT = "payment-paid-output";
  String INPUT = "checkout-created-input";
  @Output(OUTPUT)
  MessageChannel ouput();

  @Input(INPUT)
  SubscribableChannel input();
}
