package antigravity.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MockPaymentService implements PaymentGatewayService {

	private final NotifyService notifyService;

	public MockPaymentService(NotifyService notifyService) {
		this.notifyService = notifyService;
	}

	@Override public boolean checkPaymentStatus(String transactionId) {
		return true;
	}

	@Override public void cancelPayment(String transactionId) {
		log.error("request cancellation to payment gateway - transaction id:{}", transactionId);
	}

	public void sendNotification() {
		notifyService.send();
	}
}
