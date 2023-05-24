package antigravity.service;

public interface PaymentGatewayService {

	//결제 확인
	boolean checkPaymentStatus(String transactionId);

	//결제 취소
	void cancelPayment(String transactionId);
}
