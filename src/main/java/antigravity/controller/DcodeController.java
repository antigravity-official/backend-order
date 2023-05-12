package antigravity.controller;

import antigravity.model.request.OrderRequest;
import antigravity.model.response.OrderDetailResponse;
import antigravity.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dcode")
public class DcodeController {

    private final OrderService orderService;

    //주문생성
    @Async
    public void orderCreate(OrderRequest request) {
        orderService.orderCreate(request);
    }

    //주문내역
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetailResponse> getOrderDetail() {
        OrderDetailResponse response = orderService.getOrderDetail();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
