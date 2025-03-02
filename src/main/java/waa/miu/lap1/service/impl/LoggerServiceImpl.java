package waa.miu.lap1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.miu.lap1.entity.Logger;
import waa.miu.lap1.repository.LoggerRepo;
import waa.miu.lap1.service.LoggerService;

@Service
public class LoggerServiceImpl implements LoggerService {
    @Autowired
    private LoggerRepo loggerRepository;

    public void logOperation(String operation) {
        Logger log = new Logger("StaticUser", operation);
        loggerRepository.save(log);
    }
}
