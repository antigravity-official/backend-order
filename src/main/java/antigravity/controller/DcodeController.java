package antigravity.controller;

import antigravity.model.request.OrderRequest;
import antigravity.model.response.OrderDetailResponse;
import antigravity.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dcode")
public class DcodeController {

    private final OrderService orderService;

    //주문생성
    @Async
    @PostMapping("/orders")
    public void orderCreate(@RequestBody OrderRequest request) {
        orderService.orderCreate(request);
    }

    //주문내역
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<List<OrderDetailResponse>> getOrderDetail(@PathVariable(name = "orderId") long orderId) {
        List<OrderDetailResponse> response = orderService.getOrderDetail(orderId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
