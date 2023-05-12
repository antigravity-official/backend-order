package antigravity.config;

import antigravity.controller.DcodeController;
import antigravity.model.request.OrderRequest;
import antigravity.model.request.ProductInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OrderRunner implements CommandLineRunner {

    @Autowired
    DcodeController dcodeController;

    @Override
    public void run(String... args) throws Exception {
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

            dcodeController.orderCreate(request);
        }
    }

}
