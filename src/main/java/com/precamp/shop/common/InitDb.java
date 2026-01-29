package com.precamp.shop.common;

import com.precamp.shop.service.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final ProductService productService;

        public void dbInit() {
            // 초기 테스트 데이터들
            productService.createProduct("아이폰 15 프로", 1550000, 10, "애플의 최신 스마트폰");
            productService.createProduct("로지텍 키보드", 120000, 50, "사무용 기계식 키보드");
            productService.createProduct("맥북 에어 M3", 1890000, 5, "가볍고 강력한 노트북");
            productService.createProduct("에어팟 프로 2", 320000, 100, "노이즈 캔슬링 이어폰");

            // 품절 테스트용 데이터 (재고 0)
            productService.createProduct("단종된 마우스", 10000, 0, "이제는 구할 수 없는 마우스");
        }
    }
}