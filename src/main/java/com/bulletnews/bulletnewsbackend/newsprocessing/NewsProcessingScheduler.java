package com.bulletnews.bulletnewsbackend.newsprocessing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NewsProcessingScheduler {

    private final NewsProcessingService newsProcessingService;

    @Scheduled(cron = "0 0 6 * * ?")
    public void performTask() {
        log.info("Executing daily task at 6 AM");
        newsProcessingService.fetchProcessAndSaveNewsForEachCategory();
    }

}
