package br.com.hcb.ecommerce.payment.listener;

import br.com.hcb.ecommerce.payment.event.CheckoutCreatedEvent;
import br.com.hcb.ecommerce.payment.event.PaymentCreatedEvent;
import br.com.hcb.ecommerce.payment.streaming.CheckoutProcessor;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckoutCreatedListener {

  private final CheckoutProcessor checkoutProcessor;

  @StreamListener(CheckoutProcessor.INPUT)
  public void handler(@Payload CheckoutCreatedEvent event) {
    final PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent
        .newBuilder()
        .setCheckoutCode(event.getCheckoutCode())
        .setCheckoutStatus(event.getStatus())
        .setPaymentCode(UUID.randomUUID().toString())
        .build();
    checkoutProcessor.ouput().send(MessageBuilder.withPayload(paymentCreatedEvent).build());

  }
}
