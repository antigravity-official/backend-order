package antigravity.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MockNotifyService implements NotifyService {
	@Override public void send() {
		log.error("send notify to someone!");
	}
}
