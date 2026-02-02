package com.precamp.shop.common;

import com.precamp.shop.dto.product.ProductCreateRequest;
import com.precamp.shop.service.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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
            log.info("===== 초기 데이터 생성 시작 =====");

            // 전자제품
            createProduct("아이폰 15 프로", 1_550_000, 10, "애플의 최신 스마트폰");
            createProduct("맥북 에어 M3", 1_890_000, 5, "가볍고 강력한 노트북");
            createProduct("에어팟 프로 2", 320_000, 100, "노이즈 캔슬링 이어폰");

            // 주변기기
            createProduct("로지텍 MX Master 3", 120_000, 50, "프로용 무선 마우스");
            createProduct("매직 키보드", 150_000, 30, "애플 매직 키보드");

            log.info("===== 초기 데이터 생성 완료 =====");
        }

        private void createProduct(String name, int price, int stockQuantity, String description) {
            ProductCreateRequest request = new ProductCreateRequest(
                    name,
                    price,
                    stockQuantity,
                    description
            );
            productService.createProduct(request);
            log.debug("상품 생성: {}", name);
        }
    }
}