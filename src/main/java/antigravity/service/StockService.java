package antigravity.service;

import antigravity.domain.entity.Stock;
import antigravity.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class StockService {

	private final StockRepository stockRepository;

	public long decreaseAndGet(long productId, int count) {
		Stock stock = stockRepository.findByProductId(productId).orElseThrow(() -> new RuntimeException());
		return stock.decreaseQuantity(count);
	}

}
