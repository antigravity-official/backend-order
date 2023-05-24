package antigravity;

import antigravity.controller.DcodeController;
import antigravity.model.request.OrderRequest;
import antigravity.model.request.ProductInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
public class AntigravityApplication {
	@Autowired
	DcodeController dcodeController;

	public static void main(String[] args) {
		SpringApplication.run(AntigravityApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {

		ProductInfoRequest productInfo = ProductInfoRequest.builder()
				.productId(1)
				.productCount(2)
				.color("black")
				.size("XL")
				.build();

		for (int i = 1; i <= 200; i++) {
			OrderRequest request = OrderRequest.builder()
					.userId(i)
					.productInfo(productInfo)
					.couponId(1)
					.point(1000)
					.tId("dcode000" + i)
					.build();

			try {
				dcodeController.orderCreate(request);
			} catch (Exception e) {

			}

		}

	}
}
