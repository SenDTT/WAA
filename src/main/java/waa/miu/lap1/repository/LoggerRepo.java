package waa.miu.lap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waa.miu.lap1.entity.Logger;

public interface LoggerRepo extends JpaRepository<Logger, Long> {
}
