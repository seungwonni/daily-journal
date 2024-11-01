package com.poker;

import com.poker.dto.CompletedCardInfo;
import com.poker.service.SettingCardService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest
@RequiredArgsConstructor
@RestController
class PokerApplicationTests {

    private final SettingCardService settingCardService;


    @Test
    void 스트레이프플러쉬테스트() {
        CompletedCardInfo cardInfo = settingCardService.startProcess(1);
    }

}
